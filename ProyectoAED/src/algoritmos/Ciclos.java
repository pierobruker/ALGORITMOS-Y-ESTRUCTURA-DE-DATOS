package algoritmos;

import java.util.HashSet;
import java.util.Set;
import modelos.Arista;
import modelos.Grafo;
import modelos.Nodo;

public class Ciclos {
    public static boolean tieneCiclo(Grafo grafo) {
        Set<Nodo> visitados = new HashSet<>();
        Set<Nodo> enPila = new HashSet<>();

        for (Nodo nodo : grafo.getNodos()) {
            if (dfsCiclo(grafo, nodo, visitados, enPila)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfsCiclo(Grafo grafo, Nodo actual, Set<Nodo> visitados, Set<Nodo> enPila) {
        if (enPila.contains(actual)) return true;
        if (visitados.contains(actual)) return false;

        visitados.add(actual);
        enPila.add(actual);

        for (Arista arista : grafo.getAristasDesde(actual)) {
            Nodo vecino = arista.getDestino();
            if (dfsCiclo(grafo, vecino, visitados, enPila)) {
                return true;
            }
        }
        enPila.remove(actual);
        return false;
    }
}