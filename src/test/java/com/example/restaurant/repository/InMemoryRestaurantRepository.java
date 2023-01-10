package com.example.restaurant.repository;

import com.example.restaurant.RestaurantTestData;
import com.example.restaurant.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import static com.example.restaurant.RestaurantTestData.thisDay;

@Repository
public class InMemoryRestaurantRepository implements RestaurantRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryRestaurantRepository.class);

    private final Map<LocalDate, InMemoryBaseRepository<Restaurant>> restaurantMap = new ConcurrentHashMap<>();

    {
        var restaurants = new InMemoryBaseRepository<Restaurant>();
        RestaurantTestData.restaurants.forEach(restaurants::put);
        restaurantMap.put(thisDay, restaurants);
    }


    @Override
    public Restaurant save(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "restaurant must not be null");
        var restaurants = restaurantMap.computeIfAbsent(restaurant.getCreationDay(), rCD -> new InMemoryBaseRepository<>());
        return restaurants.save(restaurant);
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
    public boolean delete(int id) {
        var restaurants = restaurantMap.get(thisDay);
        return restaurants != null && restaurants.delete(id);
    }

    @Override
    public Restaurant get(int id) {
        var restaurants = restaurantMap.get(thisDay);
        return restaurants == null ? null : restaurants.get(id);
    }

    @Override
    public List<Restaurant> getAll(LocalDate thisDay) {
        return filterByPredicate(thisDay, restaurant -> true);
    }

    private List<Restaurant> filterByPredicate(LocalDate thisDay, Predicate<Restaurant> filter) {
        var restaurants = restaurantMap.get(thisDay);
        return restaurants == null ? Collections.emptyList() :
                restaurants.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Restaurant::getCreationDay).reversed())
                        .toList();
    }
}
