package com.pratik.repository;

import com.pratik.model.IngredientsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsCategoryRepository extends JpaRepository<IngredientsCategory, Long> {

    public List<IngredientsCategory> findByRestaurantId(Long restaurantId);
}
