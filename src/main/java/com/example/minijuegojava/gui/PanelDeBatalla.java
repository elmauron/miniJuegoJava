package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Carta;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class PanelDeBatalla extends JPanel {
    private PanelCarta panelCarta1;
    private PanelCarta panelCarta2;
    private JDialog battleDialog;
    private JButton comenzarButton;
    private JButton attackButton;
    private JButton serAtacadoButton;
    int turno = 0;

    public PanelDeBatalla(Frame parentFrame, Carta carta1, Carta carta2) {
        setLayout(new GridLayout(1, 2));
        panelCarta1 = new PanelCarta(carta1);
        panelCarta2 = new PanelCarta(carta2);
        add(panelCarta1);
        add(panelCarta2);

        battleDialog = new JDialog(parentFrame, "Jugar Ronda", true);
        battleDialog.setLayout(new BorderLayout());
        battleDialog.setSize(600, 400);
        battleDialog.setLocationRelativeTo(parentFrame);
        battleDialog.add(this, BorderLayout.CENTER);
    }

    public int getTurno() {
        return turno;
    }

    public void sumarTurno(int turno) {
        this.turno = turno + 1;
        System.out.println("TURNO NUMERO: " + this.getTurno());    }

    public void showBattleDialog() {
        battleDialog.setVisible(true);
    }

    public void closeBattleDialog() {
        battleDialog.dispose();
    }

    public void addComenzarButton() {
        comenzarButton = new JButton("Comenzar");
        battleDialog.add(comenzarButton, BorderLayout.SOUTH);
    }

    public void addAttackButton() {
        attackButton = new JButton("Atacar");
        battleDialog.add(attackButton, BorderLayout.SOUTH);
        if (comenzarButton != null) {
            battleDialog.remove(comenzarButton);
        }
        if (serAtacadoButton != null) {
            battleDialog.remove(serAtacadoButton);
        }
    }

    public void addSerAtacadoButton() {
        if (comenzarButton != null) {
            battleDialog.remove(comenzarButton);
        }
        if (attackButton != null) {
            battleDialog.remove(attackButton);
        }
        serAtacadoButton = new JButton("Ser Atacado");
        battleDialog.add(serAtacadoButton, BorderLayout.SOUTH);
    }

    public void updateCartas(Carta carta1, Carta carta2) {
        panelCarta1.updateCarta(carta1);
        panelCarta2.updateCarta(carta2);
    }

    public void addAttackButtonListener(ActionListener listener) {
        attackButton.addActionListener(listener);
    }

    public void addSerAtacadoButtonListener(ActionListener listener) {
        serAtacadoButton.addActionListener(listener);
    }

    public void addComenzarButtonListener(ActionListener listener) {
        comenzarButton.addActionListener(listener);
    }



}