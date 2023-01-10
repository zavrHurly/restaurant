package com.example.restaurant.web.vote;

import com.example.restaurant.model.Vote;
import com.example.restaurant.service.VoteService;
import com.example.restaurant.to.RestaurantTo;
import com.example.restaurant.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.example.restaurant.util.ValidationUtil.assureIdConsistent;
import static com.example.restaurant.util.ValidationUtil.checkNew;

public class AbstractVoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public Vote get(int id, LocalDate thisDay) {
        int userId = SecurityUtil.authUserId();
        log.info("get Vote {} for user {}", id, userId);
        return service.get(id, userId, thisDay);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public Vote create(Vote vote, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} for user {} behind {}", vote, userId, restaurantId);
        checkNew(vote);
        return service.create(vote, userId, restaurantId);
    }

    public void update(Vote vote, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} for user {} behind {}", vote, userId, restaurantId);
        assureIdConsistent(vote, userId);
        service.update(vote, userId, restaurantId);
    }

    public RestaurantTo getVotingResult(LocalDate today) {
        log.info("get voting result");
        return service.getVotingResult(today);
    }
}
