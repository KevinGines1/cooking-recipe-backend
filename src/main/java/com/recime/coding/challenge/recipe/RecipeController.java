package com.recime.coding.challenge.recipe;

import com.recime.coding.challenge.recipe.models.dto.RecipeDto;
import com.recime.coding.challenge.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController("recipeController")
@RequestMapping("/recipe")
public class RecipeController {
    public static final String X_REQUEST_ID = "x-request-id";

    private final RecipeService recipeService;
    public RecipeController(@Qualifier("recipeDataService") RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDto createRecipe(
            @RequestHeader(value=X_REQUEST_ID, required=true) String xRequestId,
            @Valid @RequestBody RecipeDto newRecipe
    ) {
        return recipeService.createRecipe(newRecipe);
    }

    @GetMapping("/{recipeId}")
    public RecipeDto findRecipe(
            @RequestHeader(value=X_REQUEST_ID, required=true) String xRequestId,
            @PathVariable("recipeId") UUID recipeId
    ) {
        return recipeService.findRecipeById(recipeId);
    }

    @PatchMapping("/{recipeId}")
    public RecipeDto updateRecipe(
            @RequestHeader(value=X_REQUEST_ID, required=true) String xRequestId,
            @PathVariable("recipeId") UUID recipeId,
            @Valid @RequestBody RecipeDto updatedRecipe
    ) {
        return recipeService.updateRecipe(recipeId, updatedRecipe);
    }

    @DeleteMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecipe(
            @RequestHeader(value=X_REQUEST_ID, required=true) String xRequestId,
            @PathVariable("recipeId") UUID recipeId
    ) {
        recipeService.deleteRecipe(recipeId);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchRecipe(
            @RequestHeader(value=X_REQUEST_ID, required=true) String xRequestId
    ) {
        return null;
    }

}
