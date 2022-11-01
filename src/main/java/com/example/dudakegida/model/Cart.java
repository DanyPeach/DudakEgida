package com.example.dudakegida.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "Cart")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long userId;

    private int quantity;

    @Column(name = "cart_status")
    @Enumerated
    private CartItemsStatus cartItemsStatus;

    @ManyToOne
    @JoinColumn(name = "pet_food_id", referencedColumnName = "id")
    private PetFood petFood;

    public Cart(long userId, PetFood petFood) {
        this.userId = userId;
        this.petFood = petFood;
    }
}
