package com.example.dudakegida.service.impl;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.repository.AnimalRepository;
import com.example.dudakegida.service.AnimalService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public void update(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findAnimalById(id);
    }


//    @Override
//    public void delete(Long animal_id) {
//        Animal animal = animalRepository.findAnimalById(animal_id);
//        animalRepository.delete(animal);
//    }


    @Override
    public List<Animal> findAll(){
        return animalRepository.findAll();
    }
}
