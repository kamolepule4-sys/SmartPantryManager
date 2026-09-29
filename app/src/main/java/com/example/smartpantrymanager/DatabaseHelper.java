package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME =
            "SmartPantry.db";

    private static final int DATABASE_VERSION = 2;


    public DatabaseHelper(Context context) {

        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createIngredientsTable =
                "CREATE TABLE ingredients (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "quantity REAL NOT NULL," +
                        "unit TEXT NOT NULL," +
                        "expiryDate TEXT," +
                        "category TEXT" +
                        ")";

        db.execSQL(createIngredientsTable);


        String createRecipesTable =
                "CREATE TABLE recipes (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "ingredients TEXT NOT NULL," +
                        "preparationSteps TEXT NOT NULL" +
                        ")";

        db.execSQL(createRecipesTable);


        String createRecipeIngredientsTable =
                "CREATE TABLE recipe_ingredients (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "recipeId INTEGER NOT NULL," +
                        "ingredientName TEXT NOT NULL," +
                        "requiredQuantity REAL NOT NULL," +
                        "unit TEXT NOT NULL" +
                        ")";

        db.execSQL(createRecipeIngredientsTable);
    }


    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL(
                "DROP TABLE IF EXISTS recipe_ingredients"
        );

        db.execSQL(
                "DROP TABLE IF EXISTS recipes"
        );

        db.execSQL(
                "DROP TABLE IF EXISTS ingredients"
        );

        onCreate(db);
    }


    // =========================
    // INGREDIENT DATABASE
    // =========================

    public long addIngredient(
            String name,
            double quantity,
            String unit,
            String expiryDate,
            String category) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("name", name);
        values.put("quantity", quantity);
        values.put("unit", unit);
        values.put("expiryDate", expiryDate);
        values.put("category", category);

        return db.insert(
                "ingredients",
                null,
                values
        );
    }


    public Cursor getAllIngredients() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.query(
                "ingredients",
                null,
                null,
                null,
                null,
                null,
                "id ASC"
        );
    }


    public int updateIngredient(
            int id,
            String name,
            double quantity,
            String unit,
            String expiryDate,
            String category) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("name", name);
        values.put("quantity", quantity);
        values.put("unit", unit);
        values.put("expiryDate", expiryDate);
        values.put("category", category);

        return db.update(
                "ingredients",
                values,
                "id = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }


    public int deleteIngredient(int id) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        return db.delete(
                "ingredients",
                "id = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }


    // =========================
    // RECIPE DATABASE
    // =========================

    public long addRecipe(
            String name,
            String ingredients,
            String preparationSteps) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                "name",
                name
        );

        values.put(
                "ingredients",
                ingredients
        );

        values.put(
                "preparationSteps",
                preparationSteps
        );

        return db.insert(
                "recipes",
                null,
                values
        );
    }


    public Cursor getAllRecipes() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.query(
                "recipes",
                null,
                null,
                null,
                null,
                null,
                "id ASC"
        );
    }


    public long addRecipeIngredient(
            int recipeId,
            String ingredientName,
            double requiredQuantity,
            String unit) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                "recipeId",
                recipeId
        );

        values.put(
                "ingredientName",
                ingredientName
        );

        values.put(
                "requiredQuantity",
                requiredQuantity
        );

        values.put(
                "unit",
                unit
        );

        return db.insert(
                "recipe_ingredients",
                null,
                values
        );
    }


    public Cursor getRecipeIngredients(
            int recipeId) {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.query(
                "recipe_ingredients",
                null,
                "recipeId = ?",
                new String[]{
                        String.valueOf(recipeId)
                },
                null,
                null,
                "id ASC"
        );
    }
}