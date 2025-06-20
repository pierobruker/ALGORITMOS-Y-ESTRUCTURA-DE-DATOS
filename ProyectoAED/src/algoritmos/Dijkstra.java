package algoritmos;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import modelos.Arista;
import modelos.Grafo;
import modelos.Nodo;

public class Dijkstra {
    public static void calcularRuta(Grafo grafo, Nodo inicio, Nodo destino) {
        Map<Nodo, Double> distancias = new HashMap<>();
        Map<Nodo, Nodo> anteriores = new HashMap<>();
        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparing(distancias::get));

        for (Nodo nodo : grafo.getNodos()) {
            distancias.put(nodo, Double.MAX_VALUE);
        }
        distancias.put(inicio, 0.0);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            for (Arista arista : grafo.getAristasDesde(actual)) {
                Nodo vecino = arista.getDestino();
                double nuevaDistancia = distancias.get(actual) + arista.getPeso();
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    anteriores.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        LinkedList<Nodo> camino = new LinkedList<>();
        Nodo actual = destino;
        while (actual != null && anteriores.containsKey(actual)) {
            camino.addFirst(actual);
            actual = anteriores.get(actual);
        }
        if (actual == inicio) camino.addFirst(inicio);

        System.out.println("\nRuta óptima:");
        for (Nodo n : camino) {
            System.out.print(n.getNombre() + " -> ");
        }
        System.out.println("FIN");
        System.out.println("Distancia total: " + distancias.get(destino));
    }
}