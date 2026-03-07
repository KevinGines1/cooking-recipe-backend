package com.recime.coding.challenge.recipe.services;

import com.recime.coding.challenge.recipe.models.dto.DietTypeDto;
import com.recime.coding.challenge.recipe.models.dto.IngredientDto;
import com.recime.coding.challenge.recipe.models.dto.RecipeDto;
import com.recime.coding.challenge.recipe.models.entities.DietType;
import com.recime.coding.challenge.recipe.models.entities.Ingredient;
import com.recime.coding.challenge.recipe.models.entities.Recipe;
import com.recime.coding.challenge.recipe.repositories.DietTypeRepository;
import com.recime.coding.challenge.recipe.repositories.IngredientRepository;
import com.recime.coding.challenge.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("recipeService")
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final DietTypeRepository dietTypeRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, DietTypeRepository dietTypeRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.dietTypeRepository = dietTypeRepository;
    }

    public RecipeDto createRecipe(RecipeDto recipe) {
        saveNewIngredients(recipe);
        saveNewDietTypes(recipe);

        Recipe newRecipe = Recipe.fromDto(recipe);
        newRecipe.setCreatedDate(Instant.now());
        Recipe savedRecipe = recipeRepository.save(newRecipe);

        return RecipeDto.toDto(savedRecipe);
    }

    public RecipeDto findRecipeById(UUID recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (recipe.isEmpty()) {
            // TODO: Recipe not found 404
            throw new RuntimeException();
        }

        return RecipeDto.toDto(recipe.get());
    }

    public RecipeDto updateRecipe(UUID recipeId, RecipeDto recipeDto) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (recipe.isEmpty()) {
            // TODO: Recipe not found 404
            throw new RuntimeException();
        }

        Recipe updatedRecipe = recipeRepository.save(setUpdatedFields(recipeDto, recipe.get()));
        return RecipeDto.toDto(updatedRecipe);
    }

    public void deleteRecipe(UUID recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (recipe.isEmpty()) {
            // TODO: Recipe not found 404
            throw new RuntimeException();
        }

        recipeRepository.deleteById(recipeId);
    }

    private void saveNewIngredients(RecipeDto recipe) {
        List<IngredientDto> processedIngredients = new ArrayList<>();

        if (recipe.getIngredients() != null) {
            for (IngredientDto ing : recipe.getIngredients()) {
                Ingredient existing = ingredientRepository.findByName(ing.getName())
                        .orElseGet(
                                () -> {
                                    Ingredient newIngredient = Ingredient.fromDto(ing);
                                    newIngredient.setCreatedDate(Instant.now());
                                    return ingredientRepository.save(newIngredient);
                                }
                        );
                processedIngredients.add(IngredientDto.toDto(existing));
            }
        }
        recipe.setIngredients(processedIngredients);
    }

    private void saveNewDietTypes(RecipeDto recipe) {
        List<DietTypeDto> processedDietTypes = new ArrayList<>();

        if (recipe.getDietTypes() != null) {
            for (DietTypeDto dt : recipe.getDietTypes()) {
                DietType existing = dietTypeRepository.findByName(dt.getName())
                        .orElseGet(
                                () -> {
                                    DietType newDietType = DietType.fromDto(dt);
                                    newDietType.setCreatedDate(Instant.now());
                                    return dietTypeRepository.save(newDietType);
                                }
                        );
                processedDietTypes.add(DietTypeDto.toDto(existing));
            }
        }
        recipe.setDietTypes(processedDietTypes);
    }

    private Recipe setUpdatedFields(RecipeDto recipeDto, Recipe recipe) {
        if (null != recipeDto.getTitle()) {
            recipe.setTitle(recipeDto.getTitle());
        }

        if (null != recipeDto.getDescription()) {
            recipe.setDescription(recipeDto.getDescription());
        }

        if (null != recipeDto.getServings()) {
            recipe.setServings(recipeDto.getServings());
        }

        if (null != recipeDto.getInstructions()) {
            recipe.setInstructions(recipeDto.getTitle());
        }

        if (null != recipeDto.getIngredients()) {
            List<Ingredient> ingredients = processUpdatedIngredients(recipeDto.getIngredients());
            recipe.setIngredients(ingredients);
        }

        if (null != recipeDto.getDietTypes()) {
            List<DietType> dietTypes = processUpdatedDietTypes(recipeDto.getDietTypes());
            recipe.setDietTypes(dietTypes);
        }

        return recipe;
    }

    private List<Ingredient> processUpdatedIngredients(List<IngredientDto> ingredientDtos) {
        List<Ingredient> updatedIngredients = new ArrayList<>();
        for (IngredientDto ingDTO : ingredientDtos) {

            Ingredient ingredient;
            if (ingDTO.getId() != null) {
                ingredient = ingredientRepository.findById(ingDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Ingredient not found"));
                if (ingDTO.getName() != null) ingredient.setName(ingDTO.getName());
                if (ingDTO.getDescription() != null) ingredient.setDescription(ingDTO.getDescription());
                ingredient.setUpdatedDate(Instant.now());
            } else {
                ingredient = new Ingredient();
                ingredient.setName(ingDTO.getName());
                ingredient.setDescription(ingDTO.getDescription());
                ingredient.setCreatedDate(Instant.now());
                ingredientRepository.save(ingredient);
            }

            updatedIngredients.add(ingredient);
        }

        return updatedIngredients;
    }

    private List<DietType> processUpdatedDietTypes(List<DietTypeDto> dietTypesDto) {
        List<DietType> updatedDietTypes = new ArrayList<>();
        for (DietTypeDto dto : dietTypesDto) {

            DietType dietType;
            if (dto.getId() != null) {
                dietType = dietTypeRepository.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("Diet type not found"));
                if (dto.getName() != null) dietType.setName(dto.getName());
                if (dto.getDescription() != null) dietType.setDescription(dto.getDescription());
                dietType.setUpdatedDate(Instant.now());
            } else {
                dietType = new DietType();
                dietType.setName(dto.getName());
                dietType.setDescription(dto.getDescription());
                dietType.setCreatedDate(Instant.now());
                dietTypeRepository.save(dietType);
            }

            updatedDietTypes.add(dietType);
        }

        return updatedDietTypes;
    }
}
