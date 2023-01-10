package com.example.restaurant.repository;

import com.example.restaurant.model.Vote;
import com.example.restaurant.to.RestaurantTo;

import java.time.LocalDate;

public interface VoteRepository {

    Vote save(Vote vote, int restaurantId, int userId);

    Vote get(int id, int userId, LocalDate today);

    boolean delete(int id, int userId);

    RestaurantTo getVotingResult(LocalDate today);
}
