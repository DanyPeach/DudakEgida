package com.example.dudakegida.service.impl;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.AnimalStatus;
import com.example.dudakegida.model.User;
import com.example.dudakegida.repository.AnimalRepository;
import com.example.dudakegida.repository.UserRepository;
import com.example.dudakegida.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service

public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    @Transactional
    public void update(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    @Transactional
    public Animal findById(Long id) {
        return animalRepository.findAnimalById(id).orElse(new Animal());
    }

    @Override
    @Transactional
    public List<Animal> findByType(int type) {
       return animalRepository.groupByType(type).stream().filter(pet -> pet.getAnimal_status()
                       .equals(AnimalStatus.AFFORDABLE) || pet.getAnimal_status().equals(AnimalStatus.CHECKING))
               .toList();
    }

    @Override
    @Transactional
    public List<Animal> findRandomThree() {
        List<Animal> animals = findAll().stream()
                .filter(pet -> pet.getAnimal_status().equals(AnimalStatus.AFFORDABLE))
                .limit(3)
                .toList();
        if(!animals.isEmpty() && animals.size()>=3) {
            return animals;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public List<Animal> findPetsByUserId(long id) {
        return animalRepository.findPetsByUserId(id);
    }

    @Override
    public String saveImg(MultipartFile imageFile) throws IOException {
        String folder = "C:\\Users\\user\\DudakEgida\\src\\main\\resources\\static\\images\\uploadedImg\\";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);

        return "/images/uploadedImg/" + imageFile.getOriginalFilename();
    }

    @Override
    public void deletePetsByIdIfTaken(Long id) {
        animalRepository.deleteCheckingPetWhenConfirme(id);
    }

//    @Override
//    public List<User> findUserAnimalWanted(Authentication authentication) {
//        List<User> list = new ArrayList<>();
//
//        for (Animal animal : findAll()){
//            if (animal.getUserChose() == userRepository.findByLogin(authentication.getName())){
//                list.add((User) animal.getUserChose());
//            }
//        }
//
//        return list;
//    }


    @Override
    public List<Animal> findAll(){
        System.out.println("hello: " + animalRepository.findAll());
        return animalRepository.findAll();
    }



}
