# Smart Pantry Manager System Design

## Main Screens

The Smart Pantry Manager application contains the following main screens:

### Pantry List

The Pantry List displays the ingredients currently stored in the user's pantry.

The user can:

- View pantry ingredients
- Search for ingredients
- Filter ingredients by category
- Edit an ingredient
- Delete an ingredient
- Add a new ingredient
- Open the suggested recipes screen

### Add Ingredient

The Add Ingredient screen allows the user to enter a new pantry ingredient.

The user can enter:

- Ingredient name
- Quantity
- Unit
- Category
- Optional expiry date

Input validation is used before the ingredient is saved.

### Edit Ingredient

The Edit Ingredient screen allows the user to change the information of an existing pantry ingredient.

The updated information is saved to the SQLite database.

### Suggested Recipes

The Suggested Recipes screen displays recipes that can be prepared using the ingredients currently available in the pantry.

The matching system uses strict matching. A recipe is only displayed when all required ingredients are available in sufficient quantities.

### Recipe Details

The Recipe Details screen displays:

- Recipe name
- Required ingredients
- Required quantities
- Preparation method

### Settings

The Settings screen provides the application's basic settings area.

## Application Flow

The general application flow is:

Pantry List → Add/Edit Ingredient → Save to Database

Pantry List → Suggested Recipes → Recipe Details

The application uses Android Intents to move between screens.

## Data Flow

The user enters or changes pantry information through the application screens.

The information is stored in the SQLite database.

When the pantry list is opened, the stored information is loaded from the database and displayed using a RecyclerView and custom Adapter.

The recipe matching system reads the pantry ingredients and compares them with the required ingredients stored in the recipe database.

Matching recipes are then displayed to the user.