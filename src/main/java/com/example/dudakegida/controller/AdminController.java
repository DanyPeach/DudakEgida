package com.example.dudakegida.controller;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.service.AnimalService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AnimalService animalService;

    public AdminController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/addPage")
    public ModelAndView addAnimalPage(ModelAndView modelAndView, @ModelAttribute(name = "animal") Animal animal){
        modelAndView.addObject("animal", animal);
        modelAndView.setViewName("addPetPage");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addAnimal(@ModelAttribute("pets") Animal animal){
        ModelAndView modelAndView = new ModelAndView();
        animalService.save(animal);
        List<Animal> list = animalService.findAll();
        modelAndView.addObject("listOfPets", list);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }
}
