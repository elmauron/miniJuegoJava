package com.example.minijuegojava.gui;

import javax.swing.*;
import java.awt.*;

public class PanelDeVictoria extends JPanel {

    public PanelDeVictoria(String winnerName, BasicSwingApp mainApp) {
        setLayout(new BorderLayout());
        JLabel victoryLabel = new JLabel("¡" + winnerName + " ha ganado la partida!", SwingConstants.CENTER);
        victoryLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(victoryLabel, BorderLayout.CENTER);

        JButton restartButton = new JButton("Volver al Inicio");
        restartButton.addActionListener(e -> restartGame(mainApp));
        add(restartButton, BorderLayout.SOUTH);
    }

    private void restartGame(BasicSwingApp mainApp) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
        SwingUtilities.invokeLater(() -> new BasicSwingApp().setVisible(true));
    }
}