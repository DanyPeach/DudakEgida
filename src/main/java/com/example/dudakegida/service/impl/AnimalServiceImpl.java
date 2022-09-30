package com.example.dudakegida.service.impl;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.repository.AnimalRepository;
import com.example.dudakegida.service.AnimalService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    @Override
    public List<Animal> findByType(int type) {
       return animalRepository.groupByType(type);
    }

    @Override
    public List<Animal> findRandomThree() {
        List<Animal> animals = findAll();
        if(!animals.isEmpty()) {
            Collections.shuffle(animals);
            List<Animal> result = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                result.add(animals.get(i));
            }
            System.out.println(result);
            return result;
        }else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Animal> findPetsByUserId(long id) {
        return animalRepository.findPetsByUserId(id);
    }



//    @Override
//    public List<Animal> findAnimalByUserId(long id) {
//        long  = animalRepository.findPetsByUserId(id);
//        return null;
//    }


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
