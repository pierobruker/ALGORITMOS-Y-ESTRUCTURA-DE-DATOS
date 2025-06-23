package algoritmos;

import modelo.Ubicacion;
import modelo.Ruta;
import java.util.*;

public class DetectorCiclos {

    // Método público principal para verificar si el grafo contiene algún ciclo
    public static boolean tieneciclos(Map<Ubicacion, List<Ruta>> grafo) {
        // Conjunto de nodos ya completamente visitados
        Set<Ubicacion> visitados = new HashSet<>();
        // Conjunto de nodos que están actualmente en el recorrido (pila de llamadas activa)
        Set<Ubicacion> enRecorrido = new HashSet<>();
        
        // Se recorre cada nodo del grafo
        for (Ubicacion nodo : grafo.keySet()) {
            // Si el nodo aún no ha sido visitado, se inicia la búsqueda DFS desde él
            if (!visitados.contains(nodo)) {
                if (detectarCicloDFS(grafo, nodo, visitados, enRecorrido)) {
                    // Si se detecta un ciclo, retorna true inmediatamente
                    return true;
                }
            }
        }
        // Si al terminar no se detectaron ciclos, retorna false
        return false;
    }

    // Método privado recursivo para realizar la detección de ciclo usando DFS
    private static boolean detectarCicloDFS(Map<Ubicacion, List<Ruta>> grafo, Ubicacion actual, Set<Ubicacion> visitados, Set<Ubicacion> enRecorrido) {
        // Si el nodo actual ya está en el recorrido, hay un ciclo (se vuelve a un nodo activo)
        if (enRecorrido.contains(actual)) {
            return true;
        }
        
        // Si ya fue completamente visitado antes, no hay necesidad de volver a procesarlo
        if (visitados.contains(actual)) {
            return false;
        }
        
        // Marca el nodo actual como visitado y lo agrega al recorrido actual
        visitados.add(actual);
        enRecorrido.add(actual);
        
        // Se recorren todos los vecinos del nodo actual
        for (Ruta ruta : grafo.getOrDefault(actual, Collections.emptyList())) {
            Ubicacion vecino = ruta.getDestino();
            // Se aplica recursivamente el DFS para cada vecino
            if (detectarCicloDFS(grafo, vecino, visitados, enRecorrido)) {
                return true;  // Si se detecta ciclo en alguna rama, retorna true
            }
        }
        
        // Al finalizar el recorrido de este nodo, se remueve del conjunto de nodos en recorrido
        enRecorrido.remove(actual);
        return false;  // No se detectó ciclo desde este nodo
    }
}