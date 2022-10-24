package com.example.dudakegida.repository;

import com.example.dudakegida.model.PetFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<PetFood, Long> {
}
