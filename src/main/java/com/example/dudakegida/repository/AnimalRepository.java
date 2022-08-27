package com.example.dudakegida.repository;

import com.example.dudakegida.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
