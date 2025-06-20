package algoritmos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import modelos.Arista;
import modelos.Grafo;
import modelos.Nodo;



public class ComponentesConexas {
    public static List<Set<Nodo>> encontrarComponentes(Grafo grafo) {
        Set<Nodo> visitados = new HashSet<>();
        List<Set<Nodo>> componentes = new ArrayList<>();

        for (Nodo nodo : grafo.getNodos()) {
            if (!visitados.contains(nodo)) {
                Set<Nodo> componente = new HashSet<>();
                Queue<Nodo> cola = new LinkedList<>();
                cola.add(nodo);
                visitados.add(nodo);

                while (!cola.isEmpty()) {
                    Nodo actual = cola.poll();
                    componente.add(actual);

                    for (Arista arista : grafo.getAristasDesde(actual)) {
                        Nodo vecino = arista.getDestino();
                        if (!visitados.contains(vecino)) {
                            cola.add(vecino);
                            visitados.add(vecino);
                        }
                    }

                    for (Nodo posible : grafo.getNodos()) {
                        for (Arista arista : grafo.getAristasDesde(posible)) {
                            if (arista.getDestino().equals(actual) && !visitados.contains(posible)) {
                                cola.add(posible);
                                visitados.add(posible);
                            }
                        }
                    }
                }

                componentes.add(componente);
            }
        }
        return componentes;
    }
}