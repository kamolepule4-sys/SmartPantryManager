# Smart Pantry Manager Recipe Matching

## Purpose

The recipe matching system checks whether the ingredients and quantities in the user's pantry are enough to prepare a recipe.

## Strict Matching

The application uses strict matching rather than showing recipes that are only partially possible.

For a recipe to be suggested:

1. Every required ingredient must be present in the pantry.
2. The pantry quantity must be enough for the required quantity.
3. If one required ingredient is missing, the recipe is excluded.
4. If there is not enough of one required ingredient, the recipe is excluded.

## Example

If a recipe requires:

- 2 eggs
- 1 cup flour
- 1 cup milk

And the pantry contains:

- 2 eggs
- 1 cup flour
- 1 cup milk

The recipe can be suggested.

However, if the pantry contains no milk, the recipe is not suggested.

The application does not display the recipe as a partial match.

## Ingredient Name Matching

The matching system also handles simple differences in ingredient names.

For example, singular and plural forms can be treated as the same ingredient where appropriate.

## Unit Matching

Common unit differences are handled where possible so that equivalent quantities can be compared.

The purpose is to make the matching system practical while still following the strict requirement that the user must have enough of every required ingredient.

## Matching Process

The recipe matching process follows these steps:

1. Load the user's pantry ingredients from the SQLite database.
2. Load the stored recipes and their required ingredients.
3. Check each required ingredient against the pantry.
4. Compare the available quantity with the required quantity.
5. Continue checking until every required ingredient has been checked.
6. Add the recipe to the suggestions only if all requirements are satisfied.
7. If no recipes satisfy all requirements, the application informs the user that there are no matching recipes.