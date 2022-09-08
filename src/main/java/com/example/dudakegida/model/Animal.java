package com.example.dudakegida.model;

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
    private String animalName;
    @Column(name = "animal_type")
    private AnimalType animalType;
    private int age;
    private String sex;
    private boolean deasise;
    private String imgURL;
    @Column(name = "animal_status")
    @Enumerated(EnumType.STRING)
    private AnimalStatus animal_status;
}
