package com.example.restaurant.repository;

import com.example.restaurant.model.Vote;
import com.example.restaurant.repository.proxy.ProxyRestaurantRepository;
import com.example.restaurant.repository.proxy.ProxyUserRepository;
import com.example.restaurant.repository.proxy.ProxyVoteRepository;
import com.example.restaurant.to.RestaurantTo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Repository
public class JpaVoteRepository implements VoteRepository {

    private final ProxyVoteRepository proxyVoteRepository;

    private final ProxyRestaurantRepository proxyRestaurantRepository;

    private final ProxyUserRepository proxyUserRepository;

    public JpaVoteRepository(ProxyVoteRepository proxyVoteRepository, ProxyRestaurantRepository proxyRestaurantRepository, ProxyUserRepository proxyUserRepository) {
        this.proxyVoteRepository = proxyVoteRepository;
        this.proxyRestaurantRepository = proxyRestaurantRepository;
        this.proxyUserRepository = proxyUserRepository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote, int restaurantId, int userId) {
        if (LocalTime.now().isAfter(LocalTime.parse("11:00:00"))) { return null;}
        vote.setRestaurant(proxyRestaurantRepository.getReferenceById(restaurantId));
        vote.setUser(proxyUserRepository.getReferenceById(userId));
        return proxyVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxyVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId, LocalDate localDate) {
        return proxyVoteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .filter(vote -> vote.getCreationDay() == localDate)
                .orElse(null);
    }

    public RestaurantTo getVotingResult(LocalDate today) {
        List <RestaurantTo> result = proxyVoteRepository.getVotingResult(today);
        Collections.sort(result);
        return result.get(1);
    }
}
