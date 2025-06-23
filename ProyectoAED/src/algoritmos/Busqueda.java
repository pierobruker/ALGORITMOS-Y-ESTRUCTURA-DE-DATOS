package algoritmos;

import modelo.Ubicacion;
import modelo.Ruta;
import java.util.*;

public class Busqueda {

    // Algoritmo de Búsqueda en Anchura (Breadth-First Search - BFS)
    public static void BFS(Map<Ubicacion, List<Ruta>> grafo, Ubicacion inicio) {
        // Primero se valida que el nodo de inicio exista dentro del grafo
        if (!grafo.containsKey(inicio)) return;
        
        // Se utiliza una cola (FIFO) para manejar los nodos pendientes de visitar
        Queue<Ubicacion> cola = new LinkedList<>();
        // Se mantiene un conjunto de nodos visitados para evitar ciclos
        Set<Ubicacion> visitados = new HashSet<>();
        
        // Se inicia el recorrido agregando el nodo inicial
        cola.add(inicio);
        visitados.add(inicio);
        
        // Mientras haya nodos por visitar
        while (!cola.isEmpty()) {
            // Se extrae el primer nodo de la cola (nodo actual)
            Ubicacion actual = cola.poll();
            System.out.println("Visitando: " + actual.getId());
            
            // Se recorren los vecinos (destinos) conectados al nodo actual
            for (Ruta ruta : grafo.get(actual)) {
                Ubicacion vecino = ruta.getDestino();
                // Solo se agrega a la cola si aún no ha sido visitado
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
    }

    // Algoritmo de Búsqueda en Profundidad (Depth-First Search - DFS)
    public static void DFS(Map<Ubicacion, List<Ruta>> grafo, Ubicacion inicio) {
        // Se valida que el nodo inicial exista
        if (!grafo.containsKey(inicio)) return;
        
        // Se utiliza una pila (LIFO) para simular la profundidad
        Stack<Ubicacion> pila = new Stack<>();
        // Se guarda el registro de los nodos visitados
        Set<Ubicacion> visitados = new HashSet<>();
        
        // Se empieza empujando el nodo inicial a la pila
        pila.push(inicio);
        
        // Mientras haya nodos en la pila
        while (!pila.isEmpty()) {
            // Se saca el último nodo agregado (último en entrar, primero en salir)
            Ubicacion actual = pila.pop();
            
            // Si el nodo aún no fue visitado, se procesa
            if (!visitados.contains(actual)) {
                System.out.println("Visitando: " + actual.getId());
                visitados.add(actual);
                
                // Se recorren los vecinos para agregarlos a la pila
                for (Ruta ruta : grafo.get(actual)) {
                    Ubicacion vecino = ruta.getDestino();
                    if (!visitados.contains(vecino)) {
                        pila.push(vecino);
                    }
                }
            }
        }
    }
}