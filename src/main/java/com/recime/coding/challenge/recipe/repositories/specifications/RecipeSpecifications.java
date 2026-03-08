package com.recime.coding.challenge.recipe.repositories.specifications;

import com.recime.coding.challenge.recipe.models.dto.SearchCriteriaDto;
import com.recime.coding.challenge.recipe.models.entities.DietType;
import com.recime.coding.challenge.recipe.models.entities.Ingredient;
import com.recime.coding.challenge.recipe.models.entities.Recipe;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class RecipeSpecifications {

    public static Specification<Recipe> fullTextSearch(String text) {
        return (root, query, cb) -> cb.isTrue(
                cb.function(
                        "to_tsvector('english', title || ' ' || description || ' ' || instructions) @@ websearch_to_tsquery",
                        Boolean.class,
                        cb.literal(text)
                )
        );

    }

    public static Specification<Recipe> servingsEquals(Integer servings) {
        return (root, query, cb) ->
                cb.equal(root.get("servings"), servings);
    }

    public static Specification<Recipe> hasDietType(List<String> dietTypes) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<Recipe, DietType> join = root.join("dietTypes");

            return join.get("name").in(dietTypes);
        };
    }

    public static Specification<Recipe> includeIngredient(List<String> ingredients) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<Recipe, Ingredient> join = root.join("ingredients");

            return join.get("name").in(ingredients);
        };
    }

    public static Specification<Recipe> buildFrom(List<SearchCriteriaDto> dtos) {
        Specification<Recipe> spec = Specification.allOf();

        for (SearchCriteriaDto dto: dtos) {
            if (dto.getParameter() != null && !dto.getParameter().isBlank()) {
                if (dto.getParameter().toLowerCase().equals(SearchCriteriaDto.PARAMETER_TEXT) ) {
                    String values = String.join(" ", dto.getValues());
                    spec = spec.and(fullTextSearch(values));
                }

                if (dto.getParameter().toLowerCase().equals(SearchCriteriaDto.PARAMETER_SERVINGS)) {
                    Integer servings = Integer.parseInt(dto.getValues().get(0));
                    spec = spec.and(servingsEquals(servings));
                }

                if (dto.getParameter().toLowerCase().equals(SearchCriteriaDto.PARAMETER_DIET_TYPE)) {
                    spec = spec.and(hasDietType(dto.getValues()));
                }

                if (dto.getParameter().toLowerCase().equals(SearchCriteriaDto.PARAMETER_INGREDIENT)) {
                    spec = spec.and(includeIngredient(dto.getValues()));
                }
            }
        }

        return spec;
    }
}
