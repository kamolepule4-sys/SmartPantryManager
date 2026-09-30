package com.example.smartpantrymanager;

import android.content.Context;
import android.database.Cursor;

public class RecipeDatabaseSeeder {

    public static void seedRecipes(Context context) {

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        Cursor cursor = null;

        try {

            cursor =
                    databaseHelper.getAllRecipes();

            if (cursor.moveToFirst()) {
                return;
            }

        } finally {

            if (cursor != null) {
                cursor.close();
            }
        }

        addRecipe(
                databaseHelper,
                "Chicken Pasta",
                "Cook the pasta. Cook the chicken in a pan. Add the onion and tomato. Mix everything with the cooked pasta.",
                new String[]{"Chicken", "Pasta", "Tomato", "Onion"},
                new double[]{1, 500, 2, 1},
                new String[]{"kg", "g", "items", "items"}
        );

        addRecipe(
                databaseHelper,
                "Egg Sandwich",
                "Cook the eggs. Place the eggs on the bread. Add cheese and close the sandwich.",
                new String[]{"Eggs", "Bread", "Cheese"},
                new double[]{2, 2, 50},
                new String[]{"items", "slices", "g"}
        );

        addRecipe(
                databaseHelper,
                "Cheese Omelette",
                "Beat the eggs. Cook them in a pan. Add cheese and onion. Fold the omelette and serve.",
                new String[]{"Eggs", "Cheese", "Onion"},
                new double[]{3, 50, 1},
                new String[]{"items", "g", "items"}
        );

        addRecipe(
                databaseHelper,
                "Chicken Rice",
                "Cook the rice. Cook the chicken in a pan. Add onion and cook until soft. Serve the chicken with the rice.",
                new String[]{"Chicken", "Rice", "Onion"},
                new double[]{1, 500, 1},
                new String[]{"kg", "g", "items"}
        );

        addRecipe(
                databaseHelper,
                "Tomato Pasta",
                "Cook the pasta. Cook the tomato and onion in a pan. Mix the sauce with the pasta.",
                new String[]{"Pasta", "Tomato", "Onion"},
                new double[]{500, 2, 1},
                new String[]{"g", "items", "items"}
        );

        addRecipe(
                databaseHelper,
                "Chicken Sandwich",
                "Cook the chicken. Place the chicken on bread. Add cheese and serve.",
                new String[]{"Chicken", "Bread", "Cheese"},
                new double[]{500, 2, 50},
                new String[]{"g", "slices", "g"}
        );

        addRecipe(
                databaseHelper,
                "Egg Fried Rice",
                "Cook the rice. Scramble the eggs in a pan. Add rice and onion. Stir and cook together.",
                new String[]{"Eggs", "Rice", "Onion"},
                new double[]{2, 500, 1},
                new String[]{"items", "g", "items"}
        );

        addRecipe(
                databaseHelper,
                "Cheese Toast",
                "Place cheese and tomato on bread. Toast until the cheese melts.",
                new String[]{"Bread", "Cheese", "Tomato"},
                new double[]{2, 50, 1},
                new String[]{"slices", "g", "items"}
        );

        addRecipe(
                databaseHelper,
                "Chicken Salad",
                "Cook the chicken and allow it to cool. Chop the lettuce and tomato. Mix everything together.",
                new String[]{"Chicken", "Lettuce", "Tomato"},
                new double[]{500, 100, 2},
                new String[]{"g", "g", "items"}
        );

        addRecipe(
                databaseHelper,
                "Vegetable Rice",
                "Cook the rice. Chop the carrot and onion. Cook the vegetables and mix them with the rice.",
                new String[]{"Rice", "Carrot", "Onion"},
                new double[]{500, 2, 1},
                new String[]{"g", "items", "items"}
        );

        addRecipe(
                databaseHelper,
                "Tuna Sandwich",
                "Drain the tuna. Place tuna and tomato on bread. Close the sandwich and serve.",
                new String[]{"Tuna", "Bread", "Tomato"},
                new double[]{1, 2, 1},
                new String[]{"can", "slices", "items"}
        );

        addRecipe(
                databaseHelper,
                "Chicken Wrap",
                "Cook the chicken. Place chicken and lettuce inside the tortilla. Roll the tortilla and serve.",
                new String[]{"Chicken", "Tortilla", "Lettuce"},
                new double[]{500, 2, 100},
                new String[]{"g", "items", "g"}
        );

        addRecipe(
                databaseHelper,
                "Tomato Omelette",
                "Beat the eggs. Cook the tomato and onion. Add the eggs and cook until set.",
                new String[]{"Eggs", "Tomato", "Onion"},
                new double[]{3, 2, 1},
                new String[]{"items", "items", "items"}
        );

        addRecipe(
                databaseHelper,
                "Rice and Beans",
                "Cook the rice. Heat the beans and tomato together. Serve the beans with the rice.",
                new String[]{"Rice", "Beans", "Tomato"},
                new double[]{500, 1, 2},
                new String[]{"g", "can", "items"}
        );

        addRecipe(
                databaseHelper,
                "Chicken and Vegetables",
                "Cook the chicken. Chop the carrot and onion. Add the vegetables and cook until tender.",
                new String[]{"Chicken", "Carrot", "Onion"},
                new double[]{500, 2, 1},
                new String[]{"g", "items", "items"}
        );

        addRecipe(
                databaseHelper,
                "Cheese Pasta",
                "Cook the pasta. Heat the milk and add cheese. Mix the cheese sauce with the pasta.",
                new String[]{"Pasta", "Cheese", "Milk"},
                new double[]{500, 100, 250},
                new String[]{"g", "g", "ml"}
        );

        addRecipe(
                databaseHelper,
                "French Toast",
                "Beat the eggs and milk together. Dip the bread into the mixture. Cook both sides in a pan.",
                new String[]{"Bread", "Eggs", "Milk"},
                new double[]{2, 2, 100},
                new String[]{"slices", "items", "ml"}
        );

        addRecipe(
                databaseHelper,
                "Chicken Tomato Rice",
                "Cook the rice. Cook the chicken. Add tomato and cook together. Serve with the rice.",
                new String[]{"Chicken", "Rice", "Tomato"},
                new double[]{500, 500, 2},
                new String[]{"g", "g", "items"}
        );

        addRecipe(
                databaseHelper,
                "Vegetable Omelette",
                "Beat the eggs. Cook the carrot and onion. Add the eggs and cook until ready.",
                new String[]{"Eggs", "Carrot", "Onion"},
                new double[]{3, 2, 1},
                new String[]{"items", "items", "items"}
        );

        addRecipe(
                databaseHelper,
                "Tuna Pasta",
                "Cook the pasta. Heat the tuna and tomato together. Mix with the cooked pasta.",
                new String[]{"Tuna", "Pasta", "Tomato"},
                new double[]{1, 500, 2},
                new String[]{"can", "g", "items"}
        );

        databaseHelper.close();
    }

    private static void addRecipe(
            DatabaseHelper databaseHelper,
            String name,
            String preparationSteps,
            String[] ingredientNames,
            double[] quantities,
            String[] units) {

        if (ingredientNames.length != quantities.length ||
                ingredientNames.length != units.length) {

            return;
        }

        long recipeId =
                databaseHelper.addRecipe(
                        name,
                        createIngredientText(
                                ingredientNames
                        ),
                        preparationSteps
                );

        if (recipeId == -1) {
            return;
        }

        for (int i = 0;
             i < ingredientNames.length;
             i++) {

            databaseHelper.addRecipeIngredient(
                    (int) recipeId,
                    ingredientNames[i],
                    quantities[i],
                    units[i]
            );
        }
    }

    private static String createIngredientText(
            String[] ingredientNames) {

        StringBuilder ingredients =
                new StringBuilder();

        for (int i = 0;
             i < ingredientNames.length;
             i++) {

            ingredients.append(
                    ingredientNames[i]
            );

            if (i < ingredientNames.length - 1) {
                ingredients.append(", ");
            }
        }

        return ingredients.toString();
    }
}