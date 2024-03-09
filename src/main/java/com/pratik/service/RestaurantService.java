package com.pratik.service;

import com.pratik.dto.RestaurentDto;
import com.pratik.model.Restaurant;
import com.pratik.model.User;
import com.pratik.request.CreaterRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreaterRestaurantRequest request, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreaterRestaurantRequest updateRequest) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long id) throws Exception;

    public RestaurentDto addFavourites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
