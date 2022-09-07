package com.example.dudakegida.controller;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.service.AnimalService;
import com.example.dudakegida.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AnimalService animalService;

    public UserController(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

    @GetMapping("/home")
    public ModelAndView userList(ModelAndView modelAndView){
        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("tem");
        return modelAndView;
    }

    @GetMapping("/allpets")
    public ModelAndView allPets(ModelAndView modelAndView){
        List<Animal> listOfPets = animalService.findAll();
        modelAndView.addObject("listOfPets", listOfPets);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }
}
