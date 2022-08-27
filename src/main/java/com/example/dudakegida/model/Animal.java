package com.example.dudakegida.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "pets")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;
    @Column(name = "animal_name")
    private String animalName;
    @Column(name = "animal_type")
    private AnimalType animalType;
    private int age;
    private String sex;
    private boolean deasise;
    private String imgURL;

    public Long getAnimalId() {
        return animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public boolean isDeasise() {
        return deasise;
    }

    public String getImgURL() {
        return imgURL;
    }
}
