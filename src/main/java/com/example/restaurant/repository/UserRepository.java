package com.example.restaurant.repository;

import com.example.restaurant.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int Id);

    List<User> getAll();
}
