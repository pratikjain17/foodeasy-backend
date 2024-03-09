package com.pratik.request;

import com.pratik.model.Category;
import com.pratik.model.IngredientsItems;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long RestaurantId;
    private boolean isVeg;
    private boolean isSeasonal;
    private List<IngredientsItems> ingredients;
}
