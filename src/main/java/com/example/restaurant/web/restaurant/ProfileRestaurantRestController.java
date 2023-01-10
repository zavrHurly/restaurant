package com.example.restaurant.web.restaurant;

import com.example.restaurant.model.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantRestController extends AbstractRestaurantController{

    static final String REST_URL = "rest/profile/restaurant";

    @GetMapping
    public List<Restaurant> getAll() {
        LocalDate thisDay = LocalDate.now();
        return super.getAll(thisDay);
    }
}
