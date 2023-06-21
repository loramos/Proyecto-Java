/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Juego {
    private final IU iu;
    private List<Jugador> jugadores;
    private Baraja baraja;
    private Mesa mesa;
    private int puntosAsDeOro;

    public Juego(IU iu) {
        this.iu = iu;
        this.jugadores = new LinkedList<>();
        this.puntosAsDeOro = 2;
    }

    private void iniciarNuevaPartida() {
        this.baraja = new Baraja();
        this.mesa = new Mesa();
        for (Jugador jugador : jugadores) {
            jugador.getMano().clear();
        }
        repartirCartas();
    }

    private void repartirCartas() {
        iu.mostrarMensaje("Repartiendo las cartas....");
        int numCartasPorJugador = 48 / jugadores.size();
        for (int i = 0; i < numCartasPorJugador; i++) {
            for (Jugador jugador : jugadores) {
                Carta carta = baraja.repartirCarta();
                if (carta != null) {
                    jugador.agregarCarta(carta);
                }
            }
        }
    }
    
    private Jugador seleccionarJugadorInicial() {
        iu.mostrarMensaje("Sorteando inicio de partida...");
        int indiceJugadorInicial = (int) (Math.random() * jugadores.size());
        return jugadores.get(indiceJugadorInicial);
    }
    
    private void jugarPartida() {
    iu.mostrarJugadores(jugadores);
    Jugador jugadorInicial = seleccionarJugadorInicial();
    iu.mostrarMensaje("El jugador que comienza la partida es: " + jugadorInicial.getNombre());
    boolean partidaTerminada = false;
    int indiceJugadorActual = jugadores.indexOf(jugadorInicial);

    do {
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        iu.mostrarEstadoMesa(mesa);
        iu.mostrarManoJugador(jugadorActual);
        if (jugadorActual.getMano().stream().noneMatch(mesa::puedeColocarCarta)) {
            iu.mostrarMensaje("No puedes colocar ninguna carta, paso turno automaticamente.");
            indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
            continue;
        }
        boolean jugadaValida;
        do {
            Carta cartaSeleccionada = iu.seleccionarCarta(jugadorActual);
            if (cartaSeleccionada != null && mesa.puedeColocarCarta(cartaSeleccionada)) {
                mesa.colocarCarta(cartaSeleccionada);
                jugadorActual.eliminarCarta(cartaSeleccionada);
                if (jugadorActual.manoVacia()) {
                    jugadorActual.asignarPuntosPartida();
                    partidaTerminada = true;
                }
                if (cartaSeleccionada.getNumero() == 1 && cartaSeleccionada.getPalo().equals("Oros")) {
                    jugadorActual.sumarPuntosAsDeOro(puntosAsDeOro);
                }
                indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
                jugadaValida = true;
            } else {
                iu.mostrarMensaje("No puedes colocar esa carta en la mesa. Por favor, selecciona otra carta.");
                jugadaValida = false;
            }
        } while (!jugadaValida);
    } while (!partidaTerminada);
    if (!mesa.getAsDeOrosEnMesa()) { // Si el as de oros no está en la mesa al final de la partida
        puntosAsDeOro += 2; // Se suman 2 puntos a los puntosAsDeOro
    }
}



    public void jugar() {
    iu.mostrarMensaje("Bienvenido al Juego Cinquillo-Oro!!!!!");
    int numJugadores = iu.pedirNumeroJugadores();
    List<String> nombresJugadores = iu.pedirDatosJugadores(numJugadores);
    for (String nombre : nombresJugadores) {
        jugadores.add(new Jugador(nombre));
    }
    boolean asDeOroColocado;
    do {
        iniciarNuevaPartida();
        jugarPartida();
        asDeOroColocado = mesa.getAsDeOrosEnMesa();
        puntosAsDeOro += 2;
    } while (!asDeOroColocado);
    int maxPuntuacion = jugadores.stream().mapToInt(Jugador::getPuntuacionTotal).max().orElse(-1);
    List<Jugador> ganadores = jugadores.stream()
            .filter(jugador -> jugador.getPuntuacionTotal() == maxPuntuacion)
            .collect(Collectors.toList());
    for (Jugador ganador : ganadores) {
        iu.mostrarMensaje("¡Felicidades! " + ganador.getNombre() + " ha ganado con " + ganador.getPuntuacionTotal() +" puntos.");
    }
    for (Jugador jugador : jugadores) {
        iu.mostrarMensaje(jugador.getNombre() + " obtuvo " + jugador.getPuntuacionTotal() + " puntos.");
    }
}

    
}


        
    




    






        
    

