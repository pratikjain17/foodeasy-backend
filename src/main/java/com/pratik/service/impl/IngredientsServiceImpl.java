package com.pratik.service.impl;

import com.pratik.model.IngredientsCategory;
import com.pratik.model.IngredientsItems;
import com.pratik.model.Restaurant;
import com.pratik.repository.IngredientsCategoryRepository;
import com.pratik.repository.IngredientsItemsRepository;
import com.pratik.service.IngredientsService;
import com.pratik.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    @Autowired
    private IngredientsCategoryRepository ingredientsCategoryRepository;
    @Autowired
    private IngredientsItemsRepository ingredientsItemsRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory ingredientsCategory = new IngredientsCategory();
        ingredientsCategory.setName(name);
        ingredientsCategory.setRestaurant(restaurant);
        return ingredientsCategoryRepository.save(ingredientsCategory);
    }

    @Override
    public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> ingredientsCategory = ingredientsCategoryRepository.findById(id);
        if(ingredientsCategory.isEmpty()){
            throw new Exception("Ingredient Category Not Found...");
        }
        return ingredientsCategory.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long restaurantId) throws Exception {
        restaurantService.findRestaurantById(restaurantId);
        return ingredientsCategoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<IngredientsItems> findRestaurantsIngredients(Long restaurantId) throws Exception {
        return ingredientsItemsRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItems createIngredientsItems(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory ingredientsCategory = findIngredientsCategoryById(categoryId);
        IngredientsItems ingredientsItems = new IngredientsItems();
        ingredientsItems.setName(ingredientName);
        ingredientsItems.setRestaurant(restaurant);
        ingredientsItems.setCategory(ingredientsCategory);
        IngredientsItems ingredient = ingredientsItemsRepository.save(ingredientsItems);
        ingredientsCategory.getIngredients().add(ingredient);
        return ingredient;
    }

    @Override
    public IngredientsItems updateStock(Long id) throws Exception {
        Optional<IngredientsItems> ingredientsItems = ingredientsItemsRepository.findById(id);
        if (ingredientsItems.isEmpty()){
            throw new Exception("Ingredient Not Found...");
        }
        IngredientsItems ingredientsItem = ingredientsItems.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientsItemsRepository.save(ingredientsItem);
    }
}
