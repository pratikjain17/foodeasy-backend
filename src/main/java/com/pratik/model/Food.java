package com.pratik.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long price;
    @ManyToOne
    private Category foodCategory;
    @Column(length = 1000)
    @ElementCollection
    private List<String> images;
    private boolean isAvailable;
    @ManyToOne
    private Restaurant restaurant;
    private boolean isVeg;
    private boolean isSeasonable;
    @ManyToMany
    private List<IngredientsItems> ingredientsItems = new ArrayList<>();
    private Date creationDate;
}
