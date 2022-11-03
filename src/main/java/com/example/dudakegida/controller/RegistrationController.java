package com.example.dudakegida.controller;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.User;
import com.example.dudakegida.service.AnimalService;
import com.example.dudakegida.service.MessageService;
import com.example.dudakegida.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Configuration
@RequestMapping("/new")
public class RegistrationController {
    private final UserService userService;
    private final MessageService messageService;
    private final AnimalService animalService;

    public RegistrationController(UserService userService, MessageService messageService, AnimalService animalService) {
        this.userService = userService;
        this.messageService = messageService;
        this.animalService = animalService;
    }

    @GetMapping("/registrationPage")
    public ModelAndView registrationPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        user.setBalance(0);
        userService.save(user);
        List<Animal> randomThree = animalService.findRandomThree();
        modelAndView.addObject("randomThree", randomThree);
        modelAndView.setViewName("tem");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }
}
