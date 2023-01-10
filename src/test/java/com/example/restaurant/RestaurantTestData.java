package com.example.restaurant;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.example.restaurant.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "registered", "dish", "name");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_DISH_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("registered", "dishes.restaurant", "password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int NOT_FOUND = 10;

    public static final LocalDate thisDay;

    static {
        thisDay = LocalDate.of(2023, 1, 1);
    }

    public static final int RESTAURANT1_ID = START_SEQ + 3;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Ресторан 1", thisDay);

    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID, "Ресторан 2", thisDay);

    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID, "Ресторан 3", thisDay);

    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3);

    public static Restaurant getNew() {
        return new Restaurant(null, "Созданный ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный ресторан", thisDay.plusDays(1));
    }
}
