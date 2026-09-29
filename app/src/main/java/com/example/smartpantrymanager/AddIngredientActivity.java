package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ingredient);


        EditText ingredientNameInput =
                findViewById(R.id.ingredientNameInput);


        EditText quantityInput =
                findViewById(R.id.quantityInput);


        EditText unitInput =
                findViewById(R.id.unitInput);


        Spinner categorySpinner =
                findViewById(R.id.categorySpinner);


        EditText expiryDateInput =
                findViewById(R.id.expiryDateInput);


        Button saveIngredientButton =
                findViewById(R.id.saveIngredientButton);


        Button cancelButton =
                findViewById(R.id.cancelButton);


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


        saveIngredientButton.setOnClickListener(v -> {

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


            String category =
                    categorySpinner
                            .getSelectedItem()
                            .toString();


            String expiryDate =
                    expiryDateInput
                            .getText()
                            .toString()
                            .trim();


            if (ingredientName.isEmpty()
                    || quantityText.isEmpty()
                    || unit.isEmpty()) {

                Toast.makeText(
                        AddIngredientActivity.this,
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
                            AddIngredientActivity.this,
                            "Quantity must be greater than 0.",
                            Toast.LENGTH_SHORT
                    ).show();

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
                        AddIngredientActivity.this,
                        ingredient
                );


                Toast.makeText(
                        AddIngredientActivity.this,
                        "Ingredient saved successfully!",
                        Toast.LENGTH_SHORT
                ).show();


                Intent intent =
                        new Intent(
                                AddIngredientActivity.this,
                                PantryActivity.class
                        );


                startActivity(intent);

                finish();

            } catch (NumberFormatException e) {

                Toast.makeText(
                        AddIngredientActivity.this,
                        "Please enter a valid quantity.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        cancelButton.setOnClickListener(v -> finish());
    }
}