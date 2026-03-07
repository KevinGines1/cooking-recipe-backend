package com.recime.coding.challenge.recipe.repositories;

import com.recime.coding.challenge.recipe.models.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    Optional<Recipe> findById(UUID id);
}
