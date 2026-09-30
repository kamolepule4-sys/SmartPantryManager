package com.example.smartpantrymanager;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class RecipeManager {

    private static final ArrayList<Recipe> recipes =
            new ArrayList<>();

    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public static void loadRecipes(Context context) {

        recipes.clear();

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        Cursor cursor = null;

        try {

            cursor = databaseHelper.getAllRecipes();

            while (cursor.moveToNext()) {

                int id =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow("id")
                        );

                String name =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("name")
                        );

                String ingredients =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("ingredients")
                        );

                String preparationSteps =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        "preparationSteps"
                                )
                        );

                Recipe recipe =
                        new Recipe(
                                id,
                                name,
                                ingredients,
                                preparationSteps
                        );

                recipes.add(recipe);
            }

        } finally {

            if (cursor != null) {
                cursor.close();
            }

            databaseHelper.close();
        }
    }

    public static long addRecipe(
            Context context,
            Recipe recipe) {

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        try {

            return databaseHelper.addRecipe(
                    recipe.getName(),
                    recipe.getIngredients(),
                    recipe.getPreparationSteps()
            );

        } finally {

            databaseHelper.close();
        }
    }

    public static ArrayList<RecipeIngredient> getRecipeIngredients(
            Context context,
            int recipeId) {

        ArrayList<RecipeIngredient> ingredients =
                new ArrayList<>();

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        Cursor cursor = null;

        try {

            cursor =
                    databaseHelper.getRecipeIngredients(
                            recipeId
                    );

            while (cursor.moveToNext()) {

                int id =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow("id")
                        );

                int storedRecipeId =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow("recipeId")
                        );

                String ingredientName =
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

                String unit =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("unit")
                        );

                RecipeIngredient ingredient =
                        new RecipeIngredient(
                                id,
                                storedRecipeId,
                                ingredientName,
                                requiredQuantity,
                                unit
                        );

                ingredients.add(ingredient);
            }

        } finally {

            if (cursor != null) {
                cursor.close();
            }

            databaseHelper.close();
        }

        return ingredients;
    }
}