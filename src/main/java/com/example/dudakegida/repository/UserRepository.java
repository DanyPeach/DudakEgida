package com.example.dudakegida.repository;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    @Query(value = "SELECT user_id FROM user_confirming ", nativeQuery = true)
    Set<Long> findUsersIdByChosenPets();
}
