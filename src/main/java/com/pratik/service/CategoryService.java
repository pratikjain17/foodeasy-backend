package com.pratik.service;

import com.pratik.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(String name, Long userId) throws Exception;
    List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception;
    Category findCategoryById(Long id) throws Exception;
}
