package com.example.smartpantrymanager;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class IngredientManager {

    private static final ArrayList<Ingredient> ingredients =
            new ArrayList<>();

    public static void addIngredient(
            Context context,
            Ingredient ingredient) {

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        databaseHelper.addIngredient(
                ingredient.getName(),
                ingredient.getQuantity(),
                ingredient.getUnit(),
                ingredient.getExpiryDate(),
                ingredient.getCategory()
        );

        databaseHelper.close();

        loadIngredients(context);
    }

    public static ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public static void loadIngredients(Context context) {

        ingredients.clear();

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        Cursor cursor =
                databaseHelper.getAllIngredients();

        try {

            while (cursor.moveToNext()) {

                int id =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow("id")
                        );

                String name =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("name")
                        );

                double quantity =
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow("quantity")
                        );

                String unit =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("unit")
                        );

                String expiryDate =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("expiryDate")
                        );

                String category =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("category")
                        );

                Ingredient ingredient =
                        new Ingredient(
                                id,
                                name,
                                quantity,
                                unit,
                                expiryDate,
                                category
                        );

                ingredients.add(ingredient);
            }

        } finally {

            cursor.close();
            databaseHelper.close();
        }
    }

    public static void deleteIngredient(
            Context context,
            int position) {

        if (position < 0 ||
                position >= ingredients.size()) {

            return;
        }

        Ingredient ingredient =
                ingredients.get(position);

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        databaseHelper.deleteIngredient(
                ingredient.getId()
        );

        databaseHelper.close();

        loadIngredients(context);
    }

    public static void updateIngredient(
            Context context,
            int position,
            String name,
            double quantity,
            String unit,
            String expiryDate,
            String category) {

        if (position < 0 ||
                position >= ingredients.size()) {

            return;
        }

        Ingredient ingredient =
                ingredients.get(position);

        DatabaseHelper databaseHelper =
                new DatabaseHelper(context);

        databaseHelper.updateIngredient(
                ingredient.getId(),
                name,
                quantity,
                unit,
                expiryDate,
                category
        );

        databaseHelper.close();

        loadIngredients(context);
    }

    public static Ingredient getIngredient(int position) {

        if (position < 0 ||
                position >= ingredients.size()) {

            return null;
        }

        return ingredients.get(position);
    }
}