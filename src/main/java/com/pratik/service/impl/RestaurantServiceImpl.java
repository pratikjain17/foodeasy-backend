package com.pratik.service.impl;

import com.pratik.dto.RestaurentDto;
import com.pratik.model.Address;
import com.pratik.model.Restaurant;
import com.pratik.model.User;
import com.pratik.repository.AddressRepository;
import com.pratik.repository.RestaurantRepository;
import com.pratik.repository.UserRepository;
import com.pratik.request.CreaterRestaurantRequest;
import com.pratik.service.RestaurantService;
import com.pratik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreaterRestaurantRequest request, User user) {
        Address address = addressRepository.save(request.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreaterRestaurantRequest updateRequest) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updateRequest.getCuisineType());
        }
        if(restaurant.getDescription() != null){
            restaurant.setDescription(updateRequest.getDescription());
        }
        if(restaurant.getName() != null){
            restaurant.setName(updateRequest.getName());
        }
        if(restaurant.getAddress() != null){
            restaurant.setAddress(updateRequest.getAddress());
        }
        if(restaurant.getOpeningHours() != null){
            restaurant.setOpeningHours(updateRequest.getOpeningHours());
        }
        if(restaurant.getImages() != null){
            restaurant.setImages(updateRequest.getImages());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.searchByQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()){
            throw new Exception("Restaurant Not Found with Id: " + id);
        }
        return restaurant.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long id) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(id);
        if(restaurant == null){
            throw new Exception("Restaurant Not Found with Owner Id: " + id);
        }
        return restaurant;
    }

    @Override
    public RestaurentDto addFavourites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurentDto restaurentDto = new RestaurentDto();
        restaurentDto.setDescription(restaurant.getDescription());
        restaurentDto.setImages(restaurant.getImages());
        restaurentDto.setTitle(restaurant.getName());
        restaurentDto.setId(restaurantId);

       boolean isFavourited = false;
       List<RestaurentDto> favourites = user.getFavourites();
       for(RestaurentDto favourite : favourites){
           if(favourite.getId().equals(restaurantId)){
               isFavourited = true;
               break;
           }
       }

       if (isFavourited){
           favourites.removeIf(favourite -> favourite.getId().equals(restaurantId));
       }else favourites.add(restaurentDto);

        userRepository.save(user);
        return restaurentDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
