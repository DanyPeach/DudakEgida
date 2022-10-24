package com.example.dudakegida.controller;

import com.example.dudakegida.model.*;
import com.example.dudakegida.service.AnimalService;
import com.example.dudakegida.service.ProductService;
import com.example.dudakegida.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AnimalService animalService;
    private final ProductService productService;


    public UserController(UserService userService, AnimalService animalService, ProductService productService) {
        this.userService = userService;
        this.animalService = animalService;
        this.productService = productService;
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
        List<Animal> randomThree = animalService.findRandomThree();
        modelAndView.addObject("randomThree", randomThree);
        modelAndView.setViewName("tem");
        return modelAndView;
    }


    @GetMapping("/home")
    public ModelAndView userList(ModelAndView modelAndView){
        List<Animal> randomThree = animalService.findRandomThree();
        modelAndView.addObject("randomThree", randomThree);
        modelAndView.setViewName("tem");
        return modelAndView;
    }

    @GetMapping("/allpets")
    public ModelAndView allPets(ModelAndView modelAndView){
        List<Animal> listOfPets = animalService.findAll();
        List<Animal> listOfPets1 = listOfPets.stream().filter(pet -> pet.getAnimal_status()
                .equals(AnimalStatus.AFFORDABLE) || pet.getAnimal_status().equals(AnimalStatus.CHECKING))
                .toList();
        modelAndView.addObject("listOfPets", listOfPets1);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }

//    @GetMapping("/randomThreePets")
//    public ModelAndView randomThreePets(ModelAndView modelAndView){
//        List<Animal> randomThree = animalService.findRandomThree();
//        modelAndView.addObject("randomThree", randomThree);
//        modelAndView.setViewName("allpets");
//        return modelAndView;
//    }


    @GetMapping("/showing/{type}")
    public ModelAndView showByType(ModelAndView modelAndView, @PathVariable int type){
        List<Animal> listOfPets = animalService.findByType(type);
        modelAndView.addObject("listOfPets", listOfPets);
        modelAndView.setViewName("allpets");
        return modelAndView;
    }

    @GetMapping("/moreInfoAboutPet/{id}")
    public ModelAndView moreInfoShow(ModelAndView modelAndView, @PathVariable Long id){
        Animal animal = animalService.findById(id);
        modelAndView.addObject("animalInfo", animal);
        modelAndView.setViewName("petInfo");
        return modelAndView;
    }

//    @GetMapping("/takePet/{pet_id}")
//    public ModelAndView takePet(@PathVariable String pet_id, Authentication authentication){
//        ModelAndView modelAndView = new ModelAndView();
//        userService.setChosen_pet_id(Long.decode(pet_id));
//        modelAndView.setViewName("confirmePet");
//        return modelAndView;
//    }
//
//    @PostMapping("/confirmePetPassword")
//    public ModelAndView confirme(@ModelAttribute User user, Authentication authentication){
//        ModelAndView modelAndView = new ModelAndView();
//        if(userService.confirmeChosenPet(user, authentication)){
//            userService.takeChosenPet(userService.getChosenPetId(), authentication);
//            modelAndView.addObject("randomThree", animalService.findRandomThree());
//            modelAndView.setViewName("tem");
//        }else{
//            modelAndView.setViewName("");
//        }
//        return modelAndView;
//    }

//    @GetMapping("/myPets")
//    public ModelAndView myPets(Authentication authentication){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("userPets",
//                animalService.findPetsByUserId(getUser(authentication).getId()));
//        modelAndView.setViewName("UserPets");
//        return modelAndView;
//    }

    private User getUser(Authentication authentication){
        String username = authentication.getName();
        return userService.findByLogin(username);
    }

//    @GetMapping("/pickPet/{id}")
//    public ModelAndView pickPetForUser(ModelAndView modelAndView, @PathVariable long id, @ModelAttribute User user){
//        Animal animal = animalService.findById(id);
//        modelAndView.addObject("chosenPet", animal);
//        List<Animal> userpets = user.getPets();
//        userpets.add(animal);
//        user.setPets(userpets);
//        modelAndView.setViewName("");
//        return modelAndView;
//    }


//    @ModelAttribute
//    public void addAttributes(Model model){
//        model.addAttribute("listOfPets", new ArrayList<Animal>());
//        model.addAttribute("user", new User());
//    }
}
