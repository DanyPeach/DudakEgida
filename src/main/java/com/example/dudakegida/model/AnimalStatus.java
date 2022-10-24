package com.example.dudakegida.model;

public enum AnimalStatus {
    AFFORDABLE("Affordable"),
    TAKEN("Taken"),
    UNAFFORDABLE("Unaffordable"),
    CHECKING("Checking");

    private String name;

    AnimalStatus(String name) {
        this.name = name;
    }
}
