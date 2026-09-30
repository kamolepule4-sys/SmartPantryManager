package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddIngredientActivity extends AppCompatActivity {

    private EditText ingredientNameInput;
    private EditText quantityInput;
    private EditText unitInput;
    private EditText expiryDateInput;

    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_ingredient);

        ingredientNameInput =
                findViewById(R.id.ingredientNameInput);

        quantityInput =
                findViewById(R.id.quantityInput);

        unitInput =
                findViewById(R.id.unitInput);

        categorySpinner =
                findViewById(R.id.categorySpinner);

        expiryDateInput =
                findViewById(R.id.expiryDateInput);

        Button saveIngredientButton =
                findViewById(R.id.saveIngredientButton);

        Button cancelButton =
                findViewById(R.id.cancelButton);

        setupCategorySpinner();

        saveIngredientButton.setOnClickListener(v ->
                saveIngredient()
        );

        cancelButton.setOnClickListener(v ->
                finish()
        );
    }

    private void setupCategorySpinner() {

        String[] categories = {
                "Meat",
                "Dairy",
                "Vegetables",
                "Fruit",
                "Grains",
                "Canned",
                "Snacks",
                "Drinks",
                "Other"
        };

        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        categories
                );

        categoryAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        categorySpinner.setAdapter(
                categoryAdapter
        );
    }

    private void saveIngredient() {

        String ingredientName =
                ingredientNameInput
                        .getText()
                        .toString()
                        .trim();

        String quantityText =
                quantityInput
                        .getText()
                        .toString()
                        .trim();

        String unit =
                unitInput
                        .getText()
                        .toString()
                        .trim();

        String expiryDate =
                expiryDateInput
                        .getText()
                        .toString()
                        .trim();

        String category =
                categorySpinner
                        .getSelectedItem()
                        .toString();

        if (ingredientName.isEmpty()) {

            ingredientNameInput.setError(
                    "Enter an ingredient name"
            );

            ingredientNameInput.requestFocus();

            return;
        }

        if (quantityText.isEmpty()) {

            quantityInput.setError(
                    "Enter a quantity"
            );

            quantityInput.requestFocus();

            return;
        }

        if (unit.isEmpty()) {

            unitInput.setError(
                    "Enter a unit"
            );

            unitInput.requestFocus();

            return;
        }

        double quantity;

        try {

            quantity =
                    Double.parseDouble(quantityText);

        } catch (NumberFormatException e) {

            quantityInput.setError(
                    "Enter a valid number"
            );

            quantityInput.requestFocus();

            return;
        }

        if (quantity <= 0) {

            quantityInput.setError(
                    "Quantity must be greater than 0"
            );

            quantityInput.requestFocus();

            return;
        }

        Ingredient ingredient =
                new Ingredient(
                        ingredientName,
                        quantity,
                        unit,
                        expiryDate,
                        category
                );

        IngredientManager.addIngredient(
                this,
                ingredient
        );

        Toast.makeText(
                this,
                "Ingredient saved successfully!",
                Toast.LENGTH_SHORT
        ).show();

        Intent intent =
                new Intent(
                        this,
                        PantryActivity.class
                );

        startActivity(intent);

        finish();
    }
}