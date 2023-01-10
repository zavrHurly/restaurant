package com.example.restaurant;

import com.example.restaurant.model.Dish;

import java.time.Month;
import java.util.List;

import static com.example.restaurant.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.of;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int NOT_FOUND = 10;
    public static final int DISH1_ID = START_SEQ + 10;

    public static final Dish dish1 = new Dish(DISH1_ID, "Суп", 200);
    public static final Dish dish2 = new Dish(DISH1_ID, "Мяско", 300);
    public static final Dish dish3 = new Dish(DISH1_ID, "Салат", 100);
    public static final Dish dish4 = new Dish(DISH1_ID, "Компот", 50);

    public static final List<Dish> dishes = List.of(dish4, dish3, dish2, dish1);

    public static Dish getNew() {
        return new Dish(null, "Созданное блюдо", 350);
    }

}
