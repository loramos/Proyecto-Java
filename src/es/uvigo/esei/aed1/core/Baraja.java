/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */

package es.uvigo.esei.aed1.core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        llenarBaraja();
        barajar();
    }

    private void llenarBaraja() {
        String[] palos = {"Oros", "Copas", "Espadas", "Bastos"};
        for (String palo : palos) {
            for (int i = 1; i <= 12; i++) {
                cartas.add(new Carta(i, palo));    
            }
        }
    }

    private void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta repartirCarta() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.remove(0);
    }
}


