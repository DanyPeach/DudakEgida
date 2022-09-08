package com.example.dudakegida.repository;

import com.example.dudakegida.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    //nativeQuery = true - чистый SQL

    @Query(value = "SELECT name FROM users WHERE id=:id", nativeQuery = true)
    User findUserById(@Param("id") Long id);
}
