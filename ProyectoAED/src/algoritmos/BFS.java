package algoritmos;

import java.util.*;
import modelos.Arista;
import modelos.Grafo;
import modelos.Nodo;

public class BFS {
    public static void ejecutar(Grafo grafo, Nodo inicio) {
        Set<Nodo> visitados = new HashSet<>();
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(inicio);
        visitados.add(inicio);

        System.out.println("Recorrido BFS:");
        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            System.out.println("- " + actual.getNombre());
            for (Arista arista : grafo.getAristasDesde(actual)) {
                Nodo vecino = arista.getDestino();
                if (!visitados.contains(vecino)) {
                    cola.add(vecino);
                    visitados.add(vecino);
                }
            }
        }
    }
}
