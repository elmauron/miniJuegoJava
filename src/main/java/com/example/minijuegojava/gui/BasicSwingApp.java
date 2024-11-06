package com.example.minijuegojava.gui;

import com.example.minijuegojava.model.Jugador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            String timestamp = new SimpleDateFormat("HH_mm_ss-dd_MM_YY").format(new Date());
            PrintStream myconsole = new PrintStream(new FileOutputStream(new File(logDir, "minijuegolog_" + timestamp + ".txt")), true);
            System.setOut(myconsole);
        } catch (FileNotFoundException fx) {
            System.err.println("File not found: " + fx.getMessage());
        }
    }

    public BasicSwingApp() {
        setTitle("Mini Juego Java");
        setSize(700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        SoundPlayer.playSound("Ambiente.wav");

        // Set the JFrame to be undecorated
        setUndecorated(true);

        // Create a custom JPanel with a border
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new LineBorder(new Color(0xE0B65E), 10)); // Change the color and thickness as needed

        // Create a custom title bar with a close button
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(0xE0B65E));
        JButton closeButton = new JButton("X");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        titleBar.add(closeButton, BorderLayout.EAST);

        // Add the custom title bar to the content pane
        contentPane.add(titleBar, BorderLayout.NORTH);

        // Pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(0xE0B65E));
        tabbedPane.setForeground(new Color(0x704509));
        tabbedPane.setUI(new CustomTabbedUI(new Color(0xE0B65E)));

        // Paneles
        PanelDeCreacion panelDeCreacion = new PanelDeCreacion(this);
        panelDeEnfrentamiento = new PanelDeEnfrentamiento(panelDeCreacion);
        PanelDeLogs panelDeLogs = new PanelDeLogs();

        tabbedPane.addTab("Inicio", panelDeCreacion);
        tabbedPane.addTab("Batalla", panelDeEnfrentamiento);
        tabbedPane.addTab("Logs", panelDeLogs);


        // Agregar panel de pestañas al frame
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        // Set the custom content pane to the JFrame
        setContentPane(contentPane);

        // Frame visible
        setVisible(true);
    }

    public void setJugadores(List<Jugador> jugadores) {
        panelDeEnfrentamiento.setJugadores(jugadores);
    }

    public void irEnfrentamiento() {
        tabbedPane.setSelectedIndex(1);
        System.out.println("\n ¡Oh, nobles aventureros! La paz que una vez reinó en estas tierras ha sido rota. \n");

        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░░░▒░░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░░░▓▒░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░░░▓▒░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░░░▓▒░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░▒▒▒▒░░▒▒░▒▓▒░░▒░░▒▒▒▒░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░▒░░░░░░▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░▒▒▒▒▒▓▒▒▓▒▓▒▓▒░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░▒░▒▒▓▓▒█▒▒▒▓▒░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░▒░▒▒▒▒▒▓▓▓▓▓▓▒░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░▒▓▓▓▓▓▓▓▒▒▒▒░▒░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░▒▓▓▒▒▒▓▒▓▓▓▓░▒░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░▒░▓▓▓▓▓░▒░▒▒░▒▒░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░▒▒▓▒░▒▒▓▒░░░░▒▓▒░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░▒▒▓▒▓░░░▒░░▒▒▓▓▓░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░▒▒▒▓▒▒░░░▒▒▒▓▓▒░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░▒▓▓▓▒▒▒▒▒▓▒░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░▒▓▒▒▒▓▓▒▒░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░▒▒▓▓▒░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░░░▓▒░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░░░▓▒░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░░░▒░░░░░░░░░░░░░░░░░░░░░░\n");

        System.out.println("¡Que el destino les sea favorable! \n");
        // Index of the "Batalla" tab
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicSwingApp());
    }
}