package com.example.dudakegida.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
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
}
