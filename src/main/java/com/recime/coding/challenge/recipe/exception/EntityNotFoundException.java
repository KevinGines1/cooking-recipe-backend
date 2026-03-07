package com.recime.coding.challenge.recipe.exception;

public class EntityNotFoundException extends RuntimeException{
    public static String DIET_TYPE_NOT_FOUND = "Diet type not found";
    public static String INGREDIENT_NOT_FOUND = "Ingredient not found";
    public static String RECIPE_NOT_FOUND = "Recipe not found";

    public EntityNotFoundException(String message) {
        super(message);
    }
}
