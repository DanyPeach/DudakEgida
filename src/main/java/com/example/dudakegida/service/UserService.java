package com.example.dudakegida.service;

import com.example.dudakegida.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User getByName(String name);

    void save(User user);

}
