package com.example.smartpantrymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter
        extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private ArrayList<Recipe> recipes;

    public RecipeAdapter(
            Context context,
            ArrayList<Recipe> recipes) {

        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(context)
                        .inflate(
                                R.layout.recipe_item,
                                parent,
                                false
                        );

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecipeViewHolder holder,
            int position) {

        Recipe recipe =
                recipes.get(position);

        holder.recipeNameText.setText(
                recipe.getName()
        );

        holder.recipeIngredientsText.setText(
                recipe.getIngredients()
        );

        holder.viewRecipeButton.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    context,
                                    RecipeDetailActivity.class
                            );

                    intent.putExtra(
                            "recipeId",
                            recipe.getId()
                    );

                    context.startActivity(intent);
                }
        );
    }

    @Override
    public int getItemCount() {

        return recipes.size();
    }

    public static class RecipeViewHolder
            extends RecyclerView.ViewHolder {

        TextView recipeNameText;
        TextView recipeIngredientsText;
        Button viewRecipeButton;

        public RecipeViewHolder(
                @NonNull View itemView) {

            super(itemView);

            recipeNameText =
                    itemView.findViewById(
                            R.id.recipeNameText
                    );

            recipeIngredientsText =
                    itemView.findViewById(
                            R.id.recipeIngredientsText
                    );

            viewRecipeButton =
                    itemView.findViewById(
                            R.id.viewRecipeButton
                    );
        }
    }
}