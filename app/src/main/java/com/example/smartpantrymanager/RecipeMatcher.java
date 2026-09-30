package com.example.smartpantrymanager;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Locale;

public class RecipeMatcher {

    public static ArrayList<Recipe> getMatchingRecipes(
            Context context) {

        ArrayList<Recipe> matchingRecipes =
                new ArrayList<>();

        IngredientManager.loadIngredients(context);
        RecipeManager.loadRecipes(context);

        ArrayList<Ingredient> pantryIngredients =
                IngredientManager.getIngredients();

        ArrayList<Recipe> recipes =
                RecipeManager.getRecipes();

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        try {

            for (Recipe recipe : recipes) {

                boolean recipeMatches = true;
                boolean hasRequiredIngredients = false;

                Cursor cursor =
                        databaseHelper.getRecipeIngredients(
                                recipe.getId()
                        );

                try {

                    while (cursor.moveToNext()) {

                        hasRequiredIngredients = true;

                        String requiredName =
                                cursor.getString(
                                        cursor.getColumnIndexOrThrow(
                                                "ingredientName"
                                        )
                                );

                        double requiredQuantity =
                                cursor.getDouble(
                                        cursor.getColumnIndexOrThrow(
                                                "requiredQuantity"
                                        )
                                );

                        String requiredUnit =
                                cursor.getString(
                                        cursor.getColumnIndexOrThrow(
                                                "unit"
                                        )
                                );

                        boolean ingredientFound = false;

                        for (Ingredient pantryIngredient :
                                pantryIngredients) {

                            if (!sameIngredient(
                                    pantryIngredient.getName(),
                                    requiredName
                            )) {

                                continue;
                            }

                            if (hasEnoughQuantity(
                                    pantryIngredient.getQuantity(),
                                    pantryIngredient.getUnit(),
                                    requiredQuantity,
                                    requiredUnit
                            )) {

                                ingredientFound = true;
                                break;
                            }
                        }

                        if (!ingredientFound) {

                            recipeMatches = false;
                            break;
                        }
                    }

                } finally {

                    cursor.close();
                }

                /*
                 * A recipe must have at least one required
                 * ingredient and every required ingredient
                 * must be available.
                 */
                if (recipeMatches &&
                        hasRequiredIngredients) {

                    matchingRecipes.add(recipe);
                }
            }

        } finally {

            databaseHelper.close();
        }

        return matchingRecipes;
    }

    private static boolean sameIngredient(
            String pantryName,
            String recipeName) {

        String pantry =
                normalizeIngredientName(
                        pantryName
                );

        String recipe =
                normalizeIngredientName(
                        recipeName
                );

        return pantry.equals(recipe);
    }

    private static String normalizeIngredientName(
            String name) {

        if (name == null) {
            return "";
        }

        String normalized =
                name.trim()
                        .toLowerCase(
                                Locale.getDefault()
                        );

        normalized =
                normalized.replaceAll(
                        "\\s+",
                        " "
                );

        /*
         * Handle common plural forms.
         */
        if (normalized.endsWith("ies") &&
                normalized.length() > 3) {

            normalized =
                    normalized.substring(
                            0,
                            normalized.length() - 3
                    ) + "y";

        } else if (normalized.endsWith("es") &&
                normalized.length() > 2) {

            normalized =
                    normalized.substring(
                            0,
                            normalized.length() - 2
                    );

        } else if (normalized.endsWith("s") &&
                normalized.length() > 1) {

            normalized =
                    normalized.substring(
                            0,
                            normalized.length() - 1
                    );
        }

        return normalized;
    }

    private static boolean hasEnoughQuantity(
            double pantryQuantity,
            String pantryUnit,
            double requiredQuantity,
            String requiredUnit) {

        if (pantryUnit == null ||
                requiredUnit == null) {

            return false;
        }

        double convertedPantryQuantity =
                convertToBaseUnit(
                        pantryQuantity,
                        pantryUnit
                );

        double convertedRequiredQuantity =
                convertToBaseUnit(
                        requiredQuantity,
                        requiredUnit
                );

        /*
         * If both units are recognised, compare them
         * using the same base unit.
         */
        if (convertedPantryQuantity >= 0 &&
                convertedRequiredQuantity >= 0) {

            String pantryType =
                    getUnitType(pantryUnit);

            String requiredType =
                    getUnitType(requiredUnit);

            /*
             * Do not compare unrelated units such as
             * kilograms with cans.
             */
            if (!pantryType.equals(requiredType)) {
                return false;
            }

            return convertedPantryQuantity >=
                    convertedRequiredQuantity;
        }

        /*
         * For custom units, only match when the unit
         * names are exactly the same.
         */
        return pantryUnit
                .trim()
                .equalsIgnoreCase(
                        requiredUnit.trim()
                )
                &&
                pantryQuantity >= requiredQuantity;
    }

    private static double convertToBaseUnit(
            double quantity,
            String unit) {

        if (unit == null) {
            return -1;
        }

        String normalizedUnit =
                unit.trim()
                        .toLowerCase(
                                Locale.getDefault()
                        );

        switch (normalizedUnit) {

            case "kg":
                return quantity * 1000;

            case "g":
                return quantity;

            case "l":
                return quantity * 1000;

            case "ml":
                return quantity;

            case "item":
            case "items":
                return quantity;

            case "slice":
            case "slices":
                return quantity;

            case "can":
            case "cans":
                return quantity;

            default:
                return -1;
        }
    }

    private static String getUnitType(
            String unit) {

        if (unit == null) {
            return "";
        }

        String normalizedUnit =
                unit.trim()
                        .toLowerCase(
                                Locale.getDefault()
                        );

        switch (normalizedUnit) {

            case "kg":
            case "g":
                return "weight";

            case "l":
            case "ml":
                return "volume";

            case "item":
            case "items":
                return "item";

            case "slice":
            case "slices":
                return "slice";

            case "can":
            case "cans":
                return "can";

            default:
                return normalizedUnit;
        }
    }
}