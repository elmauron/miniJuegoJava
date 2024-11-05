package com.example.minijuegojava.model;

import java.util.*;

    public class Juego {

        public List<Jugador> creacionDeJugadores() {
            List<Jugador> jugadores = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Ingrese su nombre de invocador:");
                if (scanner.hasNextLine()) {
                    String nombreJugador1 = scanner.nextLine();
                    Jugador jugador1 = new Jugador(nombreJugador1, 0, 0, new Carta[3]);

                    System.out.println("¿Desea crear personajes aleatorios o manualmente? (aleatorio/manual): ");
                    String eleccion = scanner.nextLine().toLowerCase();

                    for (int i = 0; i < jugador1.getCartas().length; i++) {
                        if (eleccion.equals("aleatorio")) {
                            jugador1.getCartas()[i] = new Carta(Personaje.crearPersonajeAleatorio());
                        } else if (eleccion.equals("manual")) {
                            jugador1.getCartas()[i] = new Carta(Personaje.crearPersonajeDesdeConsola());
                        } else {
                            System.out.println("Opción no válida. Creando personaje aleatorio por defecto.");
                            jugador1.getCartas()[i] = new Carta(Personaje.crearPersonajeAleatorio());
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
                } else {
                    System.err.println("No se ha ingresado un nombre de invocador.");
                }
            } catch (NoSuchElementException e) {
                System.err.println("No se ha ingresado un nombre de invocador.");
            }

            return jugadores;
        }

    public double enfrentamiento (Carta carta1, Carta carta2) {
        double daño;

        if (carta1.getPersonaje().getRaza() == Raza.HUMANO) {
           daño = ((carta1.getVA() * carta1.getED()) - carta2.getPDEF() / 500)* 100;
        } else if (carta1.getPersonaje().getRaza() == Raza.ELFO) {
            daño = (((carta1.getVA() * carta1.getED()) - carta2.getPDEF() / 500)* 100)* 1.05;
        } else {
            daño = (((carta1.getVA() * carta1.getED()) - carta2.getPDEF() / 500)* 100)* 1.1;
        }

        carta2.getPersonaje().restarVida(daño);

        //q:will this method return the sout?
        //a: no, it will return the damage dealt
        //q: how do I make it return both?
        //a: you can't return both, but you can return the damage dealt and then print the sout in the method that calls this one


        return(daño);
    }

    public Carta seleccionDeCartas(Jugador jugador) {
        Random random = new Random();
        return jugador.getCartas()[random.nextInt(jugador.getCartas().length)];
    }

    //Implementacion logica de juego
    public void jugar(List<Jugador> jugadores) {

        Jugador jugador1 = jugadores.get(0);
        Jugador jugador2 = jugadores.get(1);

        System.out.println("Comienza la batalla!");
        System.out.println(jugador1.getNombre() + " vs " + jugador2.getNombre());

        while (jugador1.tieneCartas() && jugador2.tieneCartas()) {
            Carta carta1 = seleccionDeCartas(jugador1);
            Carta carta2 = seleccionDeCartas(jugador2);

            //debe elegir que jugador empieza al azar
            Jugador primerJugador = Math.random() < 0.5 ? jugador1 : jugador2;
            Jugador segundoJugador = primerJugador == jugador1 ? jugador2 : jugador1;

            Carta cartaPrimerJugador = primerJugador == jugador1 ? carta1 : carta2;
            Carta cartaSegundoJugador = segundoJugador == jugador1 ? carta1 : carta2;

            while (carta1.getPersonaje().getVida() > 0 && carta2.getPersonaje().getVida() > 0) {
                int ronda = 1;
                System.out.println("Ronda: " + ronda);

                for (int i = 0; i < 14; i++) {
                    if (cartaPrimerJugador.getPersonaje().getVida() <= 0 || cartaSegundoJugador.getPersonaje().getVida() <= 0) {

                        if (cartaPrimerJugador.getPersonaje().getVida() <= 0) {
                            System.out.println(cartaPrimerJugador.getPersonaje().getNombre() + " ha sido derrotado.");
                            primerJugador.setPartidasPerdidas(primerJugador.getPartidasPerdidas() + 1);
                            segundoJugador.setPartidasGanadas(segundoJugador.getPartidasGanadas() + 1);

                            primerJugador = segundoJugador;
                        } else {
                            System.out.println(cartaSegundoJugador.getPersonaje().getNombre() + " ha sido derrotado.");
                            segundoJugador.setPartidasPerdidas(segundoJugador.getPartidasPerdidas() + 1);
                            primerJugador.setPartidasGanadas(primerJugador.getPartidasGanadas() + 1);

                            segundoJugador = primerJugador;
                        }
                        break;
                    }

                    if (i % 2 == 0) {
                        System.out.println(cartaPrimerJugador.getPersonaje().getNombre() + " ataca a "
                                + cartaSegundoJugador.getPersonaje().getNombre() + " por "
                                + enfrentamiento(cartaPrimerJugador, cartaSegundoJugador) + " de daño.");

                        System.out.println("Vida restante de " + cartaSegundoJugador.getPersonaje().getNombre() + ": "
                                + cartaSegundoJugador.getPersonaje().getVida());
                    } else {
                        System.out.println(cartaSegundoJugador.getPersonaje().getNombre() + " ataca a "
                                + cartaPrimerJugador.getPersonaje().getNombre() + " por "
                                + enfrentamiento(cartaSegundoJugador, cartaPrimerJugador) + " de daño.");

                        System.out.println("Vida restante de " + cartaPrimerJugador.getPersonaje().getNombre() + ": "
                                + cartaPrimerJugador.getPersonaje().getVida());
                    }
                }

                System.out.println("Fin de la ronda.");
                System.out.println("A " + primerJugador.getNombre() + " le queda: " + primerJugador.mostrarCartas());


                ronda++;



            }

        }

    }

    public void mostrarResultados() {
        // Implement results display logic
    }
}

