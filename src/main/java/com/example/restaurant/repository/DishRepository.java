package com.example.restaurant.repository;

import com.example.restaurant.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int Id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

    default Dish getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }
}
