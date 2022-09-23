package com.example.dudakegida.repository;

import com.example.dudakegida.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query(value = "SELECT * FROM pets WHERE animal_id=:animal_id", nativeQuery = true)
    Animal findAnimalById(@Param("animal_id") Long animal_id);

    @Query(value = "SELECT * FROM pets WHERE animal_type=:animal_type", nativeQuery = true)
    List<Animal> groupByType(@Param("animal_type") int animal_type);

}
