package com.recime.coding.challenge.recipe.models.dto;

import com.recime.coding.challenge.recipe.models.entities.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
    private UUID id;
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;

    public static IngredientDto toDto(Ingredient ingredient) {
        return IngredientDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .description(ingredient.getDescription())
                .createdDate(ingredient.getCreatedDate())
                .updatedDate(ingredient.getUpdatedDate())
                .build();
    }
}
