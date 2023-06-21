package es.uvigo.esei.aed1.core;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Mesa {
    private Map<String, Deque<Carta>> mesa;

    public Mesa() {
        mesa = new HashMap<>();
        mesa.put("Oros", new ArrayDeque<>());
        mesa.put("Copas", new ArrayDeque<>());
        mesa.put("Espadas", new ArrayDeque<>());
        mesa.put("Bastos", new ArrayDeque<>());
    }

public boolean puedeColocarCarta(Carta carta) {
    Deque<Carta> cartasPalo = mesa.get(carta.getPalo());
    int numero = carta.getNumero();

    if (numero == 5) {
        return cartasPalo.isEmpty() || cartasPalo.contains(new Carta(4, carta.getPalo()));
    } else {
        return cartasPalo.stream().anyMatch(c -> c.getNumero() == numero - 1) || cartasPalo.stream().anyMatch(c -> c.getNumero() == numero + 1);
    }
}

    public void colocarCarta(Carta carta) {
        Deque<Carta> cartasPalo = mesa.get(carta.getPalo());
        if (carta.getNumero() == 1) {
            cartasPalo.addFirst(carta);
        } else {
            cartasPalo.addLast(carta);
        }
    }
    public boolean getAsDeOrosEnMesa() {
        Deque<Carta> cartasOros = mesa.get("Oros");
        for (Carta carta : cartasOros) {
            if (carta.getNumero() == 1) {
                return true;
            }
        }
        return false;
    }
   
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
      for (Map.Entry<String, Deque<Carta>> entry : mesa.entrySet()) {
        sb.append(entry.getKey()).append(": ");
        for (Carta carta : entry.getValue()) {
            sb.append(carta).append(", ");
        }
        if (!entry.getValue().isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(System.lineSeparator());
    }
    return sb.toString();
}
}


















