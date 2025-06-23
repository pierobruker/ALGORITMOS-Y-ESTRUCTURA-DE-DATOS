package algoritmos;

import modelo.Ubicacion;
import modelo.Ruta;
import java.util.*;

public class Dijkstra {

    // Método principal que calcula la ruta óptima entre dos ubicaciones
    public static List<Ubicacion> calcularRutaOptima(Map<Ubicacion, List<Ruta>> grafo, Ubicacion origen, Ubicacion destino) {
        
        // Mapa para almacenar la distancia mínima calculada desde el origen a cada nodo
        Map<Ubicacion, Double> distancias = new HashMap<>();
        
        // Mapa para almacenar el predecesor de cada nodo (para poder reconstruir la ruta final)
        Map<Ubicacion, Ubicacion> predecesores = new HashMap<>();
        
        // Cola de prioridad ordenada según las distancias actuales
        PriorityQueue<Ubicacion> cola = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
        
        // Inicialización de distancias: se asigna infinito (MAX_VALUE) a todos los nodos
        for (Ubicacion nodo : grafo.keySet()) {
            distancias.put(nodo, Double.MAX_VALUE);
        }
        // La distancia desde el origen a sí mismo es 0
        distancias.put(origen, 0.0);
        // Se agrega el nodo de inicio a la cola de prioridad
        cola.add(origen);
        
        // Bucle principal del algoritmo de Dijkstra
        while (!cola.isEmpty()) {
            // Se toma el nodo con la menor distancia acumulada
            Ubicacion actual = cola.poll();
            
            // Si se llega al destino, se puede finalizar la búsqueda (optimización)
            if (actual.equals(destino)) break;
            
            // Se recorren los vecinos del nodo actual
            for (Ruta ruta : grafo.getOrDefault(actual, Collections.emptyList())) {
                Ubicacion vecino = ruta.getDestino();
                
                // Se calcula la distancia acumulada al vecino pasando por el nodo actual
                double nuevaDist = distancias.get(actual) + ruta.getPeso();
                
                // Si se encuentra una mejor distancia, se actualiza
                if (nuevaDist < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDist);
                    predecesores.put(vecino, actual);
                    
                    // Actualiza la cola de prioridad: remueve el nodo para actualizar su posición
                    cola.remove(vecino);
                    cola.add(vecino);
                }
            }
        }
        
        // Reconstrucción de la ruta óptima obtenida
        List<Ubicacion> rutaOptima = new LinkedList<>();
        Ubicacion actual = destino;
        
        // Se reconstruye el camino hacia atrás, desde el destino al origen
        while (actual != null) {
            rutaOptima.add(0, actual);  // Inserta al inicio para mantener el orden correcto
            actual = predecesores.get(actual);
        }
        
        // Verificación final: si el primer nodo de la ruta es el origen, la ruta es válida
        return rutaOptima.get(0).equals(origen) ? rutaOptima : Collections.emptyList();
    }
}