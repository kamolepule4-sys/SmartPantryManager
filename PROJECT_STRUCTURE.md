# Smart Pantry Manager Project Structure

## Android Activities

The application uses separate Activities for the main areas of the application.

### DashboardActivity

Provides the main starting screen and navigation to the application sections.

### PantryActivity

Displays the ingredients currently stored in the pantry.

It loads the data from SQLite and displays it using a RecyclerView.

### AddIngredientActivity

Allows the user to add a new ingredient to the pantry.

### EditIngredientActivity

Allows the user to update an existing pantry ingredient.

### SuggestedRecipesActivity

Displays recipes that match the ingredients and quantities available in the pantry.

### RecipeDetailActivity

Displays the ingredients and preparation steps for a selected recipe.

### SettingsActivity

Provides the Settings screen required by the application.

## Database Classes

### DatabaseHelper

Handles the SQLite database.

It creates the database tables and provides operations for storing and retrieving application data.

### IngredientManager

Handles pantry ingredient operations such as adding, loading, updating and deleting ingredients.

### RecipeManager

Handles retrieving recipe information from the database.

### RecipeDatabaseSeeder

Adds the initial recipe collection to the database.

## Recipe Matching

### RecipeMatcher

Contains the logic used to compare pantry ingredients with recipe requirements.

It checks both ingredient availability and required quantities before allowing a recipe to appear in the suggestions.

## Model Classes

### Ingredient

Represents a pantry ingredient.

### Recipe

Represents a recipe.

### RecipeIngredient

Represents an ingredient required by a recipe.

## Adapters

### IngredientAdapter

Displays pantry ingredients in the RecyclerView.

### RecipeAdapter

Displays matching recipes in the Suggested Recipes screen.

## Layout Files

XML layout files are used to define the user interface for the different screens and list items.

The layouts contain elements such as text fields, buttons, lists, spinners and other Android views.
