package com.example.restaurant.repository;

import com.example.restaurant.model.User;
import com.example.restaurant.repository.proxy.ProxyUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

    private final ProxyUserRepository proxyRepository;

    public JpaUserRepository(ProxyUserRepository proxyRepository) {
        this.proxyRepository = proxyRepository;
    }

    @Override
    public User save(User user) {
        return proxyRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return proxyRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return proxyRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return proxyRepository.findAll();
    }
}
