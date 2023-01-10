package com.example.restaurant.repository.proxy;

import com.example.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("ALL")
@Transactional(readOnly = true)
public interface ProxyRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") Integer id);

    @EntityGraph(attributePaths = {"dish"})
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant getWithDish(@Param("id") Integer id);

    @Query("SELECT r FROM Restaurant r WHERE r.creationDate=:localDate")
    List<Restaurant> getAll(@Param("localDate") LocalDate localDate);
}
