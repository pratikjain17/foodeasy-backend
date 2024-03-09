package com.pratik.request;

import com.pratik.model.Address;
import com.pratik.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreaterRestaurantRequest {
    private long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
