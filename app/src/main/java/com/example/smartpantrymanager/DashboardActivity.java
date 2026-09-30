package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private TextView totalIngredientsText;
    private TextView lowStockText;
    private TextView expiringSoonText;
    private LinearLayout expiringIngredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        totalIngredientsText =
                findViewById(R.id.totalIngredientsText);

        lowStockText =
                findViewById(R.id.lowStockText);

        expiringSoonText =
                findViewById(R.id.expiringSoonText);

        expiringIngredientsList =
                findViewById(R.id.expiringIngredientsList);

        Button viewPantryButton =
                findViewById(R.id.viewPantryButton);

        Button addIngredientButton =
                findViewById(R.id.addIngredientButton);

        viewPantryButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            DashboardActivity.this,
                            PantryActivity.class
                    );

            startActivity(intent);
        });

        addIngredientButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            DashboardActivity.this,
                            AddIngredientActivity.class
                    );

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDashboardData();
    }

    private void loadDashboardData() {

        IngredientManager.loadIngredients(this);

        ArrayList<Ingredient> ingredients =
                IngredientManager.getIngredients();

        totalIngredientsText.setText(
                String.valueOf(ingredients.size())
        );

        int lowStockCount = 0;
        int expiringCount = 0;

        expiringIngredientsList.removeAllViews();

        for (Ingredient ingredient : ingredients) {

            if (ingredient.getQuantity() <= 2) {
                lowStockCount++;
            }

            if (isExpiringSoon(ingredient.getExpiryDate())) {

                expiringCount++;

                addExpiringIngredient(
                        ingredient
                );
            }
        }

        lowStockText.setText(
                String.valueOf(lowStockCount)
        );

        expiringSoonText.setText(
                String.valueOf(expiringCount)
        );

        if (expiringCount == 0) {

            TextView emptyMessage =
                    new TextView(this);

            emptyMessage.setText(
                    "No ingredients are expiring soon."
            );

            emptyMessage.setTextColor(
                    android.graphics.Color.DKGRAY
            );

            emptyMessage.setTextSize(15);

            expiringIngredientsList.addView(
                    emptyMessage
            );
        }
    }

    private boolean isExpiringSoon(String expiryDate) {

        if (expiryDate == null ||
                expiryDate.trim().isEmpty()) {

            return false;
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
                            expiryDate.trim()
                    );

            if (expiry == null) {
                return false;
            }

            Calendar today =
                    Calendar.getInstance();

            today.set(
                    Calendar.HOUR_OF_DAY,
                    0
            );

            today.set(
                    Calendar.MINUTE,
                    0
            );

            today.set(
                    Calendar.SECOND,
                    0
            );

            today.set(
                    Calendar.MILLISECOND,
                    0
            );

            Calendar sevenDaysFromNow =
                    (Calendar) today.clone();

            sevenDaysFromNow.add(
                    Calendar.DAY_OF_YEAR,
                    7
            );

            return !expiry.before(
                    today.getTime()
            ) && !expiry.after(
                    sevenDaysFromNow.getTime()
            );

        } catch (ParseException e) {

            return false;
        }
    }

    private void addExpiringIngredient(
            Ingredient ingredient) {

        TextView ingredientText =
                new TextView(this);

        String text =
                ingredient.getName()
                        + " — "
                        + ingredient.getExpiryDate();

        ingredientText.setText(text);

        ingredientText.setTextColor(
                android.graphics.Color.rgb(
                        32,
                        57,
                        43
                )
        );

        ingredientText.setTextSize(16);

        ingredientText.setPadding(
                0,
                10,
                0,
                10
        );

        expiringIngredientsList.addView(
                ingredientText
        );
    }
}