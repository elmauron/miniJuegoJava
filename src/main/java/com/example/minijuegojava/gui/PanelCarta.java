package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Carta;

import javax.swing.*;
import java.awt.*;

public class PanelCarta extends JPanel {

    public PanelCarta(Carta carta) {
        setLayout(new GridLayout(0, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(new JLabel("Nombre: " + carta.getPersonaje().getNombre()));
        add(new JLabel("Raza: " + carta.getPersonaje().getRaza()));
        add(new JLabel("Vida: " + carta.getPersonaje().getVida()));
        add(new JLabel("Velocidad: " + carta.getPersonaje().getVelocidad()));
        add(new JLabel("Fuerza: " + carta.getPersonaje().getFuerza()));
        add(new JLabel("Destreza: " + carta.getPersonaje().getDestreza()));
        add(new JLabel("Armadura: " + carta.getPersonaje().getArmadura()));
        add(new JLabel("Nivel: " + carta.getPersonaje().getNivel()));
    }

    public void updateCarta(Carta carta1) {
        removeAll();
        add(new JLabel("Nombre: " + carta1.getPersonaje().getNombre()));
        add(new JLabel("Raza: " + carta1.getPersonaje().getRaza()));
        add(new JLabel("Vida: " + carta1.getPersonaje().getVida()));
        add(new JLabel("Velocidad: " + carta1.getPersonaje().getVelocidad()));
        add(new JLabel("Fuerza: " + carta1.getPersonaje().getFuerza()));
        add(new JLabel("Destreza: " + carta1.getPersonaje().getDestreza()));
        add(new JLabel("Armadura: " + carta1.getPersonaje().getArmadura()));
        add(new JLabel("Nivel: " + carta1.getPersonaje().getNivel()));

        revalidate();
        repaint();
    }
}