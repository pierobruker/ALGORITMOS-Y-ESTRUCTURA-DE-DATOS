package controlador;

import java.util.ArrayList; 
import java.util.List;
import modelo.Almacen;
import util.EntradaUsuario;
import visualizador.Visualizador;

public class ControladorAlmacen {

    // Declaración de los controladores y el modelo principal de Almacén
    private final Almacen almacen;
    private final ControladorGrafo ctrlGrafo;
    private final ControladorArbolB ctrlArbol;
    private final ControladorAlgoritmos ctrlAlgoritmos;
    private final ControladorVisualizacion ctrlVisualizacion;

    // Constructor que inicializa el sistema completo
    public ControladorAlmacen() {
        // Se crea la instancia principal de Almacen
        this.almacen = new Almacen();
        // Se crean los controladores pasándoles el almacén, para que puedan trabajar sobre el mismo conjunto de datos
        this.ctrlGrafo = new ControladorGrafo(almacen);
        this.ctrlArbol = new ControladorArbolB(almacen);
        this.ctrlAlgoritmos = new ControladorAlgoritmos(almacen);
        this.ctrlVisualizacion = new ControladorVisualizacion(almacen);
    }

    // Método principal que mantiene el sistema en ejecución mientras el usuario no elija salir
    public void iniciarSistema() {
        while (true) {
            // Se muestra el menú principal y se lee la opción seleccionada
            int opcion = EntradaUsuario.leerEntero(
                "\n==== SISTEMA DE GESTIÓN DE ALMACENES ====\n" +
                "1. Gestionar Ubicaciones\n" +
                "2. Gestionar Rutas\n" +
                "3. Gestionar Productos\n" +
                "4. Ejecutar Algoritmos\n" +
                "5. Simular Escenarios\n" +
                "6. Visualizar Grafo de Rutas\n" +
                "7. Visualizar Árbol B+ de Productos\n" +
                "8. Salir\n" +
                "Seleccione opción: ", 1, 8);
            
            // Dependiendo de la opción elegida, se llama al método correspondiente
            switch (opcion) {
                case 1 -> ctrlGrafo.gestionarUbicaciones();
                case 2 -> ctrlGrafo.gestionarRutas();
                case 3 -> ctrlArbol.gestionarProductos();
                case 4 -> ctrlAlgoritmos.ejecutarAlgoritmo();
                case 5 -> simularEscenarios();
                case 6 -> ctrlVisualizacion.mostrarGrafo();
                case 7 -> ctrlVisualizacion.mostrarArbolBPlus();
                case 8 -> {
                    // Si el usuario elige salir, se termina el programa
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                }
            }
        }
    }

    // Método que presenta el submenú de simulaciones de escenarios
    private void simularEscenarios() {
        int opcion = EntradaUsuario.leerEntero(
            "\n==== SIMULACIÓN DE ESCENARIOS ====\n" +
            "1. Cerrar ruta temporalmente\n" +
            "2. Modificar demanda de producto\n" +
            "3. Reubicar zona de almacén\n" +
            "4. Volver\n" +
            "Seleccione: ", 1, 4);
        
        // Ejecuta la simulación correspondiente según la opción
        switch (opcion) {
            case 1 -> cerrarRuta();
            case 2 -> modificarDemanda();
            case 3 -> reubicarZona();
        }
    }

    // Método que simula el cierre temporal de una ruta
    private void cerrarRuta() {
        System.out.println("\n--- CERRAR RUTA ---");
        String origen = EntradaUsuario.leerString("ID Ubicación Origen: ");
        String destino = EntradaUsuario.leerString("ID Ubicación Destino: ");
        // Se llama al modelo para cerrar la ruta indicada
        almacen.cerrarRuta(origen, destino);
        System.out.println("Ruta cerrada. Recalculando rutas óptimas...");
    }

    // Método que permite modificar la demanda de un producto
    private void modificarDemanda() {
        System.out.println("\n--- MODIFICAR DEMANDA ---");
        String idProducto = EntradaUsuario.leerString("ID Producto: ");
        int nuevaDemanda = EntradaUsuario.leerEntero("Nueva demanda: ", 0, Integer.MAX_VALUE);
        almacen.modificarDemanda(idProducto, nuevaDemanda);
        System.out.println("Demanda actualizada.");
    }

    // Método para reubicar una zona completa de almacén
    private void reubicarZona() {
        System.out.println("\n--- REUBICAR ZONA ---");
        String idZona = EntradaUsuario.leerString("ID Zona a reubicar: ");
        almacen.reubicarZona(idZona);
        System.out.println("Zona reubicada. Reorganizando inventario...");
    }
}