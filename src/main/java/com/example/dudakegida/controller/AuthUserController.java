package com.example.dudakegida.controller;

import com.example.dudakegida.model.User;
import com.example.dudakegida.service.AnimalService;
import com.example.dudakegida.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/egida")
public class AuthUserController {
    private final UserService userService;
    private final AnimalService animalService;

    public AuthUserController(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

    @GetMapping("/myPets")
    public ModelAndView myPets(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userPets",
                animalService.findPetsByUserId(getUser(authentication).getId()));
        modelAndView.setViewName("UserPets");
        return modelAndView;
    }

    @GetMapping("/takePet/{pet_id}")
    public ModelAndView takePet(@PathVariable String pet_id, Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        userService.setChosen_pet_id(Long.decode(pet_id));
        modelAndView.setViewName("confirmePet");
        return modelAndView;
    }

    @PostMapping("/confirmePetPassword")
    public ModelAndView confirme(@ModelAttribute User user, Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        if(userService.confirmeChosenPet(user, authentication)){
            userService.takeChosenPet(userService.getChosenPetId(), authentication);
            modelAndView.addObject("randomThree", animalService.findRandomThree());
            modelAndView.setViewName("tem");
        }else{
            modelAndView.setViewName("");
        }
        return modelAndView;
    }

    private User getUser(Authentication authentication){
        String username = authentication.getName();
        return userService.findByLogin(username);
    }
}
