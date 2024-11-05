package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Carta;
import com.example.minijuegojava.model.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelJugador extends JPanel {

    private Jugador jugador;
    private Color originalColor;


    public PanelJugador(Jugador jugador, PanelDeCreacion parentPanel) {
        this.jugador = jugador;
        setLayout(new GridLayout(0, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(new JLabel("Nombre: " + jugador.getNombre()));
        add(new JLabel("Partidas Ganadas: " + jugador.getPartidasGanadas()));
        add(new JLabel("Partidas Perdidas: " + jugador.getPartidasPerdidas()));
        add(new JLabel("Cartas:"));
        for (Carta carta : jugador.getCartas()) {
            add(new JLabel(" - " + carta.getPersonaje().getNombre() + " (Vida: " + carta.getPersonaje().getVida() + ")"));
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentPanel.displayCartasJugador(jugador);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(182, 132, 50)); // Change to desired hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(195, 120, 13)); // Revert to original color
            }
        });
    }
}