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


    public static void loadRecipes(
            Context context) {

        recipes.clear();


        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);


        Cursor cursor =
                databaseHelper.getAllRecipes();


        while (cursor.moveToNext()) {

            int id =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    "id"
                            )
                    );


            String name =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "name"
                            )
                    );


            String ingredients =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "ingredients"
                            )
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


        cursor.close();

        databaseHelper.close();
    }


    public static long addRecipe(
            Context context,
            Recipe recipe) {

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);


        long recipeId =
                databaseHelper.addRecipe(
                        recipe.getName(),
                        recipe.getIngredients(),
                        recipe.getPreparationSteps()
                );


        databaseHelper.close();


        return recipeId;
    }
}