package com.recime.coding.challenge.recipe.models.entities;

import com.recime.coding.challenge.recipe.models.dto.IngredientDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredients", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message="Ingredient name is required")
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

    public static Ingredient fromDto(IngredientDto dto) {
        return Ingredient.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
