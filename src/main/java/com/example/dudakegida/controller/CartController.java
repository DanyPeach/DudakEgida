package com.example.dudakegida.controller;

import com.example.dudakegida.model.Cart;
import com.example.dudakegida.model.CartItemsStatus;
import com.example.dudakegida.model.PetFood;
import com.example.dudakegida.model.User;
import com.example.dudakegida.service.CartService;
import com.example.dudakegida.service.ProductService;
import com.example.dudakegida.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;

    public CartController(ProductService productService, UserService userService, CartService cartService) {
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/takeProduct/{product_id}")
    public ModelAndView moveProductToCart(@PathVariable Long product_id,
                                          ModelAndView modelAndView, Authentication authentication){
        PetFood product = productService.findById(product_id);
        cartService.add(product, getUser(authentication));
        List<Cart> list = cartService.findCartsByUsername(getUser(authentication));
        Map<PetFood, Integer> petFoodMap = getMap(authentication);
        modelAndView.addObject("userCarts", petFoodMap);
        modelAndView.addObject("userBalance", getUser(authentication).getBalance());

        modelAndView.addObject("totalPrice", cartService.getTotalPriceOfUserCart(getUser(authentication)));
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @GetMapping("/showMyCart")
    public  ModelAndView showUserCart(ModelAndView modelAndView, Authentication authentication){
        List<Cart> list = cartService.findCartsByUsername(getUser(authentication));
        Map<PetFood, Integer> petFoodMap = getMap(authentication);
        modelAndView.addObject("userCarts", petFoodMap);
        modelAndView.addObject("userBalance", getUser(authentication).getBalance());
        modelAndView.addObject("totalPrice", cartService.getTotalPriceOfUserCart(getUser(authentication)));
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @GetMapping("/deleteItemFromCart/{cartid}")
    public ModelAndView deleteItemFromCart(ModelAndView modelAndView, @PathVariable Long cartid, Authentication authentication){
        User user = getUser(authentication);
        cartService.deleteCartByUsernameAndProductId(user, cartid);
        List<Cart> list = cartService.findCartsByUsername(getUser(authentication));
        Map<PetFood, Integer> petFoodMap = getMap(authentication);
        modelAndView.addObject("userCarts", petFoodMap);
        modelAndView.addObject("userBalance", getUser(authentication).getBalance());
        modelAndView.addObject("totalPrice", cartService.getTotalPriceOfUserCart(getUser(authentication)));
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @GetMapping("/editproductweight/{cartid}")
    public ModelAndView editProductWeight(ModelAndView modelAndView, @PathVariable Long cartid){
        PetFood petFood = productService.findById(cartid);
        modelAndView.addObject("cartObj", petFood);
        modelAndView.setViewName("editWeight");
        return modelAndView;
    }

    @GetMapping("/chacngeQuantityPlus/{cartid}")
    public ModelAndView changeQuantity(ModelAndView modelAndView, Authentication authentication,
             @PathVariable Long cartid){
        List<Cart> list = cartService.findCartsByUsername(getUser(authentication));
        Cart cart = cartService.findCartsByUsername(getUser(authentication)).stream()
                .filter(cart1 -> cart1.getPetFood().getId() == cartid)
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
        cartService.plusOrMinusItem(cart, 1);

        Map<PetFood, Integer> petFoodMap = getMap(authentication);
        modelAndView.addObject("userCarts", petFoodMap);
        modelAndView.addObject("userBalance", getUser(authentication).getBalance());
        modelAndView.addObject("totalPrice", cartService.getTotalPriceOfUserCart(getUser(authentication)));
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @GetMapping("/chacngeQuantityMinus/{cartid}")
    public ModelAndView changeQuantityMinus(ModelAndView modelAndView, Authentication authentication,
                                       @PathVariable Long cartid){

        Cart cart = cartService.findCartsByUsername(getUser(authentication)).stream()
                .filter(cart1 -> cart1.getPetFood().getId() == cartid)
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
        cartService.plusOrMinusItem(cart, 2);
        List<Cart> list = cartService.findCartsByUsername(getUser(authentication));

        Map<PetFood, Integer> petFoodMap = getMap(authentication);

        modelAndView.addObject("userCarts", petFoodMap);
        modelAndView.addObject("userBalance", getUser(authentication).getBalance());
        modelAndView.addObject("totalPrice", cartService.getTotalPriceOfUserCart(getUser(authentication)));
        modelAndView.setViewName("cart");
        return modelAndView;
    }


    private User getUser(Authentication authentication){
        String username = authentication.getName();
        return userService.findByLogin(username);
    }

    private Map<PetFood, Integer> getMap(Authentication authentication) {
        List<Cart> listCart = cartService.findCartsByUsername(getUser(authentication))
                .stream()
                .filter(p -> p.getCartItemsStatus().equals(CartItemsStatus.INCART))
                .toList();
        List<PetFood> petFoodList = cartService.formatingCartToProduct(listCart);
        Map<PetFood, Integer> petFoodMap = new HashMap<>();
        for(int i = 0; i<listCart.size(); i++){
            petFoodMap.put(petFoodList.get(i), listCart.get(i).getQuantity());
        }
        return petFoodMap;
    }
}
