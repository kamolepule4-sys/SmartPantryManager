# Smart Pantry Manager Testing Checklist

## Pantry Management

The following functions should be demonstrated:

* Add a pantry ingredient
* View the ingredient in the pantry list
* Edit the ingredient
* Delete the ingredient

## Input Validation

The application should prevent invalid or incomplete ingredient information from being saved.

Required information should be checked before the ingredient is added or updated.

## Recipe Suggestions

The recipe matching system should be checked using pantry ingredients that:

* Match all required ingredients and quantities
* Are missing a required ingredient
* Do not have enough of a required ingredient

Only recipes that satisfy all required ingredients and quantities should appear in the main suggestions.

## Recipe Details

A selected recipe should display:

* Recipe name
* Required ingredients
* Required quantities
* Preparation steps

## Database Persistence

After adding pantry ingredients, the application should be closed and opened again.

The previously saved ingredients should still be available because they are stored in SQLite.

## Navigation

The following screens should be accessible through the application's navigation:

* Pantry List
* Add Ingredient
* Edit Ingredient
* Suggested Recipes
* Recipe Details
* Settings

## Final Demonstration

The final demonstration should show the main application functions, database persistence and strict recipe matching.
