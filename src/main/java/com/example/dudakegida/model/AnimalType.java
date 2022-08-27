package com.example.dudakegida.model;

public enum AnimalType {
    DOG{
        @Override
        public String toString() {
            return "Gog";
        }
    },

    CAT{
        @Override
        public String toString() {
            return "Cat";
        }
    },

    BIRD{
        @Override
        public String toString() {
            return "Bird";
        }
    },

    RODENT{
        @Override
        public String toString() {
            return "Rodent";
        }
    },

    SCALY{
        @Override
        public String toString() {
            return "Scaly";
        }
    },

    OTHERS{
        @Override
        public String toString() {
            return "Others";
        }
    }
}
