package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView recipeDetailNameText;
    private TextView recipeDetailIngredientsText;
    private TextView recipeDetailStepsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_detail);

        recipeDetailNameText =
                findViewById(R.id.recipeDetailNameText);

        recipeDetailIngredientsText =
                findViewById(R.id.recipeDetailIngredientsText);

        recipeDetailStepsText =
                findViewById(R.id.recipeDetailStepsText);

        Button backToRecipesButton =
                findViewById(R.id.backToRecipesButton);

        backToRecipesButton.setOnClickListener(v -> finish());

        loadRecipe();
    }

    private void loadRecipe() {

        int recipeId =
                getIntent().getIntExtra("recipeId", -1);

        if (recipeId == -1) {

            Toast.makeText(
                    this,
                    "Recipe could not be found.",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        RecipeManager.loadRecipes(this);

        ArrayList<Recipe> recipes =
                RecipeManager.getRecipes();

        Recipe selectedRecipe = null;

        for (Recipe recipe : recipes) {

            if (recipe.getId() == recipeId) {
                selectedRecipe = recipe;
                break;
            }
        }

        if (selectedRecipe == null) {

            Toast.makeText(
                    this,
                    "Recipe could not be found.",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        recipeDetailNameText.setText(
                selectedRecipe.getName()
        );

        recipeDetailIngredientsText.setText(
                selectedRecipe.getIngredients()
        );

        recipeDetailStepsText.setText(
                selectedRecipe.getPreparationSteps()
        );
    }
}