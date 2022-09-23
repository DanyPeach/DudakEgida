package com.example.dudakegida.repository;

import com.example.dudakegida.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    /*User findByLogin(String login);*/

    User findByLogin(String login);


}
