package com.pratik.service;

import com.pratik.model.Category;
import com.pratik.model.Food;
import com.pratik.model.Restaurant;
import com.pratik.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant);

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVeg,
                                         boolean isNonVeg,
                                         boolean isSeasonal,
                                         String foodCategory);

    public List<Food> searchFoods(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailability(Long foodId) throws Exception;
}
