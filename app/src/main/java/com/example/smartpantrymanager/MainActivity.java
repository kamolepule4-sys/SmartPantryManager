package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView pantryEmptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pantryEmptyText =
                findViewById(R.id.pantryEmptyText);

        Button addIngredientButton =
                findViewById(R.id.addIngredientButton);

        Button suggestedRecipesButton =
                findViewById(R.id.suggestedRecipesButton);

        Button settingsButton =
                findViewById(R.id.settingsButton);

        addIngredientButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AddIngredientActivity.class
                    );

            startActivity(intent);
        });

        suggestedRecipesButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SuggestedRecipesActivity.class
                    );

            startActivity(intent);
        });

        settingsButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        updatePantryMessage();
    }

    private void updatePantryMessage() {

        IngredientManager.loadIngredients(this);

        ArrayList<Ingredient> ingredients =
                IngredientManager.getIngredients();

        if (ingredients.isEmpty()) {

            pantryEmptyText.setText(
                    "Your pantry is empty.\n" +
                            "Add your first ingredient to get started."
            );

        } else {

            pantryEmptyText.setText(
                    "You currently have "
                            + ingredients.size()
                            + " ingredient(s) in your pantry."
            );
        }
    }
}