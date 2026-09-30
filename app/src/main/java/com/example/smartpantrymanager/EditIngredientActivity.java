package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditIngredientActivity extends AppCompatActivity {

    private int ingredientPosition;

    private EditText ingredientNameInput;
    private EditText quantityInput;
    private EditText unitInput;
    private EditText expiryDateInput;

    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_edit_ingredient
        );

        ingredientNameInput =
                findViewById(
                        R.id.editIngredientNameInput
                );

        quantityInput =
                findViewById(
                        R.id.editQuantityInput
                );

        unitInput =
                findViewById(
                        R.id.editUnitInput
                );

        categorySpinner =
                findViewById(
                        R.id.editCategorySpinner
                );

        expiryDateInput =
                findViewById(
                        R.id.editExpiryDateInput
                );

        Button updateIngredientButton =
                findViewById(
                        R.id.updateIngredientButton
                );

        Button cancelEditButton =
                findViewById(
                        R.id.cancelEditButton
                );

        setupCategorySpinner();

        ingredientPosition =
                getIntent().getIntExtra(
                        "ingredientPosition",
                        -1
                );

        IngredientManager.loadIngredients(
                this
        );

        if (ingredientPosition < 0 ||
                ingredientPosition >=
                        IngredientManager
                                .getIngredients()
                                .size()) {

            Toast.makeText(
                    this,
                    "Ingredient could not be found.",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

            return;
        }

        loadIngredientData();

        updateIngredientButton.setOnClickListener(
                v -> updateIngredient()
        );

        cancelEditButton.setOnClickListener(
                v -> finish()
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

    private void loadIngredientData() {

        Ingredient ingredient =
                IngredientManager
                        .getIngredients()
                        .get(ingredientPosition);

        ingredientNameInput.setText(
                ingredient.getName()
        );

        quantityInput.setText(
                String.valueOf(
                        ingredient.getQuantity()
                )
        );

        unitInput.setText(
                ingredient.getUnit()
        );

        expiryDateInput.setText(
                ingredient.getExpiryDate()
        );

        String existingCategory =
                ingredient.getCategory();

        if (existingCategory != null) {

            for (int i = 0;
                 i < categorySpinner.getCount();
                 i++) {

                if (categorySpinner
                        .getItemAtPosition(i)
                        .toString()
                        .equals(existingCategory)) {

                    categorySpinner.setSelection(i);

                    break;
                }
            }
        }
    }

    private void updateIngredient() {

        String name =
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

        if (name.isEmpty()) {

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
                    Double.parseDouble(
                            quantityText
                    );

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

        IngredientManager.updateIngredient(
                this,
                ingredientPosition,
                name,
                quantity,
                unit,
                expiryDate,
                category
        );

        Toast.makeText(
                this,
                "Ingredient updated successfully!",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }
}