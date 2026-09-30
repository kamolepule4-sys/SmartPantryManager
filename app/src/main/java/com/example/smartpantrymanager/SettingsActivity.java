package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settingslayout);

        Button backToPantryButton =
                findViewById(R.id.backToPantryButton);

        backToPantryButton.setOnClickListener(v -> finish());
    }
}