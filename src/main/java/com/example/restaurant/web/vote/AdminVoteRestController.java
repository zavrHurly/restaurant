package com.example.restaurant.web.vote;

import com.example.restaurant.to.RestaurantTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = AdminVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteRestController extends AbstractVoteController{

    static final String REST_URL = "rest/admin/result";

    @GetMapping
    public RestaurantTo getVotingResult() {
        LocalDate today = LocalDate.now();
        return super.getVotingResult(today);
    }


}
