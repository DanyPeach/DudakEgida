package com.example.dudakegida.repository;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT * FROM cart WHERE user_id=:user_id", nativeQuery = true)
    List<Cart> findCartByUserId(@Param("user_id") long user_id);
}
