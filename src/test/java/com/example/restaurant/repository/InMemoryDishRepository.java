package com.example.restaurant.repository;

import com.example.restaurant.DishTestData;
import com.example.restaurant.RestaurantTestData;
import com.example.restaurant.model.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@Repository
public class InMemoryDishRepository implements DishRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryDishRepository.class);

    private final Map<Integer, InMemoryBaseRepository<Dish>> dishesMap = new ConcurrentHashMap<>();

    {
        var dishes = new InMemoryBaseRepository<Dish>();
        DishTestData.dishes.forEach(dishes::put);
        dishesMap.put(RestaurantTestData.RESTAURANT1_ID, dishes);
    }


    @Override
    public Dish save(Dish dish, int restaurantId) {
        Objects.requireNonNull(dish, "meal must not be null");
        var dishes = dishesMap.computeIfAbsent(restaurantId, uId -> new InMemoryBaseRepository<>());
        return dishes.save(dish);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        var dishes = dishesMap.get(restaurantId);
        return dishes != null && dishes.delete(id);
    }

    @Override
    public Dish get(int id, int userId) {
        var dishes = dishesMap.get(userId);
        return dishes == null ? null : dishes.get(id);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return filterByPredicate(restaurantId, dish -> true);
    }

    private List<Dish> filterByPredicate(int restaurantId, Predicate<Dish> filter) {
        var restaurants = dishesMap.get(restaurantId);
        return restaurants == null ? Collections.emptyList() :
                restaurants.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Dish::getPrice).reversed())
                        .toList();
    }
}
