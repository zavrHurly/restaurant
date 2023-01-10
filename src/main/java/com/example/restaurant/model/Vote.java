package com.example.restaurant.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "creationDate")
    @NotNull
    private LocalDate creationDay;

    public Vote(){
    }
    public Vote (User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
        creationDay = creationDay.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getCreationDay() {
        return creationDay;
    }

    public void setThisDay(LocalDate creationDay) {
        this.creationDay = creationDay;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user" + user +
                "restaurant=" + restaurant +
                '}';
    }
}
