package com.recime.coding.challenge.recipe.models.entities;

import com.recime.coding.challenge.recipe.models.dto.DietTypeDto;
import com.recime.coding.challenge.recipe.models.dto.RecipeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Recipe title is required")
    private String title;

    private String description;

    @Positive(message="Recipe servings should be a positive integer")
    @NotNull(message = "Recipe servings is required")
    private Integer servings;

    private String instructions;
    private Instant createdDate;
    private Instant updatedDate;

    @ManyToMany
    @JoinTable(
            name="recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @NotNull(message="Recipe must contain ingredients")
    private List<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(
            name="recipe_diet_types",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_type_id")
    )
    private List<DietType> dietTypes;

    public static Recipe fromDto(RecipeDto dto) {
        List<Ingredient> ingredients = dto.getIngredients().stream()
                .map(Ingredient::fromDto)
                .collect(Collectors.toList());

        List<DietTypeDto> dietTypeDtos = Optional.ofNullable(dto.getDietTypes()).orElse(List.of());

        List<DietType> dietType = dietTypeDtos.stream()
                .map(DietType::fromDto)
                .collect(Collectors.toList());

        return Recipe.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .servings(dto.getServings())
                .instructions(dto.getInstructions())
                .ingredients(ingredients)
                .dietTypes(dietType)
                .build();
    }
}
