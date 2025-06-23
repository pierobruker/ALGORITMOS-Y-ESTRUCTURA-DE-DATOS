package controlador;

import modelo.Almacen;
import modelo.Ubicacion;
import modelo.Ruta;
import algoritmos.Dijkstra;
import algoritmos.Busqueda;
import algoritmos.DetectorCiclos;
import algoritmos.ComponentesConexas;
import util.EntradaUsuario;
import java.util.List;
import java.util.Map;

public class ControladorAlgoritmos {

    // Este controlador también tiene acceso directo al modelo de Almacen
    private final Almacen almacen;

    // Constructor que recibe la instancia de Almacen
    public ControladorAlgoritmos(Almacen almacen) {
        this.almacen = almacen;
    }

    // Método que muestra el menú de algoritmos de grafos
    public void ejecutarAlgoritmo() {
        int opcion = EntradaUsuario.leerEntero(
            "\n==== ALGORITMOS DE GRAFOS ====\n" +
            "1. Ruta óptima (Dijkstra)\n" +
            "2. Búsqueda en anchura (BFS)\n" +
            "3. Búsqueda en profundidad (DFS)\n" +
            "4. Detectar ciclos\n" +
            "5. Componentes conexas\n" +
            "6. Zonas aisladas\n" +
            "7. Volver\n" +
            "Seleccione: ", 1, 7);
        
        // Según la opción elegida, se ejecuta el algoritmo correspondiente
        switch (opcion) {
            case 1 -> calcularRutaOptima();
            case 2 -> ejecutarBFS();
            case 3 -> ejecutarDFS();
            case 4 -> detectarCiclos();
            case 5 -> componentesConexas();
            case 6 -> zonasAisladas();
        }
    }

    // Método auxiliar para obtener el grafo completo desde el almacén
    private Map<Ubicacion, List<Ruta>> obtenerGrafo() {
        return almacen.getGrafo().getListaAdyacencia();
    }

    // Algoritmo 1: Calcula la ruta óptima usando Dijkstra
    private void calcularRutaOptima() {
        System.out.println("\n--- CALCULAR RUTA ÓPTIMA ---");
        String origen = EntradaUsuario.leerString("ID Ubicación Origen: ");
        String destino = EntradaUsuario.leerString("ID Ubicación Destino: ");
        
        // Se buscan las ubicaciones en el almacén
        Ubicacion uOrigen = almacen.buscarUbicacion(origen);
        Ubicacion uDestino = almacen.buscarUbicacion(destino);
        
        // Si alguna ubicación no existe, se muestra un mensaje de error
        if (uOrigen == null || uDestino == null) {
            System.out.println("Ubicación no encontrada!");
            return;
        }
        
        // Se obtiene el grafo y se calcula la ruta óptima
        Map<Ubicacion, List<Ruta>> grafo = obtenerGrafo();
        List<Ubicacion> ruta = Dijkstra.calcularRutaOptima(grafo, uOrigen, uDestino);
        
        // Se muestra el resultado
        if (ruta != null && !ruta.isEmpty()) {
            System.out.println("\nRuta óptima:");
            ruta.forEach(u -> System.out.println("-> " + u.getId()));
            System.out.println("Distancia total: " + calcularDistanciaRuta(grafo, ruta));
        } else {
            System.out.println("No se encontró ruta entre las ubicaciones");
        }
    }

    // Método que calcula la distancia total de una ruta
    private double calcularDistanciaRuta(Map<Ubicacion, List<Ruta>> grafo, List<Ubicacion> ruta) {
        double distancia = 0.0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Ubicacion actual = ruta.get(i);
            Ubicacion siguiente = ruta.get(i + 1);
            for (Ruta arista : grafo.get(actual)) {
                if (arista.getDestino().equals(siguiente)) {
                    distancia += arista.getPeso();
                    break;
                }
            }
        }
        return distancia;
    }

    // Algoritmo 2: Búsqueda en anchura (BFS)
    private void ejecutarBFS() {
        System.out.println("\n--- BÚSQUEDA EN ANCHURA (BFS) ---");
        String inicio = EntradaUsuario.leerString("ID Ubicación Inicio: ");
        Ubicacion uInicio = almacen.buscarUbicacion(inicio);
        
        if (uInicio == null) {
            System.out.println("Ubicación no encontrada!");
            return;
        }
        
        System.out.println("\nRecorrido BFS:");
        Map<Ubicacion, List<Ruta>> grafo = obtenerGrafo();
        Busqueda.BFS(grafo, uInicio);
    }

    // Algoritmo 3: Búsqueda en profundidad (DFS)
    private void ejecutarDFS() {
        System.out.println("\n--- BÚSQUEDA EN PROFUNDIDAD (DFS) ---");
        String inicio = EntradaUsuario.leerString("ID Ubicación Inicio: ");
        Ubicacion uInicio = almacen.buscarUbicacion(inicio);
        
        if (uInicio == null) {
            System.out.println("Ubicación no encontrada!");
            return;
        }
        
        System.out.println("\nRecorrido DFS:");
        Map<Ubicacion, List<Ruta>> grafo = obtenerGrafo();
        Busqueda.DFS(grafo, uInicio);
    }

    // Algoritmo 4: Detección de ciclos en el grafo
    private void detectarCiclos() {
        System.out.println("\n--- DETECCIÓN DE CICLOS ---");
        Map<Ubicacion, List<Ruta>> grafo = obtenerGrafo();
        if (DetectorCiclos.tieneciclos(grafo)) {
            System.out.println("El grafo contiene ciclos");
        } else {
            System.out.println("El grafo es acíclico");
        }
    }

    // Algoritmo 5: Contar componentes conexas del grafo
    private void componentesConexas() {
        System.out.println("\n--- COMPONENTES CONEXAS ---");
        Map<Ubicacion, List<Ruta>> grafo = obtenerGrafo();
        int componentes = ComponentesConexas.contarComponentes(grafo);
        System.out.println("Número de componentes conexas: " + componentes);
    }

    // Algoritmo 6: Identificación de zonas aisladas (nodos desconectados)
    private void zonasAisladas() {
        System.out.println("\n--- ZONAS AISLADAS ---");
        Map<Ubicacion, List<Ruta>> grafo = obtenerGrafo();
        List<Ubicacion> aisladas = ComponentesConexas.identificarZonasAisladas(grafo);
        if (aisladas.isEmpty()) {
            System.out.println("No hay zonas aisladas");
        } else {
            System.out.println("Zonas aisladas encontradas:");
            aisladas.forEach(u -> System.out.println("- " + u.getId()));
        }
    }
}