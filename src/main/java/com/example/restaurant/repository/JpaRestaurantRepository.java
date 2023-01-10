package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.proxy.ProxyRestaurantRepository;
import com.example.restaurant.repository.proxy.ProxyUserRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JpaRestaurantRepository implements RestaurantRepository {

    private final ProxyRestaurantRepository proxyRepository;

    public JpaRestaurantRepository(ProxyRestaurantRepository proxyRepository) {
        this.proxyRepository = proxyRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return proxyRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return proxyRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return proxyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll(LocalDate thisDay) {
        return proxyRepository.getAll(thisDay);
    }
    @Override
    public Restaurant getWithDish(int id) {
        return RestaurantRepository.super.getWithDish(id);
    }
}
