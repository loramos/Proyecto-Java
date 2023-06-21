/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

package es.uvigo.esei.aed1.core;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> mano;
    private int puntosPartida;
    private int puntosAsDeOro;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.puntosPartida = 0;
        this.puntosAsDeOro = 0;
    }
    public void asignarPuntosPartida() {
        this.puntosPartida += 4;
    }

    public void asignarPuntosAsDeOro() {
        this.puntosAsDeOro += 2;
    }

    public void sumarPuntosPartida(int puntos) {
        this.puntosPartida += puntos;
    }
    public void sumarPuntosAsDeOro(int puntos) {
    this.puntosAsDeOro += puntos;
}


    public void anotarAsDeOro(int puntos) {
    this.puntosAsDeOro += puntos;
    }


    public int getPuntuacionTotal() {
        return puntosPartida + puntosAsDeOro;
    }

    public void agregarCarta(Carta carta) {
        mano.add(carta);
    }

    public String mostrarMano() {
        StringBuilder cartasEnMano = new StringBuilder();
        for (Carta carta : mano) {
            cartasEnMano.append(carta).append(", ");
        }
        if (cartasEnMano.length() > 0) {
            cartasEnMano.setLength(cartasEnMano.length() - 2);
        }
        return cartasEnMano.toString();
    }

    public void eliminarCarta(Carta carta) {
        mano.remove(carta);
    }

    public boolean manoVacia() {
        return mano.isEmpty();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carta> getMano() {
        return mano;
    }
    @Override
public String toString() {
    return "Jugador: " + this.nombre +
           "\nPuntos de Partida: " + this.puntosPartida +
           "\nPuntos As de Oro: " + this.puntosAsDeOro +
           "\nPuntuacion Total: " + this.getPuntuacionTotal();
}

}

