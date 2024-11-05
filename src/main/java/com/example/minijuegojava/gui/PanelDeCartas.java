package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelDeCartas extends JPanel {

    public PanelDeCartas() {
        setLayout(new GridLayout(0, 1));
    }

    public void mostrarCartas(List<Carta> cartas) {
        removeAll();
        setLayout(new GridLayout(cartas.size(), 1));

        for (Carta carta : cartas) {
            add(new PanelCarta(carta));
        }

        revalidate();
        repaint();
    }
}