package com.example.dudakegida.service.impl;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.AnimalStatus;
import com.example.dudakegida.model.User;
import com.example.dudakegida.repository.AnimalRepository;
import com.example.dudakegida.repository.UserRepository;
import com.example.dudakegida.service.AnimalService;
import com.example.dudakegida.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AnimalService animalService;

    private long chosen_pet_id = 0;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AnimalService animalRepository) {
        this.userRepository = userRepository;
        this.animalService = animalRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
//        return findAll().stream().filter(user -> user.getLogin().equals(login))
//                .findFirst().orElse(new User());
    }

    @Override
    public boolean confirmeChosenPet(User user, Authentication authentication) {
        User user1 = getUser(authentication);
        user.setPassword(user1.getPassword());
        System.out.println("auth user - " + user1.getPassword());
        System.out.println("user from form - " + user.getPassword());
        if(user.getPassword().equals(user1.getPassword())){
            Animal animal = animalService.findById(chosen_pet_id);
            animal.setAnimal_status(AnimalStatus.UNAFFORDABLE);
            animalService.update(animal);
            return true;
        }else{
            return false;
        }
    }

    private User getUser(Authentication authentication){
        String username = authentication.getName();
        return findByLogin(username);
    }

    public long getChosenPetId() {
        return chosen_pet_id;
    }

    public void setChosen_pet_id(long chosen_pet_id) {
        this.chosen_pet_id = chosen_pet_id;
    }

    @Override
    public List<Animal> showMyPets(Authentication authentication) {
        User user = getUser(authentication);
        return animalService.findPetsByUserId(user.getId());
    }

    @Override
    public void takeChosenPet(long petId, Authentication authentication) {
        Animal animal = animalService.findById(petId);
        animal.setUser(getUser(authentication));
        animalService.update(animal);
    }
}
