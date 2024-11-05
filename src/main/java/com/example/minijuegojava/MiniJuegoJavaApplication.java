package com.example.minijuegojava;

import com.example.minijuegojava.gui.BasicSwingApp;
import com.example.minijuegojava.model.Juego;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.WebApplicationType;

import javax.swing.*;

@SpringBootApplication
public class MiniJuegoJavaApplication {

	public static void main(String[] args) {

		System.setProperty("java.awt.headless", "false");

		new SpringApplicationBuilder(MiniJuegoJavaApplication.class)
				.web(WebApplicationType.NONE) // Disable web environment
				.run(args);

		// Launch the Swing application
		SwingUtilities.invokeLater(() -> new BasicSwingApp());

	}
}
