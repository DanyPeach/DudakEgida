package com.example.dudakegida.repository;

import com.example.dudakegida.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query(value = "SELECT * FROM pets WHERE animal_id=:animal_id", nativeQuery = true)
    Optional<Animal> findAnimalById(@Param("animal_id") Long animal_id);

    @Query(value = "SELECT * FROM pets WHERE animal_type=:animal_type", nativeQuery = true)
    List<Animal> groupByType(@Param("animal_type") int animal_type);

    @Query(value = "SELECT * FROM pets WHERE user_id=:user_id", nativeQuery = true)
    List<Animal> findPetsByUserId(@Param("user_id") long user_id);

    @Query(value = "SELECT animal_animal_id FROM pets_user_chose ", nativeQuery = true)
    Set<Long> findAnimalIdByChosenPets();

    @Query(value = "DELETE pets_id, user_id FROM user_confirming WHERE animal_id=:animal_id ", nativeQuery = true)
    void deleteCheckingPetWhenConfirme(@Param("animal_id") Long animal_id);
}
