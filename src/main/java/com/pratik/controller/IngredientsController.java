package com.pratik.controller;

import com.pratik.model.IngredientsCategory;
import com.pratik.model.IngredientsItems;
import com.pratik.request.IngredientsCategoryRequest;
import com.pratik.request.IngredientsItemRequest;
import com.pratik.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientsCategory(
            @RequestBody IngredientsCategoryRequest request) throws Exception{
        IngredientsCategory ingredientsCategory = ingredientsService.createIngredientsCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItems> createIngredientsItems(
            @RequestBody IngredientsItemRequest request) throws Exception{
        IngredientsItems ingredientsItem = ingredientsService.createIngredientsItems(request.getRestaurantId(), request.getName(), request.getCategoryId());
        return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItems> updateStock(
            @PathVariable Long id) throws Exception{
        IngredientsItems ingredientsItem = ingredientsService.updateStock(id);
        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItems>> getRestaurantsIngredients(
            @PathVariable Long id) throws Exception{
        List<IngredientsItems> ingredientsItem = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantsIngredientsCategory(
            @PathVariable Long id) throws Exception{
        List<IngredientsCategory> ingredientsCategory = ingredientsService.findIngredientsCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.OK);
    }
}
