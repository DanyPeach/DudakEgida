package com.example.dudakegida.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "pets")
@Getter
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private boolean deasise;

    @NotNull
    private String imgURL;

    @Column(name = "animal_status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AnimalStatus animal_status;

    @ManyToOne
    private User user;

    public Animal(Long animalId, String animalName, AnimalType animal_type, double weight, int age, String sex, boolean deasise, String imgURL, AnimalStatus animal_status) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.animal_type = animal_type;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.deasise = deasise;
        this.imgURL = imgURL;
        this.animal_status = animal_status;
    }
}
