package controlador;

import modelo.Almacen;
import modelo.Ubicacion;
import modelo.Ruta;
import util.EntradaUsuario;
import java.util.List;

public class ControladorGrafo {

    // Este controlador también trabaja sobre el almacén
    private final Almacen almacen;

    // Constructor que recibe la instancia de Almacen
    public ControladorGrafo(Almacen almacen) {
        this.almacen = almacen;
    }

    // Menú para gestionar las ubicaciones dentro del grafo
    public void gestionarUbicaciones() {
        while (true) {
            int opcion = EntradaUsuario.leerEntero(
                "\n==== GESTIÓN DE UBICACIONES ====\n" +
                "1. Agregar ubicación\n" +
                "2. Eliminar ubicación\n" +
                "3. Listar ubicaciones\n" +
                "4. Buscar ubicación\n" +
                "5. Volver\n" +
                "Seleccione: ", 1, 5);
            
            // Según la opción elegida, se ejecuta el método correspondiente
            switch (opcion) {
                case 1 -> agregarUbicacion();
                case 2 -> eliminarUbicacion();
                case 3 -> listarUbicaciones();
                case 4 -> buscarUbicacion();
                case 5 -> { return; }
            }
        }
    }

    // Opción 1: Agregar una nueva ubicación al grafo
    private void agregarUbicacion() {
        System.out.println("\n--- AGREGAR UBICACIÓN ---");
        String id = EntradaUsuario.leerString("ID Ubicación: ");
        String nombre = EntradaUsuario.leerString("Nombre: ");
        String tipo = EntradaUsuario.leerTipoUbicacion("Tipo (ESTANTERIA/PASILLO/CARGA): ");
        // Se crea la nueva ubicación y se agrega al almacén
        almacen.agregarUbicacion(new Ubicacion(id, nombre, tipo));
        System.out.println("Ubicación agregada exitosamente!");
    }

    // Opción 2: Eliminar una ubicación existente
    private void eliminarUbicacion() {
        System.out.println("\n--- ELIMINAR UBICACIÓN ---");
        String id = EntradaUsuario.leerString("ID Ubicación a eliminar: ");
        if (almacen.eliminarUbicacion(id)) {
            System.out.println("Ubicación eliminada exitosamente!");
        } else {
            System.out.println("Error: Ubicación no encontrada");
        }
    }

    // Opción 3: Mostrar la lista completa de ubicaciones registradas
    private void listarUbicaciones() {
        System.out.println("\n--- LISTADO DE UBICACIONES ---");
        List<Ubicacion> ubicaciones = almacen.getUbicaciones();
        if (ubicaciones.isEmpty()) {
            System.out.println("No hay ubicaciones registradas");
        } else {
            ubicaciones.forEach(System.out::println);
        }
    }

    // Opción 4: Buscar una ubicación específica por su ID
    private void buscarUbicacion() {
        System.out.println("\n--- BUSCAR UBICACIÓN ---");
        String id = EntradaUsuario.leerString("ID Ubicación: ");
        Ubicacion u = almacen.buscarUbicacion(id);
        if (u != null) {
            System.out.println("Ubicación encontrada: " + u);
        } else {
            System.out.println("Ubicación no encontrada");
        }
    }

    // Menú para gestionar las rutas entre ubicaciones
    public void gestionarRutas() {
        while (true) {
            int opcion = EntradaUsuario.leerEntero(
                "\n==== GESTIÓN DE RUTAS ====\n" +
                "1. Agregar ruta\n" +
                "2. Eliminar ruta\n" +
                "3. Listar rutas\n" +
                "4. Modificar peso de ruta\n" +
                "5. Volver\n" +
                "Seleccione: ", 1, 5);
            
            // Según la opción elegida, se ejecuta el método correspondiente
            switch (opcion) {
                case 1 -> agregarRuta();
                case 2 -> eliminarRuta();
                case 3 -> listarRutas();
                case 4 -> modificarPesoRuta();
                case 5 -> { return; }
            }
        }
    }

    // Opción 1: Agregar una nueva ruta entre dos ubicaciones
    private void agregarRuta() {
        System.out.println("\n--- AGREGAR RUTA ---");
        String origen = EntradaUsuario.leerString("ID Ubicación Origen: ");
        String destino = EntradaUsuario.leerString("ID Ubicación Destino: ");
        double peso = EntradaUsuario.leerDouble("Peso (distancia/tiempo): ", 0, Double.MAX_VALUE);
        
        // Se agrega la ruta en el modelo
        if (almacen.agregarRuta(origen, destino, peso)) {
            System.out.println("Ruta agregada exitosamente!");
        } else {
            System.out.println("Error: No se pudo agregar la ruta");
        }
    }

    // Opción 2: Eliminar una ruta existente
    private void eliminarRuta() {
        System.out.println("\n--- ELIMINAR RUTA ---");
        String origen = EntradaUsuario.leerString("ID Ubicación Origen: ");
        String destino = EntradaUsuario.leerString("ID Ubicación Destino: ");
        
        if (almacen.eliminarRuta(origen, destino)) {
            System.out.println("Ruta eliminada exitosamente!");
        } else {
            System.out.println("Error: Ruta no encontrada");
        }
    }

    // Opción 3: Mostrar todas las rutas existentes en el sistema
    private void listarRutas() {
        System.out.println("\n--- LISTADO DE RUTAS ---");
        List<Ruta> rutas = almacen.getRutas();
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas registradas");
        } else {
            rutas.forEach(System.out::println);
        }
    }

    // Opción 4: Modificar el peso (distancia o tiempo) de una ruta existente
    private void modificarPesoRuta() {
        System.out.println("\n--- MODIFICAR PESO DE RUTA ---");
        String origen = EntradaUsuario.leerString("ID Ubicación Origen: ");
        String destino = EntradaUsuario.leerString("ID Ubicación Destino: ");
        double nuevoPeso = EntradaUsuario.leerDouble("Nuevo peso: ", 0, Double.MAX_VALUE);
        
        if (almacen.modificarPesoRuta(origen, destino, nuevoPeso)) {
            System.out.println("Peso de ruta actualizado!");
        } else {
            System.out.println("Error: Ruta no encontrada");
        }
    }
}