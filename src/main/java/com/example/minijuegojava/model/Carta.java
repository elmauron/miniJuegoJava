package com.example.minijuegojava.model;

public class Carta {
    private Personaje personaje;
    public double PD;
    public double ED;
    public double VA;
    public double PDEF;


    public Carta(Personaje personaje) {
        this.personaje = personaje;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public double getPD() {
        return PD;
    }

    public void setPD(int PD) {
        this.PD = PD;
    }

    public double getED() {
        return ED;
    }

    public void setED(double ED) {
        this.ED = ED;
    }

    public double getVA() {
        return VA;
    }

    public void setVA(double VA) {
        this.VA = VA;
    }

    public double getPDEF() {
        return PDEF;
    }

    public void setPDEF(int PDEF) {
        this.PDEF = PDEF;
    }

    public void eliminarCarta() {
        this.personaje.matarPersonaje();
    }

}
