package com.example.restaurant.to;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class RestaurantTo extends BaseTo implements Comparable {

    private final String restaurantName;

    private final int countOfVotes;

    @ConstructorProperties({"restaurantId", "restaurantName", "countOfVotes"})
    public RestaurantTo(Integer restaurantId, String restaurantName, int countOfVotes){
        super(restaurantId);
        this.restaurantName = restaurantName;
        this.countOfVotes = countOfVotes;
    }

    public String getName() {
        return restaurantName;
    }

    public int getCountOfVotes() {
        return countOfVotes;
    }

    public int compareTo(Object o) {
        RestaurantTo that = (RestaurantTo) o;
        return this.countOfVotes - that.countOfVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo that = (RestaurantTo) o;
        return countOfVotes == that.countOfVotes && Objects.equals(restaurantName, that.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantName, countOfVotes);
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "restaurantName='" + restaurantName + '\'' +
                ", countOfVotes=" + countOfVotes +
                ", restaurantId=" + id +
                '}';
    }

}