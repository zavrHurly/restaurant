package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll(LocalDate thisDay);

    default Restaurant getWithDish(int id) {
        throw new UnsupportedOperationException();
    }
}
