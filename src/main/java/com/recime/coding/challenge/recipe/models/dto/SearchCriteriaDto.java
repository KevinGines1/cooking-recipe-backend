package com.recime.coding.challenge.recipe.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchCriteriaDto {
    public static final String PARAMETER_TEXT = "text";
    public static final String PARAMETER_INGREDIENT = "ingredients";
    public static final String PARAMETER_SERVINGS = "servings";
    public static final String PARAMETER_DIET_TYPE = "diet_type";
    private String parameter;
    private String operation;
    private List<String> values;
}
