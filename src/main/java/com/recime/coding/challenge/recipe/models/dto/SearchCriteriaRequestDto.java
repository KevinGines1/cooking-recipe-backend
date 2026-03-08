package com.recime.coding.challenge.recipe.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;


@Getter
public class SearchCriteriaRequestDto {
    @NotNull(message="searchCriteria list if required")
    private List<SearchCriteriaDto> searchCriteria;
}
