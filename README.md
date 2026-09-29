# Smart Pantry Manager

## Description

Smart Pantry Manager is an Android application developed for Mobile App Development 700.

The application allows users to manage ingredients in their pantry and find recipes that can be made using the ingredients they currently have.

The application checks the ingredients and quantities available in the pantry before suggesting a recipe. A recipe is only suggested when all of its required ingredients and quantities are available.

## Main Features

* Add pantry ingredients
* View pantry ingredients
* Edit pantry ingredients
* Delete pantry ingredients
* Store ingredient information in a database
* Search pantry ingredients
* Filter ingredients by category
* Store recipes in a database
* Suggest recipes based on available pantry ingredients
* View recipe ingredients and preparation steps
* Settings screen

## Database

SQLite was used as the database for this application.

SQLite was chosen because it is built into Android and is suitable for storing structured application data locally. It also allows the application to keep pantry and recipe information available after the application is closed and reopened.

The database contains tables for pantry ingredients, recipes and recipe ingredients.

## Recipe Matching

The application uses strict recipe matching.

For a recipe to appear in the suggested recipes screen, every required ingredient must be available in the pantry in a sufficient quantity.

If even one required ingredient is missing or the available quantity is not enough, the recipe is not suggested.

The matching logic also handles common differences such as singular and plural ingredient names and common unit conversions.

## Technologies Used

* Java
* Android Studio
* SQLite
* XML
* RecyclerView

## Project Structure

The application contains screens for:

* Pantry List
* Add Ingredient
* Edit Ingredient
* Suggested Recipes
* Recipe Details
* Settings

## How to Run

1. Clone or download the repository.
2. Open the project in Android Studio.
3. Allow Android Studio to complete the Gradle sync.
4. Connect an Android device or use an Android emulator.
5. Run the application from Android Studio.

## Author

Developed as part of the Mobile App Development 700 assignment.
