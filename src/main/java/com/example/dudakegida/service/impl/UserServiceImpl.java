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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AnimalService animalService;

    private long chosen_pet_id = 0;

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
        return user.getPassword().equals(user1.getPassword());
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
        Set<User> users = animal.getUserChose();
        users.add(getUser(authentication));
        animal.setUserChose(users);
        animal.setAnimal_status(AnimalStatus.CHECKING);
        animalService.update(animal);
    }

    @Override
    public List<User> findUsersById(){
        List<User> list = new ArrayList<>();
        for(var i : getUsersIdThatWantPet()){
            for(User user : findAll()){
                if(user.getId()==i){
                    list.add(user);
                }
            }
        }
        return list;
    }

    public Set<Long> getUsersIdThatWantPet(){
        return userRepository.findUsersIdByChosenPets();
    }
}
