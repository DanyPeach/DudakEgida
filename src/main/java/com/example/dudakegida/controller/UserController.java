package com.example.dudakegida.controller;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.Role;
import com.example.dudakegida.model.User;
import com.example.dudakegida.service.AnimalService;
import com.example.dudakegida.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AnimalService animalService;

    public UserController(UserService userService, AnimalService animalService) {
        this.userService = userService;
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
        user.setRole(Set.of(Role.USER));
        userService.save(user);
        modelAndView.setViewName("tem");
        return modelAndView;
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


    @GetMapping("/showing/{type}")
    public ModelAndView showByType(ModelAndView modelAndView, @PathVariable int type){
        List<Animal> listOfPets = animalService.findByType(type);
        modelAndView.addObject("listOfPets", listOfPets);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }

//    @ModelAttribute
//    public void addAttributes(Model model){
//        model.addAttribute("listOfPets", new ArrayList<Animal>());
//        model.addAttribute("user", new User());
//    }
}
