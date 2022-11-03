package com.example.dudakegida.service;

import com.example.dudakegida.model.Cart;
import com.example.dudakegida.model.PetFood;
import com.example.dudakegida.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CartService {
    void add(PetFood product, User user);

    List<Cart> all(long userId);

    void delete(Cart cart);

    List<Cart> findCartsByUsername(User authUser);

    void deleteCartByUsernameAndProductId(User user, long product_id);

    List<PetFood> formatingCartToProduct(List<Cart> list);

    double getTotalPriceOfUserCart(User user);

    int getAmountOfItemsInUserCart(User user);

    void plusOrMinusItem(Cart cart, int bo);

    void changeTotalPrice(int a);

    Cart findById(Long id);

    void update(Cart cart);

    String checkBalance(User user);
}
