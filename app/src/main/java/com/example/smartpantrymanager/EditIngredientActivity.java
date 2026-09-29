package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditIngredientActivity extends AppCompatActivity {

    private int ingredientPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredient);


        EditText ingredientNameInput =
                findViewById(
                        R.id.editIngredientNameInput
                );


        EditText quantityInput =
                findViewById(
                        R.id.editQuantityInput
                );


        EditText unitInput =
                findViewById(
                        R.id.editUnitInput
                );


        Spinner categorySpinner =
                findViewById(
                        R.id.editCategorySpinner
                );


        EditText expiryDateInput =
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


        ingredientPosition =
                getIntent().getIntExtra(
                        "ingredientPosition",
                        -1
                );


        if (ingredientPosition == -1
                || ingredientPosition >=
                IngredientManager
                        .getIngredients()
                        .size()) {

            finish();
            return;
        }


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


        for (int i = 0;
             i < categories.length;
             i++) {

            if (categories[i].equals(
                    existingCategory
            )) {

                categorySpinner.setSelection(i);

                break;
            }
        }


        updateIngredientButton.setOnClickListener(v -> {

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


            String category =
                    categorySpinner
                            .getSelectedItem()
                            .toString();


            String expiryDate =
                    expiryDateInput
                            .getText()
                            .toString()
                            .trim();


            if (name.isEmpty()
                    || quantityText.isEmpty()
                    || unit.isEmpty()) {

                Toast.makeText(
                        EditIngredientActivity.this,
                        "Please fill in the ingredient, quantity and unit.",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            try {

                double quantity =
                        Double.parseDouble(
                                quantityText
                        );


                if (quantity <= 0) {

                    Toast.makeText(
                            EditIngredientActivity.this,
                            "Quantity must be greater than 0.",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }


                IngredientManager.updateIngredient(
                        EditIngredientActivity.this,
                        ingredientPosition,
                        name,
                        quantity,
                        unit,
                        expiryDate,
                        category
                );


                Toast.makeText(
                        EditIngredientActivity.this,
                        "Ingredient updated successfully!",
                        Toast.LENGTH_SHORT
                ).show();


                Intent intent =
                        new Intent(
                                EditIngredientActivity.this,
                                PantryActivity.class
                        );


                startActivity(intent);

                finish();

            } catch (NumberFormatException e) {

                Toast.makeText(
                        EditIngredientActivity.this,
                        "Please enter a valid quantity.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        cancelEditButton.setOnClickListener(
                v -> finish()
        );
    }
}