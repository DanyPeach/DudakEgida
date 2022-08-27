package com.example.dudakegida.service;

import com.example.dudakegida.model.Animal;

import java.util.List;

public interface AnimalService {
    List<Animal> findAll();
    void save(Animal animal);
}
