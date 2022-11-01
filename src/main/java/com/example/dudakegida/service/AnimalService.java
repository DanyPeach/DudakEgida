package com.example.dudakegida.service;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    String saveImg(MultipartFile imageFile) throws IOException;
//    List<User> findUserAnimalWanted(Authentication authentication);
    void deletePetsByIdIfTaken(Long id);
}
