package com.example.dudakegida.service;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    void save(User user);

    User findByLogin(String login);

    boolean confirmeChosenPet(User user, Authentication authentication);

    void setChosen_pet_id(long chosen_pet_id);

    List<Animal> showMyPets(Authentication authentication);

    void takeChosenPet(long petId, Authentication authentication);

    long getChosenPetId();
    //method for admin to find users that want pets and waiting for confirming
    List<User> findUsersById();
}
