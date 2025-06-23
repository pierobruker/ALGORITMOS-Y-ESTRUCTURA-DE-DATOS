package estructuras;

import modelo.Ubicacion;
import modelo.Ruta;
import java.util.*;

public class Grafo {

    // Lista de adyacencia: cada nodo tiene asociada su lista de rutas salientes
    private final Map<Ubicacion, List<Ruta>> listaAdyacencia;

    // Lista general de todas las aristas del grafo (para facilitar algunos algoritmos)
    private final List<Ruta> aristas;

    // Permite obtener la lista de adyacencia completa
    public Map<Ubicacion, List<Ruta>> getListaAdyacencia() {
        return this.listaAdyacencia;
    }

    // Constructor principal: inicializa el grafo vacío
    public Grafo() {
        this.listaAdyacencia = new HashMap<>();
        this.aristas = new ArrayList<>();
    }

    // Métodos para gestionar nodos

    // Agregar un nuevo nodo al grafo
    public void agregarNodo(Ubicacion nodo) {
        if (!listaAdyacencia.containsKey(nodo)) {
            listaAdyacencia.put(nodo, new ArrayList<>());
        }
    }

    // Eliminar un nodo y todas sus aristas asociadas
    public void eliminarNodo(Ubicacion nodo) {
        // Primero se elimina el nodo de la lista de adyacencia
        listaAdyacencia.remove(nodo);

        // Luego se eliminan todas las aristas entrantes hacia ese nodo
        for (List<Ruta> rutas : listaAdyacencia.values()) {
            rutas.removeIf(ruta -> ruta.getDestino().equals(nodo));
        }

        // También se elimina de la lista global de aristas
        aristas.removeIf(ruta -> ruta.getOrigen().equals(nodo) || ruta.getDestino().equals(nodo));
    }

    // Métodos para gestionar aristas

    // Agregar una nueva arista (ruta) entre dos nodos
    public void agregarArista(Ubicacion origen, Ubicacion destino, double peso) {
        if (!listaAdyacencia.containsKey(origen) || !listaAdyacencia.containsKey(destino)) {
            return;
        }

        Ruta nuevaRuta = new Ruta(origen, destino, peso);
        listaAdyacencia.get(origen).add(nuevaRuta);
        aristas.add(nuevaRuta);
    }

    // Eliminar una arista específica entre dos nodos
    public boolean eliminarArista(Ubicacion origen, Ubicacion destino) {
        if (!listaAdyacencia.containsKey(origen)) {
            return false;
        }

        boolean eliminada = listaAdyacencia.get(origen).removeIf(
            ruta -> ruta.getDestino().equals(destino)
        );

        if (eliminada) {
            aristas.removeIf(ruta ->
                ruta.getOrigen().equals(origen) && ruta.getDestino().equals(destino)
            );
        }

        return eliminada;
    }

    // Modificar el peso de una arista existente
    public boolean modificarPesoArista(Ubicacion origen, Ubicacion destino, double nuevoPeso) {
        if (!listaAdyacencia.containsKey(origen)) {
            return false;
        }

        for (Ruta ruta : listaAdyacencia.get(origen)) {
            if (ruta.getDestino().equals(destino)) {
                ruta.setPeso(nuevoPeso);
                return true;
            }
        }
        return false;
    }

    // Obtener todas las aristas del grafo
    public List<Ruta> getAristas() {
        return new ArrayList<>(aristas);
    }

    // Obtener una arista específica entre dos nodos
    public Ruta getArista(Ubicacion origen, Ubicacion destino) {
        if (!listaAdyacencia.containsKey(origen)) {
            return null;
        }

        for (Ruta ruta : listaAdyacencia.get(origen)) {
            if (ruta.getDestino().equals(destino)) {
                return ruta;
            }
        }
        return null;
    }

    // Algoritmos de grafos

