/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Carta;
import es.uvigo.esei.aed1.core.Jugador;
import es.uvigo.esei.aed1.core.Mesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class IU {
    private final Scanner teclado;

    public IU() {
        teclado = new Scanner(System.in).useDelimiter("\r?\n");
    }

    public int leeNum(String msg) {
        do {
            System.out.print(msg);

            try {
                return teclado.nextInt();
            } catch (InputMismatchException exc) {
                teclado.next();
                System.out.println("Entrada no válida. Debe ser un entero.");
            }
        } while (true);
    }

    public String leeString(String msg) {
        System.out.print(msg);
        return teclado.next();
    }

    public String leeString(String msg, Object... args) {
        System.out.printf(msg, args);
        return teclado.next();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarMensaje(String msg, Object... args) {
        System.out.printf(msg, args);
    }

    public int pedirNumeroJugadores() {
        int numJugadores;
        do {
            numJugadores = leeNum("Introduce el numero de jugadores (3 o 4): ");
            if (numJugadores != 3 && numJugadores != 4) {
                mostrarMensaje("Numero invalido. Por favor, introduce 3 o 4 jugadores.");
            }
        } while (numJugadores != 3 && numJugadores != 4);

        return numJugadores;
    }

    public List<String> pedirDatosJugadores(int numJugadores) {
    List<String> nombresJugadores = new ArrayList<>();
    for (int i = 1; i <= numJugadores; i++) {
        String nombre = leeString("Introduce el nombre del jugador " + i + ": ");
        nombresJugadores.add(nombre);
    }
    return nombresJugadores;
}


    public void mostrarJugador(Jugador jugador) {
        System.out.println(jugador.toString());
    }

    public void mostrarJugadores(Collection<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            mostrarJugador(jugador);
        }
    }

    public void mostrarEstadoMesa(Mesa mesa) {
        System.out.println("Estado de la mesa:");
        System.out.println(mesa.toString());
    }

   public void mostrarManoJugador(Jugador jugador) {
    System.out.println("Mano del jugador " + jugador.getNombre() + ":");
    System.out.println(jugador.mostrarMano());
}


     private Carta buscarCarta(Jugador jugador, String cartaStr) {
    for (Carta carta : jugador.getMano()) {
        if (carta.toString().equalsIgnoreCase(cartaStr)) {
            return carta;
        }
    }
    return null;
}
    public Carta seleccionarCarta(Jugador jugador) {
    Carta cartaSeleccionada = null;
    while (cartaSeleccionada == null) {
        String cartaStr = leeString("Selecciona una carta de tu mano ");
        if (cartaStr.equalsIgnoreCase("Pasar")) {
            return null;
        }
        cartaSeleccionada = buscarCarta(jugador, cartaStr);
        if (cartaSeleccionada == null) {
            mostrarMensaje("Carta no encontrada en tu mano. Por favor, selecciona otra carta.");
        }
    }
    return cartaSeleccionada;
}
    
}
