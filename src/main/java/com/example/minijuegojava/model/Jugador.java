package com.example.minijuegojava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jugador {
    private String nombre;
    private int partidasGanadas;
    private int partidasPerdidas;
    private Carta[] cartas = new Carta[3];
    private int turno;

    public Jugador(String nombre, int partidasGanadas, int partidasPerdidas, Carta[] cartas) {
        this.nombre = nombre;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
        this.cartas = cartas;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }
    public Carta[] getCartas() {
        return cartas;
    }
    public void setCartas(Carta[] cartas) {
        this.cartas = cartas;
    }

    public boolean tieneCartas() {
        for (Carta carta : cartas) {
            if (carta != null) {
                return true;
            }
        }
        return false;
    }

    public String mostrarCartas() {
        List<String> nombres = new ArrayList<>();
        for (Carta carta : cartas) {
            if (carta != null) {
                nombres.add(carta.getPersonaje().getNombre());
            }
        }
        return nombres.toString();
    }

    public static String generarNombreAleatorio() {
        String[] nombres = {"Thor", "Loki", "Odin", "Freya", "Baldur", "Frigg", "Heimdall", "Sif", "Vidar", "Tyr"};
        Random random = new Random();
        return nombres[random.nextInt(nombres.length)];
    }

    public void removeCarta(Carta carta) {
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i] != null && cartas[i].equals(carta)) {
                cartas[i] = null;
                break;
            }
        }
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getTurno() {
        return turno;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", partidasGanadas=" + partidasGanadas +
                ", partidasPerdidas=" + partidasPerdidas +
                '}';
    }
}
