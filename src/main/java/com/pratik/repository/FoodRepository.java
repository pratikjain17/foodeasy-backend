package com.pratik.repository;

import com.pratik.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE lower(f.name) LIKE lower(concat('%',:query,'%')) \" +\n" +
            "            \"OR lower(f.foodCategory.name) LIKE lower(concat('%',:query,'%'))")
    List<Food> searchFood(String query);
}
