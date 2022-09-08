package com.example.dudakegida.controller;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.AnimalStatus;
import com.example.dudakegida.service.AnimalService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        animal.setAnimal_status(AnimalStatus.AFFORDABLE);
        animalService.save(animal);
        List<Animal> list = animalService.findAll();
        modelAndView.addObject("listOfPets", list);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }

    @GetMapping(value = "/hide/{id}")
    public ModelAndView deleteAnimal(@PathVariable(name = "id") Long id){
       ModelAndView modelAndView = new ModelAndView();
       Animal animal = animalService.findById(id);
       animal.setAnimal_status(AnimalStatus.TAKEN);
       animalService.update(animal);
       modelAndView.setViewName("allpets");
       return modelAndView;
    }

    @GetMapping(value = "/allpetsPage")
    public ModelAndView allpetsPage(){
        ModelAndView modelAndView = new ModelAndView();
        List<Animal> listOfPets = animalService.findAll();
        modelAndView.addObject("listOfPets", listOfPets);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }
}
