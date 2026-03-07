package com.recime.coding.challenge.recipe.repositories;

import com.recime.coding.challenge.recipe.models.entities.DietType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DietTypeRepository extends JpaRepository<DietType, UUID> {
    Optional<DietType> findByName(String name);
}
