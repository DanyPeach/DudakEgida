package com.example.dudakegida.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "pet_food")
public class PetFood {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private FoodType foodType;

    @NotNull
    private double price;

    @NotNull
    private String mark;

    @NotNull
    private String imgURL;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetFood)) return false;

        PetFood petFood = (PetFood) o;

        if (getId() != petFood.getId()) return false;
        if (Double.compare(petFood.getPrice(), getPrice()) != 0) return false;
        if (getName() != null ? !getName().equals(petFood.getName()) : petFood.getName() != null) return false;
        if (getFoodType() != petFood.getFoodType()) return false;
        return getMark() != null ? getMark().equals(petFood.getMark()) : petFood.getMark() == null;
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
