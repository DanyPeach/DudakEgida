package com.example.dudakegida.service.impl;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.repository.AnimalRepository;
import com.example.dudakegida.service.AnimalService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public List<Animal> findAll(){
        return animalRepository.findAll();
    }
}
