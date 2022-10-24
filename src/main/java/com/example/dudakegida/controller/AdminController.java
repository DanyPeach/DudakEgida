package com.example.dudakegida.controller;

import com.example.dudakegida.model.Animal;
import com.example.dudakegida.model.AnimalStatus;
import com.example.dudakegida.model.PetFood;
import com.example.dudakegida.model.User;
import com.example.dudakegida.service.AnimalService;
import com.example.dudakegida.service.ProductService;
import com.example.dudakegida.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AnimalService animalService;
    private final UserService userService;
    private final ProductService productService;

    public AdminController(AnimalService animalService, UserService userService, ProductService productService) {
        this.animalService = animalService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/addPage")
    public ModelAndView addAnimalPage(ModelAndView modelAndView, @ModelAttribute(name = "animal") Animal animal){
        modelAndView.addObject("animal", animal);
        modelAndView.setViewName("addPetPage");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addAnimal(@RequestParam("imageFile") MultipartFile imageFile, @ModelAttribute("pets") Animal animal) throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        animalService.saveImg(imageFile);
        animal.setImgURL(animalService.saveImg(imageFile));
        animal.setAnimal_status(AnimalStatus.AFFORDABLE);
        animalService.save(animal);
        List<Animal> list = animalService.findAll();
        modelAndView.addObject("listOfPets", list);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }

    @GetMapping("/addFoodPage")
    public ModelAndView addFoodPage(ModelAndView modelAndView, @ModelAttribute(name = "food") PetFood petFood){
        modelAndView.addObject("petfood", petFood);
        modelAndView.setViewName("addPetFood");
        return modelAndView;
    }

    @PostMapping("/addFood")
    public ModelAndView addFood(@RequestParam("imageFile") MultipartFile image,
                                ModelAndView modelAndView, @ModelAttribute("food") PetFood petFood) throws IOException {
        productService.saveImg(image);
        petFood.setImgURL(productService.saveImg(image));
        productService.save(petFood);
        List<PetFood> list = productService.findAll();
        modelAndView.addObject("foodList", list);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @GetMapping(value = "/hide/{id}")
    public ModelAndView deleteAnimal(@PathVariable Long id){
       ModelAndView modelAndView = new ModelAndView();
       Animal animal = animalService.findById(id);
       animal.setAnimal_status(AnimalStatus.TAKEN);
       animalService.update(animal);
       modelAndView.setViewName("redirect:/admin/allpetsPage");
       return modelAndView;
    }

    @GetMapping(value = "/allpetsPage")
    public ModelAndView allPetsPage(){
        ModelAndView modelAndView = new ModelAndView();
        List<Animal> listOfPets = animalService.findAll();
        modelAndView.addObject("listOfPets", listOfPets);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }

    @GetMapping(value = "/userConfirm")
    public ModelAndView confirmUserPetPage(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findUsersById();
        modelAndView.addObject("usersThatWantPet", users);
        modelAndView.setViewName("ConfirmUserChose");
        return modelAndView;
    }

    @GetMapping(value = "confirmation/{id}/{userid}")
    public ModelAndView confirmAndAddPetToUser(@PathVariable Long id, @PathVariable Long userid, ModelAndView modelAndView){
        Animal animal = animalService.findById(id);
        User user = userService.findById(userid).orElseThrow(RuntimeException::new);
        animal.setUser(user);
        animal.setAnimal_status(AnimalStatus.TAKEN);
        animalService.update(animal);
        modelAndView.setViewName("tem");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("listOfPets", new ArrayList<Animal>());
    }
}
