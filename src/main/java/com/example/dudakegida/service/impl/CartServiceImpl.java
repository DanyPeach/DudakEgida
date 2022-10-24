package com.example.dudakegida.service.impl;

import com.example.dudakegida.model.Cart;
import com.example.dudakegida.model.PetFood;
import com.example.dudakegida.model.User;
import com.example.dudakegida.repository.CartRepository;
import com.example.dudakegida.repository.UserRepository;
import com.example.dudakegida.service.CartService;
import com.example.dudakegida.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(PetFood petFood, User user) {
        List<Cart> cartList = findCartsByUsername(user);
        List<PetFood> productList = new ArrayList<>();
        for (Cart cart : cartList) {
            productList.add(cart.getPetFood());
        }
        if (!productList.contains(petFood)) {
            Cart cart = new Cart(user.getId(), petFood);
            cart.setQuantity(cart.getQuantity()+1);
            cartRepository.save(cart);
        }
    }

    @Override
    public List<Cart> all(long userId) {
        return null;
    }

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public List<Cart> findCartsByUsername(User authUser) {
        return cartRepository.findCartByUserId(authUser.getId());
    }

    @Override
    public List<PetFood> formatingCartToProduct(List<Cart> cartList){
        List<PetFood> list = new ArrayList<>();
        for(var i : cartList){
            list.add(i.getPetFood());
        }
        return list;
    }

    @Override
    public double getTotalPriceOfUserCart(User user) {
        double totalPrice = 0;

        List<Cart> list = cartRepository.findCartByUserId(user.getId());
        List<PetFood> list1 = formatingCartToProduct(list);

        for(int i = 0; i< list1.size(); i++){
            totalPrice+=list1.get(i).getPrice()*list.get(i).getQuantity();
        }
        return totalPrice;
    }

    @Override
    public int getAmountOfItemsInUserCart(User user) {
        return 0;
    }

    @Override
    public void plusOrMinusItem(Cart cart, int bo) {
        switch (bo) {
            case 1 -> {
                cart.setQuantity(cart.getQuantity() + 1);
                cartRepository.save(cart);
            }
            case 2 -> {
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.save(cart);
                if (cart.getQuantity() <= 0) {
                    cartRepository.delete(cart);
                }
            }
        }
    }


    @Override
    public void changeTotalPrice(int a) {
        if(a == 1){

        }
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(new Cart());
    }

    @Override
    public void update(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartByUsernameAndProductId(User user, long product_id) {
        Cart res = cartRepository.findAll().stream()
                .filter(cart -> cart.getPetFood().getId() == product_id &&
                        cart.getUserId() == user.getId())
                .findFirst()
                .orElse(new Cart());
        cartRepository.delete(res);
    }
}
