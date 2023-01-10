package com.example.restaurant.repository;

import com.example.restaurant.model.Dish;
import com.example.restaurant.repository.proxy.ProxyDishRepository;
import com.example.restaurant.repository.proxy.ProxyRestaurantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JpaDishRepository  implements DishRepository {

    private final ProxyDishRepository proxyDishRepository;

    private final ProxyRestaurantRepository proxyRestaurantRepository;

    public JpaDishRepository(ProxyDishRepository proxyDishRepository, ProxyRestaurantRepository proxyRestaurantRepository) {
        this.proxyDishRepository = proxyDishRepository;
        this.proxyRestaurantRepository = proxyRestaurantRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(proxyRestaurantRepository.getReferenceById(restaurantId));
        return proxyDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return proxyDishRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return proxyDishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return proxyDishRepository.getAll(restaurantId);
    }

    @Override
    public Dish getWithRestaurant(int id, int restaurantId) {
        return proxyDishRepository.getWithRestaurant(id, restaurantId);
    }
}

