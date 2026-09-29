# Smart Pantry Manager CRUD Operations

## Overview

The Smart Pantry Manager allows the user to manage pantry ingredients using the four basic CRUD operations.

CRUD stands for Create, Read, Update and Delete.

## Create

The Create operation is used when the user adds a new ingredient to the pantry.

The user enters the ingredient name, quantity, unit, category and an optional expiry date.

The information is then saved to the SQLite database.

## Read

The Read operation is used to display the ingredients stored in the pantry.

The application loads the ingredient information from the SQLite database and displays it in a RecyclerView.

A custom Adapter is used to display each ingredient in the pantry list.

## Update

The Update operation is used when the user edits an existing pantry ingredient.

The selected ingredient is loaded into the Edit Ingredient screen.

After the user changes the information, the updated data is saved back to the database.

## Delete

The Delete operation allows the user to remove an ingredient from the pantry.

When an ingredient is deleted, it is removed from the SQLite database and the pantry list is refreshed.

## Data Persistence

The pantry information is stored in SQLite rather than only being kept temporarily in memory.

This allows the stored ingredients to remain available when the application is closed and opened again.
