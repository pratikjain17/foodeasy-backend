package com.pratik.repository;

import com.pratik.model.IngredientsItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsItemsRepository extends JpaRepository<IngredientsItems, Long> {
    public List<IngredientsItems> findByRestaurantId(Long restaurantId);
}
