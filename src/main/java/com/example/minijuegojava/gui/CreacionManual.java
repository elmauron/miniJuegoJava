package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Personaje;
import com.example.minijuegojava.model.Raza;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CreacionManual extends JDialog {

    private JTextField nombreField;
    private JTextField apodoField;
    private JTextField nacimientoField;
    private JComboBox<Raza> razaComboBox;
    private JTextField edadField;
    private Personaje personaje;

    public CreacionManual(Frame owner) {
        super(owner, "Crear Personaje", true);
        setLayout(new GridLayout(6, 2));

        // Create components
        nombreField = new JTextField(20);
        apodoField = new JTextField(20);
        nacimientoField = new JTextField(20);
        razaComboBox = new JComboBox<>(Raza.values());
        edadField = new JTextField(20);

        // Add components to dialog
        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Apodo:"));
        add(apodoField);
        add(new JLabel("Fecha de Nacimiento (dd/mm/yyyy):"));
        add(nacimientoField);
        add(new JLabel("Raza:"));
        add(razaComboBox);
        add(new JLabel("Edad:"));
        add(edadField);

        JButton createButton = new JButton("Crear");
        createButton.addActionListener(e -> createPersonaje());
        add(createButton);

        pack();
        setLocationRelativeTo(owner);
    }

    private void createPersonaje() {
        Random random = new Random();

        String nombre = nombreField.getText();
        String apodo = apodoField.getText();
        String nacimiento = nacimientoField.getText();
        Raza raza = (Raza) razaComboBox.getSelectedItem();
        int edad = Integer.parseInt(edadField.getText());

        int vida = 100;
        int velocidad = random.nextInt(11); // de 0 a 10
        int fuerza = random.nextInt(11); // de 0 a 10
        int destreza = random.nextInt(6); // de 0 a 5
        int armadura = random.nextInt(11); // de 0 a 10
        int nivel = random.nextInt(11); // de 0 a 10

        personaje = new Personaje(nombre, apodo, nacimiento, raza, edad, vida, velocidad, fuerza, destreza, armadura, nivel);
        dispose();
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}