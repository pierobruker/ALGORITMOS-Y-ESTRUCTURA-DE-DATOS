package modelos;

import java.util.*;

public class Grafo {
    private Map<Integer, Nodo> nodos;
    private Map<Nodo, List<Arista>> adyacencias;

    public Grafo() {
        nodos = new HashMap<>();
        adyacencias = new HashMap<>();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.put(nodo.getId(), nodo);
        adyacencias.putIfAbsent(nodo, new ArrayList<>());
    }

    public void agregarArista(Nodo origen, Nodo destino, double peso) {
        Arista arista = new Arista(origen, destino, peso);
        adyacencias.get(origen).add(arista);
    }

    public Collection<Nodo> getNodos() {
        return nodos.values();
    }

    public List<Arista> getAristasDesde(Nodo nodo) {
        return adyacencias.getOrDefault(nodo, new ArrayList<>());
    }

    public Nodo getNodo(int id) {
        return nodos.get(id);
    }
}