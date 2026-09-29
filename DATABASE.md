# Smart Pantry Manager Database

## Database Choice

The application uses SQLite as its database.

SQLite was chosen because it is built into Android and is suitable for storing structured data locally without requiring an external database server.

## Database Tables

The database contains three main tables:

### Ingredients

This table stores the ingredients currently available in the user's pantry.

Fields include:

- ID
- Name
- Quantity
- Unit
- Expiry Date
- Category

### Recipes

This table stores the recipes available in the application.

Fields include:

- ID
- Recipe Name
- Preparation Steps

The application is seeded with 20 recipes.

### Recipe Ingredients

This table connects recipes to the ingredients they require.

Fields include:

- ID
- Recipe ID
- Ingredient Name
- Required Quantity
- Unit

## Relationship

A recipe can require multiple ingredients, and an ingredient can be required by multiple recipes.

The `recipe_ingredients` table connects the recipes and their required ingredients.

## CRUD Operations

The application supports CRUD operations for pantry ingredients:

- Create – Add a new pantry ingredient
- Read – View pantry ingredients
- Update – Edit an existing ingredient
- Delete – Remove an ingredient

The information is stored in SQLite so that pantry data remains available when the application is closed and opened again.

## Recipe Matching

The recipe matching system checks the ingredients and quantities in the pantry against the ingredients required by each recipe.

A recipe is only suggested when all of its required ingredients are available in sufficient quantities.

If one required ingredient is missing or there is not enough of it, the recipe is excluded from the suggested recipes.