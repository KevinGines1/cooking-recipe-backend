package com.recime.coding.challenge.recipe.models.dto;

import com.recime.coding.challenge.recipe.models.entities.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
    private UUID id;
    private String title;
    private String description;
    private Integer servings;
    private String instructions;
    private List<IngredientDto> ingredients;
    private List<DietTypeDto> dietTypes;

    public static RecipeDto toDto(Recipe recipe) {
        List<IngredientDto> ingredientDtos = recipe.getIngredients().stream()
                .map(IngredientDto::toDto)
                .collect(Collectors.toList());

        List<DietTypeDto> dietTypeDtos = recipe.getDietTypes().stream()
                .map(DietTypeDto::toDto)
                .collect(Collectors.toList());

        return RecipeDto.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .description(recipe.getDescription())
                .servings(recipe.getServings())
                .instructions(recipe.getInstructions())
                .ingredients(ingredientDtos)
                .dietTypes(dietTypeDtos)
                .build();
    }
}
