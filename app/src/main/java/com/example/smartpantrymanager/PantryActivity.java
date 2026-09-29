package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PantryActivity extends AppCompatActivity {

    private LinearLayout ingredientList;
    private Button addIngredientButton;
    private Button viewSuggestedRecipesButton;
    private EditText searchInput;
    private Spinner categoryFilterSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_pantry
        );


        ingredientList =
                findViewById(
                        R.id.ingredientList
                );

        addIngredientButton =
                findViewById(
                        R.id.addIngredientButton
                );

        viewSuggestedRecipesButton =
                findViewById(
                        R.id.viewSuggestedRecipesButton
                );

        searchInput =
                findViewById(
                        R.id.searchInput
                );

        categoryFilterSpinner =
                findViewById(
                        R.id.categoryFilterSpinner
                );


        RecipeDatabaseSeeder.seedRecipes(
                this
        );


        IngredientManager.loadIngredients(
                this
        );


        String[] categories = {
                "All Categories",
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


        categoryFilterSpinner.setAdapter(
                categoryAdapter
        );


        addIngredientButton.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    PantryActivity.this,
                                    AddIngredientActivity.class
                            );

                    startActivity(intent);
                }
        );


        viewSuggestedRecipesButton.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    PantryActivity.this,
                                    SuggestedRecipesActivity.class
                            );

                    startActivity(intent);
                }
        );


        searchInput.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after) {
                    }


                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count) {

                        displayIngredients();
                    }


                    @Override
                    public void afterTextChanged(
                            Editable s) {
                    }
                }
        );


        categoryFilterSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        displayIngredients();
                    }


                    @Override
                    public void onNothingSelected(
                            AdapterView<?> parent) {
                    }
                }
        );


        displayIngredients();
    }


    private void displayIngredients() {

        ingredientList.removeAllViews();


        ArrayList<Ingredient> ingredients =
                IngredientManager.getIngredients();


        LayoutInflater inflater =
                LayoutInflater.from(this);


        String searchText =
                searchInput
                        .getText()
                        .toString()
                        .trim()
                        .toLowerCase(
                                Locale.getDefault()
                        );


        String selectedCategory =
                categoryFilterSpinner
                        .getSelectedItem()
                        .toString();


        boolean foundIngredient = false;


        for (int i = 0;
             i < ingredients.size();
             i++) {

            Ingredient ingredient =
                    ingredients.get(i);


            String ingredientName =
                    ingredient.getName()
                            .toLowerCase(
                                    Locale.getDefault()
                            );


            String ingredientCategory =
                    ingredient.getCategory();


            boolean matchesSearch =
                    ingredientName.contains(
                            searchText
                    );


            boolean matchesCategory =
                    selectedCategory.equals(
                            "All Categories"
                    )
                            || ingredientCategory.equals(
                            selectedCategory
                    );


            if (!matchesSearch ||
                    !matchesCategory) {

                continue;
            }


            foundIngredient = true;


            View ingredientView =
                    inflater.inflate(
                            R.layout.ingredient_item,
                            ingredientList,
                            false
                    );


            TextView ingredientNameText =
                    ingredientView.findViewById(
                            R.id.ingredientNameText
                    );


            TextView ingredientCategoryText =
                    ingredientView.findViewById(
                            R.id.ingredientCategoryText
                    );


            TextView ingredientQuantityText =
                    ingredientView.findViewById(
                            R.id.ingredientQuantityText
                    );


            TextView ingredientExpiryText =
                    ingredientView.findViewById(
                            R.id.ingredientExpiryText
                    );


            Button editIngredientButton =
                    ingredientView.findViewById(
                            R.id.editIngredientButton
                    );


            Button deleteIngredientButton =
                    ingredientView.findViewById(
                            R.id.deleteIngredientButton
                    );


            ingredientNameText.setText(
                    ingredient.getName()
            );


            ingredientCategoryText.setText(
                    "Category: "
                            + ingredient.getCategory()
            );


            ingredientQuantityText.setText(
                    "Quantity: "
                            + ingredient.getQuantity()
                            + " "
                            + ingredient.getUnit()
            );


            setExpiryMessage(
                    ingredientExpiryText,
                    ingredient.getExpiryDate()
            );


            int position = i;


            editIngredientButton.setOnClickListener(
                    v -> {

                        Intent intent =
                                new Intent(
                                        PantryActivity.this,
                                        EditIngredientActivity.class
                                );


                        intent.putExtra(
                                "ingredientPosition",
                                position
                        );


                        startActivity(intent);
                    }
            );


            deleteIngredientButton.setOnClickListener(
                    v -> {

                        new AlertDialog.Builder(
                                PantryActivity.this
                        )
                                .setTitle(
                                        "Delete ingredient?"
                                )
                                .setMessage(
                                        "Are you sure you want to delete "
                                                + ingredient.getName()
                                                + "?"
                                )
                                .setPositiveButton(
                                        "Delete",
                                        (dialog, which) -> {

                                            IngredientManager
                                                    .deleteIngredient(
                                                            PantryActivity.this,
                                                            position
                                                    );


                                            Toast.makeText(
                                                    PantryActivity.this,
                                                    "Ingredient deleted.",
                                                    Toast.LENGTH_SHORT
                                            ).show();


                                            displayIngredients();
                                        }
                                )
                                .setNegativeButton(
                                        "Cancel",
                                        null
                                )
                                .show();
                    }
            );


            ingredientList.addView(
                    ingredientView
            );
        }


        if (!foundIngredient) {

            TextView emptyMessage =
                    new TextView(this);


            if (ingredients.isEmpty()) {

                emptyMessage.setText(
                        "Your pantry is empty."
                );

            } else {

                emptyMessage.setText(
                        "No ingredients found."
                );
            }


            emptyMessage.setTextSize(18);


            emptyMessage.setTextColor(
                    getResources().getColor(
                            android.R.color.darker_gray
                    )
            );


            ingredientList.addView(
                    emptyMessage
            );
        }
    }


    private void setExpiryMessage(
            TextView expiryText,
            String expiryDate) {

        if (expiryDate == null ||
                expiryDate.isEmpty()) {

            expiryText.setText(
                    "No expiry date"
            );

            return;
        }


        SimpleDateFormat dateFormat =
                new SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                );


        dateFormat.setLenient(false);


        try {

            Date expiry =
                    dateFormat.parse(
                            expiryDate
                    );


            Date today =
                    new Date();


            long difference =
                    expiry.getTime()
                            - today.getTime();


            long daysUntilExpiry =
                    TimeUnit.MILLISECONDS.toDays(
                            difference
                    );


            if (daysUntilExpiry < 0) {

                expiryText.setText(
                        "EXPIRED: "
                                + expiryDate
                );

            } else if (daysUntilExpiry <= 3) {

                expiryText.setText(
                        "EXPIRING SOON: "
                                + expiryDate
                );

            } else {

                expiryText.setText(
                        "Expiry: "
                                + expiryDate
                );
            }


        } catch (ParseException e) {

            expiryText.setText(
                    "Expiry: "
                            + expiryDate
            );
        }
    }


    @Override
    protected void onResume() {

        super.onResume();


        if (searchInput != null &&
                categoryFilterSpinner != null) {

            IngredientManager.loadIngredients(
                    this
            );


            displayIngredients();
        }
    }
}