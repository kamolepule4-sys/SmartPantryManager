package com.example.smartpantrymanager;

public class Recipe {

    private int id;
    private String name;
    private String ingredients;
    private String preparationSteps;

    public Recipe(
            int id,
            String name,
            String ingredients,
            String preparationSteps) {

        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.preparationSteps = preparationSteps;
    }

    public Recipe(
            String name,
            String ingredients,
            String preparationSteps) {

        this.name = name;
        this.ingredients = ingredients;
        this.preparationSteps = preparationSteps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparationSteps() {
        return preparationSteps;
    }

    public void setPreparationSteps(
            String preparationSteps) {

        this.preparationSteps = preparationSteps;
    }
}