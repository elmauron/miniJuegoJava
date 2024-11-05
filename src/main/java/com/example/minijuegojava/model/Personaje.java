package com.example.minijuegojava.model;
import java.util.Scanner;
import java.util.Random;

public class Personaje {
    //atributos MISC
    private String nombre;
    private String Apodo;
    private String nacimiento;
    private Raza raza;
    private int edad;
    private boolean vivo = true;

    //atributos estadísticos
    private double vida;
    private int velocidad;
    private int fuerza;
    private int destreza;
    private int armadura;
    private int nivel;

    private static final String[] NOMBRES = {"Aragorn", "Legolas", "Gimli", "Frodo", "Gandalf"};
    private static final String[] APODOS = {"El Valiente", "El Sabio", "El Fuerte", "El Rápido", "El Justo"};

    public Personaje(String nombre, String apodo, String nacimiento, Raza raza, int edad, double vida, int velocidad, int fuerza, int destreza, int armadura, int nivel) {
        //atributos MISC
        this.nombre = nombre;
        Apodo = apodo;
        this.nacimiento = nacimiento;
        this.raza = raza;
        this.edad = edad;
        //atributos estadísticos
        this.vida = vida;
        this.velocidad = velocidad;
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.armadura = armadura;
        this.nivel = nivel;
    }

    //metodo para creación de personaje aleatorio
    public static Personaje crearPersonajeAleatorio() {
        Random random = new Random();
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
        String apodo = APODOS[random.nextInt(APODOS.length)];
        String nacimiento = "01/01/2000";
        Raza raza = Raza.getRandomRaza();
        int edad = random.nextInt(300) + 1; // de 1 a 300 años
        int vida = 100;
        int velocidad = random.nextInt(11); // de 0 a 10
        int fuerza = random.nextInt(11); // de 0 a 10
        int destreza = random.nextInt(6); // de 0 a 5
        int armadura = random.nextInt(11); // de 0 a 10
        int nivel = random.nextInt(11); // de 0 a 10

        return new Personaje(nombre, apodo, nacimiento, raza, edad, vida, velocidad, fuerza, destreza, armadura, nivel);
    }

    //metodo para creación de personaje desde consola
    public static Personaje crearPersonajeDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Ingrese el nombre del personaje: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apodo del personaje: ");
        String apodo = scanner.nextLine();

        System.out.println("Ingrese la fecha de nacimiento del personaje (dd/mm/yyyy): ");
        String nacimiento = scanner.nextLine();

        System.out.println("Ingrese la raza del personaje (ORCO, HUMANO, ELFO, ENANO, MAGO): ");
        Raza raza = Raza.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Ingrese la edad del personaje: ");
        int edad = scanner.nextInt();

        int vida = 100;
        int velocidad = random.nextInt(11); // de 0 a 10
        int fuerza = random.nextInt(11); // de 0 a 10
        int destreza = random.nextInt(6); // de 0 a 5
        int armadura = random.nextInt(11); // de 0 a 10
        int nivel = random.nextInt(11); // de 0 a 10

        return new Personaje(nombre, apodo, nacimiento, raza, edad, vida, velocidad, fuerza, destreza, armadura, nivel);
    }

    //getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return Apodo;
    }

    public void setApodo(String apodo) {
        Apodo = apodo;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void restarVida(double daño) {
        this.vida = this.vida - daño;
        System.out.println("daño recibido: " + daño);
        System.out.println("Vida restante: " + this.vida);
    }

    public void matarPersonaje() {
        this.vida = 0;
        this.vivo = false;
    }

}
