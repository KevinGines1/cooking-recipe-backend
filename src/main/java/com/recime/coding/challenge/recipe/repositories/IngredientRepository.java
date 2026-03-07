package com.recime.coding.challenge.recipe.repositories;

import com.recime.coding.challenge.recipe.models.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    Optional<Ingredient> findByName(String name);
}
