package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Jugador;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BasicSwingApp extends JFrame {

    private PanelDeEnfrentamiento panelDeEnfrentamiento;
    private JTabbedPane tabbedPane;

    static {
        // Redirect console output to a file in the logs directory with a timestamp
        try {
            String projectDir = System.getProperty("user.dir");
            File logDir = new File(projectDir, "logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            String timestamp = new SimpleDateFormat("HH_mm-dd_MM_YY").format(new Date());
            PrintStream myconsole = new PrintStream(new FileOutputStream(new File(logDir, "minijuegolog_" + timestamp + ".txt")), true);
            System.setOut(myconsole);
            System.setErr(myconsole);
            System.out.println("Reiniciando Juego! Por favor aguarde...");
        } catch (FileNotFoundException fx) {
            System.err.println("File not found: " + fx.getMessage());
        }
    }

    public BasicSwingApp() {
        setTitle("Mini Juego Java");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Pestañas
        tabbedPane = new JTabbedPane();

        // Paneles
        PanelDeCreacion panelDeCreacion = new PanelDeCreacion(this);
        panelDeEnfrentamiento = new PanelDeEnfrentamiento(panelDeCreacion);

        tabbedPane.addTab("Inicio", panelDeCreacion);
        tabbedPane.addTab("Batalla", panelDeEnfrentamiento);

        // Agregar panel de pestañas al frame
        add(tabbedPane, BorderLayout.CENTER);

        // Frame visible
        setVisible(true);
    }

    public void setJugadores(List<Jugador> jugadores) {
        panelDeEnfrentamiento.setJugadores(jugadores);
    }

    public void irEnfrentamiento() {
        tabbedPane.setSelectedIndex(1); // Index of the "Batalla" tab
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicSwingApp());
    }
}