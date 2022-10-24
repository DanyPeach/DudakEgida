package com.example.dudakegida.model;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "pets")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long animalId;

    @Column(name = "animal_name")
    @NotNull
    private String animalName;

    @Column(name = "animal_type")
    @NotNull
    private AnimalType animal_type;

    @NotNull
    private double weight;

    @NotNull
    private int age;

    @NotNull
    private String sex;

    @NotNull
    private String description;

    @NotNull
    private String imgURL;

    @Column(name = "animal_status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AnimalStatus animal_status;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "user_confirming",
            joinColumns = @JoinColumn(name = "pets_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userChose;

    public Animal(Long animalId, String animalName, AnimalType animal_type, double weight, int age, String sex, String description, String imgURL, AnimalStatus animal_status) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.animal_type = animal_type;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.description = description;
        this.imgURL = imgURL;
        this.animal_status = animal_status;
    }

    public Animal() {

    }

    public Animal(Long animalId, String animalName, AnimalType animal_type, double weight, int age, String sex, String description, String imgURL, AnimalStatus animal_status, User user) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.animal_type = animal_type;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.description = description;
        this.imgURL = imgURL;
        this.animal_status = animal_status;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        if (Double.compare(animal.getWeight(), getWeight()) != 0) return false;
        if (getAge() != animal.getAge()) return false;
        if (!getAnimalId().equals(animal.getAnimalId())) return false;
        return  (getAnimal_status() == animal.getAnimal_status());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getAnimalId().hashCode();
        return result;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public AnimalType getAnimal_type() {
        return animal_type;
    }

    public void setAnimal_type(AnimalType animal_type) {
        this.animal_type = animal_type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDeasise(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public AnimalStatus getAnimal_status() {
        return animal_status;
    }

    public void setAnimal_status(AnimalStatus animal_status) {
        this.animal_status = animal_status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getUserChose() {
        return userChose;
    }

    public void setUserChose(Set<User> userChose) {
        this.userChose = userChose;
    }
}
