package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_dashboard
        );


        Button openPantryButton =
                findViewById(
                        R.id.openPantryButton
                );

        Button openRecipesButton =
                findViewById(
                        R.id.openRecipesButton
                );

        Button openSettingsButton =
                findViewById(
                        R.id.openSettingsButton
                );


        openPantryButton.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    DashboardActivity.this,
                                    PantryActivity.class
                            );

                    startActivity(intent);
                }
        );


        openRecipesButton.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    DashboardActivity.this,
                                    SuggestedRecipesActivity.class
                            );

                    startActivity(intent);
                }
        );


        openSettingsButton.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    DashboardActivity.this,
                                    SettingsActivity.class
                            );

                    startActivity(intent);
                }
        );
    }
}