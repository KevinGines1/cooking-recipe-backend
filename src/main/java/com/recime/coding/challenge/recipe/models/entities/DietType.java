package com.recime.coding.challenge.recipe.models.entities;

import com.recime.coding.challenge.recipe.models.dto.DietTypeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Entity(name="diet_types")
public class DietType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;

    @ManyToMany(mappedBy = "dietTypes")
    private List<Recipe> recipes;

    public static DietType fromDto(DietTypeDto dto) {
        return DietType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdDate(dto.getCreatedDate())
                .updatedDate(dto.getUpdatedDate())
                .build();
    }
}
