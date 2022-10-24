package com.example.dudakegida.controller;

import com.example.dudakegida.model.FoodType;
import com.example.dudakegida.model.PetFood;
import com.example.dudakegida.service.AnimalService;
import com.example.dudakegida.service.ProductService;
import com.example.dudakegida.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final UserService userService;
    private final ProductService productService;

    public ProductController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/allproducts")
    public ModelAndView showAllProducts(ModelAndView modelAndView){
        List<PetFood> petFoodList = productService.allFood();
        modelAndView.addObject("foodList", petFoodList);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @GetMapping("/takeProduct/{id}")
    public ModelAndView takeProduct(ModelAndView modelAndView, @PathVariable Long id){
        PetFood petFood = productService.findById(id);
        modelAndView.addObject("petFood", petFood);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @GetMapping("/showProduct/{type}")
    public ModelAndView showExactProduct(ModelAndView modelAndView, @PathVariable int type){
        List<PetFood> petFoodList = productService.allFood();
        List<PetFood> petFoodListFinal = switch (type) {
            case 0 -> petFoodList
                    .stream()
                    .filter(pet -> pet.getFoodType().equals(FoodType.FOR_BIG_DOGS)
                            || pet.getFoodType().equals(FoodType.FOR_MEDIUM_DOGS)
                            || pet.getFoodType().equals(FoodType.FOR_SMALL_DOGS))
                    .toList();
            case 1 -> petFoodList
                    .stream()
                    .filter(pet -> pet.getFoodType().equals(FoodType.FOR_CATS))
                    .toList();
            case 2 -> petFoodList
                    .stream()
                    .filter(pet -> pet.getFoodType().equals(FoodType.FOR_BIRDS))
                    .toList();
            case 4 -> petFoodList
                    .stream()
                    .filter(pet -> pet.getFoodType().equals(FoodType.SWEETS_FOR_PETS))
                    .toList();
            case 5 -> petFoodList
                    .stream()
                    .filter(pet -> pet.getFoodType().equals(FoodType.STUFF))
                    .toList();
            default -> petFoodList;
        };
        modelAndView.addObject("foodList", petFoodListFinal);
        modelAndView.setViewName("products");
        return modelAndView;

    }


}
