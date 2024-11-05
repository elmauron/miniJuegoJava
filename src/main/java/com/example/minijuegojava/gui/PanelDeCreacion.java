package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Jugador;
import com.example.minijuegojava.model.Carta;
import com.example.minijuegojava.model.Personaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelDeCreacion extends JPanel {

    private JTextField invocadorNameField;
    private JTextArea outputArea;
    private PanelDeCartas panelDeCartas;
    private BasicSwingApp parentApp;


    public PanelDeCreacion(BasicSwingApp parentApp) {
        // Create components
        invocadorNameField = new JTextField(20);
        JButton createPlayersButton = new JButton("Crear Jugadores");
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        panelDeCartas = new PanelDeCartas(); // Initialize panelDeCartas

        // Add action listener to the button
        createPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String invocadorName = invocadorNameField.getText();
                if (!invocadorName.isEmpty()) {
                    List<Jugador> jugadores = creacionDeJugadores(invocadorName);
                    displayJugadores(outputArea, jugadores);
                    parentApp.setJugadores(jugadores); // Notify parent app
                    parentApp.irEnfrentamiento(); // Switch to "Batalla" tab
                } else {
                    outputArea.setText("Por favor, ingrese un nombre de invocador.");
                }
            }
        });

        // Create a panel and add components to it
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nombre de Invocador:"));
        inputPanel.add(invocadorNameField);
        inputPanel.add(createPlayersButton);

        // Add input panel and output area to the main panel
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    private List<Jugador> creacionDeJugadores(String nombreJugador1) {
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador(nombreJugador1, 0, 0, new Carta[3]);

        String[] options = {"Aleatorio", "Manual"};
        String eleccion = (String) JOptionPane.showInputDialog(this,
                "¿Desea crear sus personajes aleatorios o manualmente?",
                "Seleccionar",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        for (int i = 0; i < jugador1.getCartas().length; i++) {
            if ("Aleatorio".equalsIgnoreCase(eleccion)) {
                jugador1.getCartas()[i] = new Carta(Personaje.crearPersonajeAleatorio());
            } else if ("Manual".equalsIgnoreCase(eleccion)) {
                CreacionManual dialog = new CreacionManual((Frame) SwingUtilities.getWindowAncestor(this));
                dialog.setVisible(true);
                jugador1.getCartas()[i] = new Carta(dialog.getPersonaje());
            }
        }
        jugador1.mostrarCartas();

        // Creacion jugador2
        Jugador jugador2 = new Jugador(Jugador.generarNombreAleatorio(), 0, 0, new Carta[3]);

        for (int i = 0; i < jugador2.getCartas().length; i++) {
            jugador2.getCartas()[i] = new Carta(Personaje.crearPersonajeAleatorio());
        }
        jugador2.mostrarCartas();

        jugadores.add(jugador1);
        jugadores.add(jugador2);

        return jugadores;
    }

    public static void displayJugadores(JTextArea outputArea, List<Jugador> jugadores) {
        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(jugadores.size(), 1, 10, 10)); // 10px gap between rows

        for (Jugador jugador : jugadores) {
            playersPanel.add(new PanelJugador(jugador, null));
        }

        outputArea.setText(""); // Clear the text area
        outputArea.setLayout(new BorderLayout());
        outputArea.add(new JScrollPane(playersPanel), BorderLayout.CENTER);
        outputArea.revalidate();
        outputArea.repaint();
    }

    public void displayCartasJugador(Jugador jugador) {
        panelDeCartas.mostrarCartas(List.of(jugador.getCartas()));
        JOptionPane.showMessageDialog(this, panelDeCartas, "Cartas de " + jugador.getNombre(), JOptionPane.PLAIN_MESSAGE);
    }
}