    // Implementación de Dijkstra integrada en la clase Grafo
    public List<Ubicacion> calcularRutaOptima(Ubicacion origen, Ubicacion destino) {
        Map<Ubicacion, Double> distancias = new HashMap<>();
        Map<Ubicacion, Ubicacion> predecesores = new HashMap<>();
        PriorityQueue<Ubicacion> cola = new PriorityQueue<>(
            Comparator.comparingDouble(u -> distancias.getOrDefault(u, Double.MAX_VALUE))
        );

        // Inicialización de distancias
        for (Ubicacion nodo : listaAdyacencia.keySet()) {
            distancias.put(nodo, Double.MAX_VALUE);
        }
        distancias.put(origen, 0.0);
        cola.add(origen);

        // Bucle principal del algoritmo
        while (!cola.isEmpty()) {
            Ubicacion actual = cola.poll();

            if (actual.equals(destino)) break;

            for (Ruta ruta : listaAdyacencia.get(actual)) {
                Ubicacion vecino = ruta.getDestino();
                double nuevaDist = distancias.get(actual) + ruta.getPeso();

                if (nuevaDist < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDist);
                    predecesores.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        // Reconstrucción de la ruta óptima
        List<Ubicacion> rutaOptima = new LinkedList<>();
        Ubicacion actual = destino;
        while (actual != null) {
            rutaOptima.add(0, actual);
            actual = predecesores.get(actual);
        }

        return rutaOptima;
    }

    // Algoritmo de Búsqueda en Anchura (BFS)
    public void recorridoBFS(Ubicacion inicio) {
        if (!listaAdyacencia.containsKey(inicio)) return;

        Queue<Ubicacion> cola = new LinkedList<>();
        Set<Ubicacion> visitados = new HashSet<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Ubicacion actual = cola.poll();
            System.out.println("Visitando: " + actual.getId());

            for (Ruta ruta : listaAdyacencia.get(actual)) {
                Ubicacion vecino = ruta.getDestino();
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
    }

    // Algoritmo de Búsqueda en Profundidad (DFS)
    public void recorridoDFS(Ubicacion inicio) {
        if (!listaAdyacencia.containsKey(inicio)) return;

        Stack<Ubicacion> pila = new Stack<>();
        Set<Ubicacion> visitados = new HashSet<>();

        pila.push(inicio);

        while (!pila.isEmpty()) {
            Ubicacion actual = pila.pop();

            if (!visitados.contains(actual)) {
                System.out.println("Visitando: " + actual.getId());
                visitados.add(actual);

                for (Ruta ruta : listaAdyacencia.get(actual)) {
                    Ubicacion vecino = ruta.getDestino();
                    if (!visitados.contains(vecino)) {
                        pila.push(vecino);
                    }
                }
            }
        }
    }

    // Detección de ciclos en grafos dirigidos (DFS con conjunto de recorrido activo)
    public boolean tieneCiclos() {
        Set<Ubicacion> visitados = new HashSet<>();
        Set<Ubicacion> enRecorrido = new HashSet<>();

        for (Ubicacion nodo : listaAdyacencia.keySet()) {
            if (detectarCicloDFS(nodo, visitados, enRecorrido)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectarCicloDFS(Ubicacion actual, Set<Ubicacion> visitados, Set<Ubicacion> enRecorrido) {
        if (enRecorrido.contains(actual)) return true;
        if (visitados.contains(actual)) return false;

        visitados.add(actual);
        enRecorrido.add(actual);

        for (Ruta ruta : listaAdyacencia.get(actual)) {
            Ubicacion vecino = ruta.getDestino();
            if (detectarCicloDFS(vecino, visitados, enRecorrido)) {
                return true;
            }
        }

        enRecorrido.remove(actual);
        return false;
    }

    // Conteo de componentes conexas (considerando componentes débiles para grafos dirigidos)
    public int componentesConexas() {
        Grafo noDirigido = convertirANoDirigido();
        Set<Ubicacion> visitados = new HashSet<>();
        int componentes = 0;

        for (Ubicacion nodo : noDirigido.listaAdyacencia.keySet()) {
            if (!visitados.contains(nodo)) {
                recorridoDFSComponente(nodo, visitados, noDirigido);
                componentes++;
            }
        }
        return componentes;
    }

    // Conversión de grafo dirigido a no dirigido
    private Grafo convertirANoDirigido() {
        Grafo noDirigido = new Grafo();

        for (Ubicacion nodo : listaAdyacencia.keySet()) {
            noDirigido.agregarNodo(nodo);
        }

        for (Ruta ruta : aristas) {
            noDirigido.agregarArista(ruta.getOrigen(), ruta.getDestino(), ruta.getPeso());
            noDirigido.agregarArista(ruta.getDestino(), ruta.getOrigen(), ruta.getPeso());
        }

        return noDirigido;
    }

    private void recorridoDFSComponente(Ubicacion inicio, Set<Ubicacion> visitados, Grafo grafo) {
        Stack<Ubicacion> pila = new Stack<>();
        pila.push(inicio);

        while (!pila.isEmpty()) {
            Ubicacion actual = pila.pop();
            if (!visitados.contains(actual)) {
                visitados.add(actual);
                for (Ruta ruta : grafo.listaAdyacencia.get(actual)) {
                    pila.push(ruta.getDestino());
                }
            }
        }
    }

    // Detección de zonas aisladas (nodos sin aristas entrantes ni salientes)
    public List<Ubicacion> zonasAisladas() {
        List<Ubicacion> aisladas = new ArrayList<>();

        for (Ubicacion nodo : listaAdyacencia.keySet()) {
            boolean tieneSalientes = !listaAdyacencia.get(nodo).isEmpty();
            boolean tieneEntrantes = false;

            for (List<Ruta> rutas : listaAdyacencia.values()) {
                for (Ruta ruta : rutas) {
                    if (ruta.getDestino().equals(nodo)) {
                        tieneEntrantes = true;
                        break;
                    }
                }
                if (tieneEntrantes) break;
            }

            if (!tieneSalientes && !tieneEntrantes) {
                aisladas.add(nodo);
            }
        }

        return aisladas;
    }
}