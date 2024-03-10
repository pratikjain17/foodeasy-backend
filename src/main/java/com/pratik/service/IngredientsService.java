package com.pratik.service;

import com.pratik.model.IngredientsCategory;
import com.pratik.model.IngredientsItems;

import java.util.List;

public interface IngredientsService {
    IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception;

    IngredientsCategory findIngredientsCategoryById(Long id) throws Exception;

    List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long restaurantId) throws Exception;

    List<IngredientsItems> findRestaurantsIngredients(Long restaurantId) throws Exception;

    IngredientsItems createIngredientsItems(Long restaurantId, String ingredientName, Long categoryId) throws Exception;

    IngredientsItems updateStock(Long restaurantId) throws Exception;
}
