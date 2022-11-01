package com.example.dudakegida.model;

public enum MessageType {
    INFO("Information_post"),
    SOS("Extra message"),
    FINDING("Find homeless pet"),
    OVEREXPOSURE("Perederjka");

    private String name;

    MessageType(String name) {
        this.name = name;
    }
}
