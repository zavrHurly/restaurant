package com.example.restaurant.web.dish;

import com.example.restaurant.model.Dish;
import com.example.restaurant.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.example.restaurant.util.ValidationUtil.assureIdConsistent;
import static com.example.restaurant.util.ValidationUtil.checkNew;

public abstract class AbstractDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish get(int id, int restaurantId) {
        log.info("get Dish {} for restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete restaurant {} for restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public List<Dish> getAll(int restaurantId) {
        log.info("getAll for restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    public Dish create(Dish dish, int restaurantId) {
        log.info("create {} for restaurant {}", dish, restaurantId);
        checkNew(dish);
        return service.create(dish, restaurantId);
    }

    public void update(Dish dish, int restaurantId) {
        log.info("update {} for restaurant {}", dish, restaurantId);
        assureIdConsistent(dish, restaurantId);
        service.update(dish, restaurantId);
    }
}
