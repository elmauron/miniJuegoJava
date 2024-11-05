package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Carta;
import com.example.minijuegojava.model.Jugador;
import com.example.minijuegojava.model.Raza;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class PanelDeEnfrentamiento extends JPanel {

    private JTextArea outputArea;
    private List<Jugador> jugadores;
    private PanelDeCreacion panelDeCreacion;
    private JButton startBattleButton;
    private PanelCarta[] panelCartasJugador1;
    private PanelCarta[] panelCartasJugador2;
    private PanelDeBatalla panelDeBatalla;
    private int ronda = 1;

    public PanelDeEnfrentamiento(PanelDeCreacion panelDeCreacion) {
        this.panelDeCreacion = panelDeCreacion;

        // Create components
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        startBattleButton = new JButton("Jugar Ronda!");

        startBattleButton.addActionListener(e -> jugarRonda(jugadores));

        // Add components to the panel
        setLayout(new BorderLayout());
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(startBattleButton, BorderLayout.SOUTH);
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        displayJugadores();
    }

    private void displayJugadores() {
        if (jugadores != null && !jugadores.isEmpty()) {
            outputArea.setText(""); // Clear previous content
            JPanel playersPanel = new JPanel(new GridLayout(1, 2, 10, 10));

            panelCartasJugador1 = new PanelCarta[jugadores.get(0).getCartas().length];
            panelCartasJugador2 = new PanelCarta[jugadores.get(1).getCartas().length];

            for (int i = 0; i < jugadores.size(); i++) {
                int index = 3;
                Jugador jugador = jugadores.get(i);
                JPanel playerPanel = new JPanel(new BorderLayout());
                playerPanel.add(new JLabel("Jugador: " + jugador.getNombre()), BorderLayout.NORTH);

                PanelDeCartas panelCartas = new PanelDeCartas();
                Carta[] cartas = jugador.getCartas();
                for (int j = 0; j < cartas.length; j++) {

                    if (cartas[j].getPersonaje().isVivo()) {
                        PanelCarta panelCarta = new PanelCarta(cartas[j]);
                        panelCartas.add(panelCarta);
                        panelCarta.setBackground(Color.WHITE);
                    } else {
                        PanelCarta panelCarta = new PanelCarta(cartas[j]);
                        panelCartas.add(panelCarta);
                        panelCarta.setBackground(Color.ORANGE);
                    }

                    if (!cartas[j].getPersonaje().isVivo()) {
                        index--;
                        System.out.println("INDEX DE JUGADOR " + jugador.getNombre() + ": " + index);
                    }
                    if (index == 0) {
                        String winnerName = (i == 0) ? jugadores.get(1).getNombre() : jugadores.get(0).getNombre();
                        showVictoryPanel(winnerName);
                        return;
                    }

                }

                playerPanel.add(panelCartas, BorderLayout.CENTER);
                playersPanel.add(playerPanel);
            }

            removeAll();
            add(playersPanel, BorderLayout.CENTER);
            add(startBattleButton, BorderLayout.SOUTH);
            revalidate();
            repaint();
        }
    }

    private void jugarRonda(List<Jugador> jugadores) {
        if (jugadores == null || jugadores.size() < 2) {
            JOptionPane.showMessageDialog(this, "No hay suficientes jugadores para comenzar la batalla.");
            return;
        }

        System.out.println("Cartas de " + jugadores.get(0).getNombre() + ": " + jugadores.get(0).mostrarCartas());
        System.out.println("Cartas de " + jugadores.get(1).getNombre() + ": " + jugadores.get(1).mostrarCartas());

        Jugador jugador1 = jugadores.get(0);
        Jugador jugador2 = jugadores.get(1);
        Carta carta1 = seleccionDeCartas(jugador1);
        Carta carta2 = seleccionDeCartas(jugador2);

        panelDeBatalla = new PanelDeBatalla((Frame) SwingUtilities.getWindowAncestor(this), carta1, carta2);

        SwingUtilities.invokeLater(() -> {
            panelDeBatalla.addComenzarButton();

            System.out.println("Ronda " + ronda);
            System.out.println("Empieza batalla entre " + jugador1.getNombre() + " y " + jugador2.getNombre());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            Jugador primerJugador = Math.random() < 0.5 ? jugador1 : jugador2;
            Jugador segundoJugador = primerJugador.equals(jugador1) ? jugador2 : jugador1;

            panelDeBatalla.addComenzarButtonListener(e -> {
                battleLoop(primerJugador, segundoJugador, carta1, carta2);
            });

            panelDeBatalla.showBattleDialog();
        });
    }

    private void battleLoop(Jugador primerJugador, Jugador segundoJugador, Carta carta1, Carta carta2) {
        panelDeBatalla.addComenzarButtonListener(e -> {
            System.out.println("Comenzando...");
            panelDeBatalla.sumarTurno(panelDeBatalla.getTurno());
            handleTurn(primerJugador, segundoJugador, carta1, carta2);
        });

        panelDeBatalla.showBattleDialog();
    }

    private void handleTurn(Jugador primerJugador, Jugador segundoJugador, Carta carta1, Carta carta2) {
        if (panelDeBatalla.getTurno() % 2 == 0) {
            // Turno del primer jugador
            panelDeBatalla.addAttackButton();
            panelDeBatalla.addAttackButtonListener(e -> {
                atacar(carta1, carta2, segundoJugador);
                if (!verificador(carta1, carta2)) {
                    panelDeBatalla.sumarTurno(panelDeBatalla.getTurno());
                    handleTurn(primerJugador, segundoJugador, carta1, carta2);
                }
            });
        } else {
            // Turno del segundo jugador
            panelDeBatalla.addSerAtacadoButton();
            panelDeBatalla.addSerAtacadoButtonListener(e -> {
                serAtacado(carta1, carta2, primerJugador);
                if (!verificador(carta1, carta2)) {
                    panelDeBatalla.sumarTurno(panelDeBatalla.getTurno());
                    handleTurn(primerJugador, segundoJugador, carta1, carta2);
                }
            });
        }
    }

    public void atacar(Carta carta1, Carta carta2, Jugador currentJugador) {
        System.out.println("---------------------------------------------------");
        System.out.println("Es el turno de " + currentJugador.getNombre());
        System.out.println("---------------------------------------------------");

        double daño = enfrentamiento(carta1, carta2);
        carta2.getPersonaje().restarVida(daño);
        panelDeBatalla.updateCartas(carta1, carta2);
    }

    public void serAtacado(Carta carta1, Carta carta2, Jugador currentJugador) {
        System.out.println("---------------------------------------------------");
        System.out.println("Es el turno de " + currentJugador.getNombre());
        System.out.println("---------------------------------------------------");

        double daño = enfrentamiento(carta2, carta1);
        carta1.getPersonaje().restarVida(daño);
        panelDeBatalla.updateCartas(carta1, carta2);
    }

    public Boolean verificador(Carta carta1, Carta carta2) {
        // Verificar si alguna carta ha muerto

        if (carta1.getPersonaje().getVida() <= 0) {
            JOptionPane.showMessageDialog(panelDeBatalla, carta1.getPersonaje().getNombre() + " ha sido derrotado!");
            carta1.eliminarCarta();
            ronda++;
            panelDeBatalla.closeBattleDialog();
            setJugadores(jugadores);
            return true;
        } else if (carta2.getPersonaje().getVida() <= 0) {
            JOptionPane.showMessageDialog(panelDeBatalla, carta2.getPersonaje().getNombre() + " ha sido derrotado!");
            carta2.eliminarCarta();
            panelDeBatalla.closeBattleDialog();
            ronda++;
            setJugadores(jugadores);
            return true;
        } else if (panelDeBatalla.getTurno() > 13) {
            JOptionPane.showMessageDialog(panelDeBatalla, "La batalla ha terminado en empate.");
            ronda++;
            panelDeBatalla.closeBattleDialog();
            return true;
        }
        return false;
    }



    private double enfrentamiento(Carta carta1, Carta carta2) {

        System.out.println("se enfrentan " + carta1.getPersonaje().getNombre() + " y " + carta2.getPersonaje().getNombre());

        carta1.setPD(carta1.getPersonaje().getDestreza() * carta1.getPersonaje().getFuerza() * carta1.getPersonaje().getNivel());
        carta1.setED ((int) (Math.random() * 100 + 1));
        carta1.setVA(carta1.getPD() * carta1.getED());
        carta1.setPDEF(carta1.getPersonaje().getArmadura() * carta2.getPersonaje().getVelocidad());

        double daño;

        if (carta1.getPersonaje().getRaza() == Raza.HUMANO) {
            daño = ((carta1.getVA() * carta1.getED()) - carta2.getPDEF() / 500) * 100;
        } else if (carta1.getPersonaje().getRaza() == Raza.ELFO) {
            daño = (((carta1.getVA() * carta1.getED()) - carta2.getPDEF() / 500) * 100) * 1.05;
        } else {
            daño = (((carta1.getVA() * carta1.getED()) - carta2.getPDEF() / 500) * 100) * 1.1;
        }

        System.out.println(carta1.getPersonaje().getNombre() + " ataca a " + carta2.getPersonaje().getNombre() + " por " + daño + " de daño.");

        daño = daño / 10000;

        return daño;
    }

    private Carta seleccionDeCartas(Jugador jugador) {
        // Seleccionar una carta aleatoria que tenga vida
        Carta carta = null;
        while (carta == null) {
            int randomIndex = new Random().nextInt(jugador.getCartas().length);
            if (jugador.getCartas()[randomIndex].getPersonaje().isVivo()) {
                carta = jugador.getCartas()[randomIndex];
            } else {
                System.out.println("La carta " + jugador.getCartas()[randomIndex].getPersonaje().getNombre() + " no tiene vida, seleccionando otra carta...");
            } if (!jugador.tieneCartas()) {
                JOptionPane.showMessageDialog(this, "El jugador " + jugador.getNombre() + " no tiene cartas con vida");
                break;
            }
        }
        System.out.println("El sistema sorteo al personaje " + carta.getPersonaje().getNombre() + " para empezar la ronda");
        return carta;
    }

    private void showVictoryPanel(String winnerName) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        BasicSwingApp mainApp = (BasicSwingApp) topFrame;
        topFrame.getContentPane().removeAll();
        topFrame.getContentPane().add(new PanelDeVictoria(winnerName, mainApp));
        topFrame.revalidate();
        topFrame.repaint();
    }
}

