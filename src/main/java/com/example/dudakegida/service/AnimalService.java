package com.example.dudakegida.service;

import com.example.dudakegida.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {
    List<Animal> findAll();
    void save(Animal animal);
    void update(Animal animal);
    Animal findById(Long id);
    List<Animal> findByType(int type);
    List<Animal> findRandomThree();
    List<Animal> findPetsByUserId(long id);

}
