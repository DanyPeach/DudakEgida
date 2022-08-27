package com.example.dudakegida.repository;

import com.example.dudakegida.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

}
