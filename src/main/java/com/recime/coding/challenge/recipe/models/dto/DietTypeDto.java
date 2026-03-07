package com.recime.coding.challenge.recipe.models.dto;

import com.recime.coding.challenge.recipe.models.entities.DietType;
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
public class DietTypeDto {
    private UUID id;
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;

    public static DietTypeDto toDto(DietType dietType) {
        return DietTypeDto.builder()
                .id(dietType.getId())
                .name(dietType.getName())
                .description(dietType.getDescription())
                .createdDate(dietType.getCreatedDate())
                .updatedDate(dietType.getUpdatedDate())
                .build();
    }
}
