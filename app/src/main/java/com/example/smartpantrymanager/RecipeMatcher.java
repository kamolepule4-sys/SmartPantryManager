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


        for (Recipe recipe : recipes) {

            boolean recipeMatches = true;

            Cursor cursor =
                    databaseHelper.getRecipeIngredients(
                            recipe.getId()
                    );


            while (cursor.moveToNext()) {

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


                boolean ingredientFound =
                        false;


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


            cursor.close();


            if (recipeMatches) {

                matchingRecipes.add(recipe);
            }
        }


        databaseHelper.close();


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

        String normalized =
                name.trim()
                        .toLowerCase(
                                Locale.getDefault()
                        );


        if (normalized.endsWith("ies")) {

            normalized =
                    normalized.substring(
                            0,
                            normalized.length() - 3
                    ) + "y";

        } else if (normalized.endsWith("es")) {

            normalized =
                    normalized.substring(
                            0,
                            normalized.length() - 2
                    );

        } else if (normalized.endsWith("s")) {

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


        if (convertedPantryQuantity == -1
                || convertedRequiredQuantity == -1) {

            return pantryUnit
                    .trim()
                    .equalsIgnoreCase(
                            requiredUnit.trim()
                    )
                    && pantryQuantity >=
                    requiredQuantity;
        }


        return convertedPantryQuantity >=
                convertedRequiredQuantity;
    }


    private static double convertToBaseUnit(
            double quantity,
            String unit) {

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
}