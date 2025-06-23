package algoritmos;

import modelo.Ubicacion;
import modelo.Ruta;
import java.util.*;

public class ComponentesConexas {

    // Método privado para convertir un grafo dirigido a un grafo no dirigido (para análisis de conectividad débil)
    private static Map<Ubicacion, List<Ruta>> convertirANoDirigido(Map<Ubicacion, List<Ruta>> grafo) {
        Map<Ubicacion, List<Ruta>> noDirigido = new HashMap<>();
        
        // Primero se copian todas las ubicaciones al nuevo grafo no dirigido
        for (Ubicacion nodo : grafo.keySet()) {
            noDirigido.putIfAbsent(nodo, new ArrayList<>());
        }
        
        // Luego se agregan las aristas en ambas direcciones (original e inversa)
        for (Map.Entry<Ubicacion, List<Ruta>> entry : grafo.entrySet()) {
            Ubicacion origen = entry.getKey();
            
            for (Ruta ruta : entry.getValue()) {
                Ubicacion destino = ruta.getDestino();
                
                // Se agrega la arista original (origen → destino)
                noDirigido.get(origen).add(ruta);
                
                // Se crea y agrega la arista inversa (destino → origen) para simular no dirigido
                Ruta rutaInversa = new Ruta(destino, origen, ruta.getPeso());
                noDirigido.putIfAbsent(destino, new ArrayList<>());
                noDirigido.get(destino).add(rutaInversa);
            }
        }
        
        return noDirigido;
    }

    // Método público para contar el número de componentes conexas (conectividad débil)
    public static int contarComponentes(Map<Ubicacion, List<Ruta>> grafo) {
        // Primero se convierte el grafo dirigido en no dirigido
        Map<Ubicacion, List<Ruta>> noDirigido = convertirANoDirigido(grafo);

        // Conjunto de nodos visitados durante la búsqueda
        Set<Ubicacion> visitados = new HashSet<>();
        int componentes = 0;  // Contador de componentes conexas
        
        // Se recorre cada nodo del grafo
        for (Ubicacion nodo : noDirigido.keySet()) {
            if (!visitados.contains(nodo)) {
                // Si el nodo no ha sido visitado, se recorre toda su componente conexa
                DFSComponente(noDirigido, nodo, visitados);
                componentes++;  // Se ha encontrado una nueva componente
            }
        }
        return componentes;
    }

    // Método auxiliar de búsqueda en profundidad para recorrer toda una componente
    private static void DFSComponente(Map<Ubicacion, List<Ruta>> grafo, Ubicacion inicio, Set<Ubicacion> visitados) {
        Stack<Ubicacion> pila = new Stack<>();
        pila.push(inicio);
        
        while (!pila.isEmpty()) {
            Ubicacion actual = pila.pop();
            
            if (!visitados.contains(actual)) {
                visitados.add(actual);
                
                // Se recorren todos los vecinos del nodo actual
                for (Ruta ruta : grafo.getOrDefault(actual, Collections.emptyList())) {
                    pila.push(ruta.getDestino());
                }
            }
        }
    }

    // Método público para identificar zonas completamente aisladas del grafo
    public static List<Ubicacion> identificarZonasAisladas(Map<Ubicacion, List<Ruta>> grafo) {
        List<Ubicacion> aisladas = new ArrayList<>();
        
        // Se analiza cada nodo del grafo
        for (Ubicacion nodo : grafo.keySet()) {
            // Se comprueba si tiene aristas salientes (conexiones hacia otros nodos)
            boolean tieneSalientes = !grafo.get(nodo).isEmpty();
            boolean tieneEntrantes = false;
            
            // Se comprueba si tiene aristas entrantes (conexiones desde otros nodos)
            for (List<Ruta> rutas : grafo.values()) {
                for (Ruta ruta : rutas) {
                    if (ruta.getDestino().equals(nodo)) {
                        tieneEntrantes = true;
                        break;
                    }
                }
                if (tieneEntrantes) break;
            }
            
            // Si no tiene ni salientes ni entrantes, está completamente aislado
            if (!tieneSalientes && !tieneEntrantes) {
                aisladas.add(nodo);
            }
        }
        
        return aisladas;
    }
}