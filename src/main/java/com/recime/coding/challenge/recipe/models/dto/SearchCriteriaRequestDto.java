package com.recime.coding.challenge.recipe.models.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class SearchCriteriaRequestDto {
    private List<SearchCriteriaDto> searchCriteria;
}
