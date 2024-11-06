package com.example.minijuegojava.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PanelDeLogs extends JPanel {

    private JTextArea textArea;
    private JPanel buttonPanel;

    public PanelDeLogs() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        add(buttonPanel, BorderLayout.WEST);

        loadLogButtons();
    }

    private void loadLogButtons() {
        String projectDir = System.getProperty("user.dir");
        File logDir = new File(projectDir, "logs");
        if (logDir.exists() && logDir.isDirectory()) {
            File[] logFiles = logDir.listFiles((dir, name) -> name.startsWith("minijuegolog_") && name.endsWith(".txt"));
            if (logFiles != null && logFiles.length > 0) {
                for (File logFile : logFiles) {
                    JButton logButton = new JButton(logFile.getName());
                    logButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            loadLogContent(logFile);
                        }
                    });
                    buttonPanel.add(logButton);
                }
            } else {
                textArea.setText("No log files found.");
            }
        } else {
            textArea.setText("Log directory not found.");
        }
    }

    private void loadLogContent(File logFile) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(logFile.getAbsolutePath())));
            textArea.setText(content);
        } catch (IOException e) {
            textArea.setText("Error loading log file: " + e.getMessage());
        }
    }
}