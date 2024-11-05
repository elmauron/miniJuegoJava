package com.example.minijuegojava.model;

import java.util.Random;

public enum Raza {
    ORCO,
    HUMANO,
    ELFO;

    public static Raza getRandomRaza() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
