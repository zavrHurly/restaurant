package com.example.restaurant.service;

import com.example.restaurant.model.Vote;
import com.example.restaurant.repository.VoteRepository;
import com.example.restaurant.to.RestaurantTo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static com.example.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id, int userId, LocalDate today) {
        return checkNotFoundWithId(repository.get(id, userId, today), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }


    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "Vote must not be null");
        checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.id());
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId, restaurantId);
    }

    public RestaurantTo getVotingResult(LocalDate today){
        return repository.getVotingResult(today);
    }

}
