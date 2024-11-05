package com.example.minijuegojava.gui;

import javax.swing.*;
import java.awt.*;

public class PanelDeResultados extends JPanel {

    private JTextArea resultsArea;

    public PanelDeResultados() {
        // Create components
        resultsArea = new JTextArea(20, 50);
        resultsArea.setEditable(false);

        // Add components to the panel
        setLayout(new BorderLayout());
        add(new JScrollPane(resultsArea), BorderLayout.CENTER);
    }

    public void mostrarResultados(String resultados) {
        resultsArea.setText(resultados);
    }
}