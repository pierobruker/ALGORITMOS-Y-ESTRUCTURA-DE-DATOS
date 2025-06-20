package algoritmos;

import java.util.HashSet;
import java.util.Set;
import modelos.Arista;
import modelos.Grafo;
import modelos.Nodo;

public class DFS {
    public static void ejecutar(Grafo grafo, Nodo inicio) {
        Set<Nodo> visitados = new HashSet<>();
        System.out.println("Recorrido DFS:");
        dfsRecursivo(grafo, inicio, visitados);
    }

    private static void dfsRecursivo(Grafo grafo, Nodo actual, Set<Nodo> visitados) {
        System.out.println("- " + actual.getNombre());
        visitados.add(actual);
        for (Arista arista : grafo.getAristasDesde(actual)) {
            Nodo vecino = arista.getDestino();
            if (!visitados.contains(vecino)) {
                dfsRecursivo(grafo, vecino, visitados);
            }
        }
    }
}