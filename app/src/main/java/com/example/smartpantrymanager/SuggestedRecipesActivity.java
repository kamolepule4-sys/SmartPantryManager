package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestedRecipesActivity
        extends AppCompatActivity {

    private RecyclerView recipeRecyclerView;
    private TextView recipeMessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_suggested_recipes
        );

        recipeRecyclerView =
                findViewById(
                        R.id.recipeRecyclerView
                );

        recipeMessageText =
                findViewById(
                        R.id.recipeMessageText
                );


        recipeRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );


        ArrayList<Recipe> matchingRecipes =
                RecipeMatcher.getMatchingRecipes(
                        this
                );


        if (matchingRecipes.isEmpty()) {

            recipeMessageText.setText(
                    "No recipes can be made with your current pantry ingredients."
            );

        } else {

            recipeMessageText.setText(
                    matchingRecipes.size()
                            + " recipe(s) can be made with your pantry."
            );
        }


        RecipeAdapter adapter =
                new RecipeAdapter(
                        this,
                        matchingRecipes
                );


        recipeRecyclerView.setAdapter(
                adapter
        );
    }
}