package com.example.restaurant.repository.proxy;

import com.example.restaurant.model.Vote;
import com.example.restaurant.to.RestaurantTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ProxyVoteRepository extends JpaRepository<Vote, Integer> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id= ?1 AND v.user.id= ?2")
    int delete(Integer id, Integer userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.id = ?1 and v.restaurant.id = ?2")
    Vote getWithRestaurant(Integer id, Integer restaurantId);

    @Query("SELECT v.restaurant.id, r.name, COUNT(v.user.id) AS countresult  FROM Vote v LEFT JOIN Restaurant r WHERE v.creationDay = ?1 GROUP BY v.restaurant.id ORDER BY countresult DESC")
    List<RestaurantTo> getVotingResult(LocalDate today);

}
