package com.example.dudakegida.controller;

import com.example.dudakegida.model.*;
import com.example.dudakegida.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AnimalService animalService;
    private final ProductService productService;
    private final CartService cartService;
    private final MessageService messageService;

    public UserController(UserService userService, AnimalService animalService, ProductService productService, CartService cartService, MessageService messageService) {
        this.userService = userService;
        this.animalService = animalService;
        this.productService = productService;
        this.cartService = cartService;
        this.messageService = messageService;
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

    @GetMapping("/addbalance")
    public ModelAndView addBalancePage(ModelAndView modelAndView, Authentication authentication){
        modelAndView.setViewName("balance");
        return modelAndView;
    }

    @PostMapping("/addbalance")
    public ModelAndView addBalance(ModelAndView modelAndView, Authentication authentication,
                                   @RequestParam(name = "money") String money){
        User user = getUser(authentication);
        user.setBalance(user.getBalance() + Double.parseDouble(money));
        userService.update(user);
        Map<PetFood, Integer> petFoodMap = getMap(authentication);
        modelAndView.addObject("userCarts", petFoodMap);
        modelAndView.addObject("userBalance", getUser(authentication).getBalance());
        modelAndView.addObject("totalPrice", cartService.getTotalPriceOfUserCart(getUser(authentication)));
        modelAndView.setViewName("cart");
        return modelAndView;
    }


    @GetMapping("/byeItems")
    public ModelAndView byeItems(ModelAndView modelAndView, Authentication authentication){
        User user = getUser(authentication);
        if(user.getBalance()-cartService.getTotalPriceOfUserCart(getUser(authentication))<0){
            modelAndView.setViewName("balance");
            return modelAndView;
        }
        user.setBalance(user.getBalance()-cartService.getTotalPriceOfUserCart(getUser(authentication)));
        userService.update(user);
        List<Cart> cartList = cartService.findCartsByUsername(getUser(authentication));
        for(var i : cartList){
            i.setCartItemsStatus(CartItemsStatus.SELL);
            cartService.update(i);
        }
        List<Cart> res = cartService.findCartsByUsername(user)
                .stream()
                .filter(p -> p.getCartItemsStatus().equals(CartItemsStatus.SELL))
                .toList();
        List<PetFood> petFoodList =
                cartService.formatingCartToProduct(res);
        modelAndView.addObject("userproducts", petFoodList);
        modelAndView.addObject("time", LocalDate.now().plusDays(2));
        modelAndView.setViewName("userproducts");
        return modelAndView;
    }

    @GetMapping("/showProducts")
    public ModelAndView showUserProductsPage(ModelAndView modelAndView, Authentication authentication){
        List<Cart> res = cartService.findCartsByUsername(getUser(authentication))
                .stream()
                .filter(p -> p.getCartItemsStatus().equals(CartItemsStatus.SELL))
                .toList();
        List<PetFood> petFoodList =
                cartService.formatingCartToProduct(res);
        modelAndView.addObject("userproducts", petFoodList);
        modelAndView.addObject("time", LocalDate.now().plusDays(2));
        modelAndView.setViewName("userproducts");
        return modelAndView;
    }

    private User getUser(Authentication authentication){
        String username = authentication.getName();
        return userService.findByLogin(username);
    }


private Map<PetFood, Integer> getMap(Authentication authentication) {
    List<Cart> listCart = cartService.findCartsByUsername(getUser(authentication));
    List<PetFood> petFoodList = cartService.formatingCartToProduct(listCart);
    Map<PetFood, Integer> petFoodMap = new HashMap<>();
    for(int i = 0; i<listCart.size(); i++){
        petFoodMap.put(petFoodList.get(i), listCart.get(i).getQuantity());
    }
    return petFoodMap;
}
}
