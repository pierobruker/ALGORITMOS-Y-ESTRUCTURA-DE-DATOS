package prueba;

import algoritmos.*;
import java.util.*;
import modelos.*;
import util.ExcepcionUtils;

public class Main {
    static Grafo grafo = new Grafo();
    static ArbolBPlus arbol = new ArbolBPlus();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE INVENTARIO ---");
            System.out.println("1. Agregar ubicación");
            System.out.println("2. Conectar ubicaciones (arista)");
            System.out.println("3. Mostrar grafo");
            System.out.println("4. Calcular ruta óptima (Dijkstra)");
            System.out.println("5. Recorrido BFS");
            System.out.println("6. Recorrido DFS");
            System.out.println("7. Detectar ciclos");
            System.out.println("8. Ver componentes conexas");
            System.out.println("9. Insertar producto en árbol B+");
            System.out.println("10. Buscar producto en árbol B+");
            System.out.println("11. Mostrar árbol B+");
            System.out.println("0. Salir");
            int opcion = ExcepcionUtils.leerEnteroSeguro(scanner, "Seleccione una opción: ");

            switch (opcion) {
                case 1 -> agregarUbicacion();
                case 2 -> conectarUbicaciones();
                case 3 -> mostrarGrafo();
                case 4 -> calcularRutaOptima();
                case 5 -> ejecutarBFS();
                case 6 -> ejecutarDFS();
                case 7 -> detectarCiclos();
                case 8 -> mostrarComponentesConexas();
                case 9 -> insertarProducto();
                case 10 -> buscarProducto();
                case 11 -> arbol.imprimir();
                case 0 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void agregarUbicacion() {
        int id = ExcepcionUtils.leerEnteroSeguro(scanner, "ID numérico de la ubicación: ");
        String nombre = ExcepcionUtils.leerLinea(scanner, "Nombre: ");
        Nodo nodo = new Nodo(id, nombre);
        grafo.agregarNodo(nodo);
        System.out.println("Ubicación agregada correctamente.");
    }

    private static void conectarUbicaciones() {
        int idOrigen = ExcepcionUtils.leerEnteroSeguro(scanner, "ID origen: ");
        int idDestino = ExcepcionUtils.leerEnteroSeguro(scanner, "ID destino: ");
        double peso = ExcepcionUtils.leerDoubleSeguro(scanner, "Peso (distancia/tiempo): ");
        Nodo origen = grafo.getNodo(idOrigen);
        Nodo destino = grafo.getNodo(idDestino);

        if (origen != null && destino != null) {
            grafo.agregarArista(origen, destino, peso);
            System.out.println("Ubicaciones conectadas correctamente.");
        } else {
            System.out.println("Una o ambas ubicaciones no existen.");
        }
    }

    private static void mostrarGrafo() {
        System.out.println("\n--- GRAFO DEL ALMACÉN ---");
        for (Nodo nodo : grafo.getNodos()) {
            System.out.println("Desde " + nodo + ":");
            for (Arista arista : grafo.getAristasDesde(nodo)) {
                System.out.println("  -> " + arista.getDestino().getNombre() + " [" + arista.getPeso() + "]");
            }
        }
    }

    private static void calcularRutaOptima() {
        int inicioId = ExcepcionUtils.leerEnteroSeguro(scanner, "ID de nodo inicio: ");
        int finId = ExcepcionUtils.leerEnteroSeguro(scanner, "ID de nodo destino: ");
        Nodo inicio = grafo.getNodo(inicioId);
        Nodo destino = grafo.getNodo(finId);

        if (inicio != null && destino != null) {
            Dijkstra.calcularRuta(grafo, inicio, destino);
        } else {
            System.out.println("Nodo(s) no válidos para calcular ruta.");
        }
    }

    private static void ejecutarBFS() {
        int id = ExcepcionUtils.leerEnteroSeguro(scanner, "ID del nodo de inicio: ");
        Nodo inicio = grafo.getNodo(id);
        if (inicio != null) {
            BFS.ejecutar(grafo, inicio);
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }

    private static void ejecutarDFS() {
        int id = ExcepcionUtils.leerEnteroSeguro(scanner, "ID del nodo de inicio: ");
        Nodo inicio = grafo.getNodo(id);
        if (inicio != null) {
            DFS.ejecutar(grafo, inicio);
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }

    private static void detectarCiclos() {
        boolean ciclo = Ciclos.tieneCiclo(grafo);
        System.out.println(ciclo ? "El grafo contiene al menos un ciclo." : "El grafo no contiene ciclos.");
    }

    private static void mostrarComponentesConexas() {
        List<Set<Nodo>> componentes = ComponentesConexas.encontrarComponentes(grafo);
        System.out.println("\nComponentes conexas encontradas: " + componentes.size());
        for (int i = 0; i < componentes.size(); i++) {
            Set<Nodo> comp = componentes.get(i);
            System.out.println("Componente #" + (i + 1) + ":");
            for (Nodo n : comp) {
                System.out.println(" - " + n.getNombre());
            }
            if (comp.size() == 1) {
                System.out.println("Zona aislada detectada.");
            }
            System.out.println();
        }
    }

    private static void insertarProducto() {
        String codigo = ExcepcionUtils.leerLinea(scanner, "Código del producto: ");
        String nombre = ExcepcionUtils.leerLinea(scanner, "Nombre del producto: ");
        String lote = ExcepcionUtils.leerLinea(scanner, "Lote del producto: ");
        String categoria = ExcepcionUtils.leerLinea(scanner, "Categoría del producto: ");
        Producto producto = new Producto(codigo, nombre, lote, categoria);
        arbol.insertar(producto);
    }

    private static void buscarProducto() {
        String codigo = ExcepcionUtils.leerLinea(scanner, "Ingrese el código del producto a buscar: ");
        Producto resultado = arbol.buscar(codigo);
        if (resultado != null) {
            System.out.println("Producto encontrado: " + resultado);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}
