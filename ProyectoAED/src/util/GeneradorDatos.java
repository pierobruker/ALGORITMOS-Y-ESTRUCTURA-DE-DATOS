package util;

import modelo.Ubicacion;
import modelo.Ruta;
import modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorDatos {

    // Objeto Random para generar números aleatorios
    private static final Random random = new Random();

    // Tipos posibles de ubicaciones
    private static final String[] TIPOS_UBICACION = {"ESTANTERIA", "PASILLO", "CARGA"};

    // Categorías disponibles de productos
    private static final String[] CATEGORIAS = {"Electrónicos", "Ropa", "Alimentos", "Hogar", "Deportes"};

    // Posibles nombres de productos (para variedad de ejemplos)
    private static final String[] NOMBRES_PRODUCTOS = {
        "Laptop", "Smartphone", "Camisa", "Pantalón", "Arroz", "Aceite",
        "Silla", "Mesa", "Balón", "Raqueta", "Libro", "Impresora"
    };

    // Método para generar una lista de ubicaciones aleatorias
    public static List<Ubicacion> generarUbicaciones(int cantidad) {
        List<Ubicacion> ubicaciones = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            String id = "U" + i;  // ID único de la forma U1, U2, U3...
            String nombre = "Ubicación " + i;
            String tipo = TIPOS_UBICACION[random.nextInt(TIPOS_UBICACION.length)];
            ubicaciones.add(new Ubicacion(id, nombre, tipo));
        }
        return ubicaciones;
    }

    // Método para generar una lista de rutas aleatorias
    public static List<Ruta> generarRutas(List<Ubicacion> ubicaciones, int cantidad) {
        List<Ruta> rutas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            // Se selecciona aleatoriamente un origen y un destino distintos
            Ubicacion origen = ubicaciones.get(random.nextInt(ubicaciones.size()));
            Ubicacion destino;
            do {
                destino = ubicaciones.get(random.nextInt(ubicaciones.size()));
            } while (destino.equals(origen));
            
            // Peso aleatorio entre 1 y 50
            double peso = 1 + random.nextDouble() * 49;
            rutas.add(new Ruta(origen, destino, peso));
        }
        return rutas;
    }

    // Método para generar una lista de productos aleatorios (para varias categorías)
    public static List<Producto> generarProductos(int cantidad) {
        List<Producto> productos = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            String id = "P" + i; // IDs P1, P2, etc.
            String nombre = NOMBRES_PRODUCTOS[random.nextInt(NOMBRES_PRODUCTOS.length)];
            String categoria = CATEGORIAS[random.nextInt(CATEGORIAS.length)];
            int stock = 10 + random.nextInt(991);  // Stock entre 10 y 1000
            double precio = 1 + random.nextDouble() * 999; // Precio entre 1 y 1000
            
            productos.add(new Producto(id, nombre, categoria, stock, precio));
        }
        return productos;
    }

    // Método para generar productos aleatorios de una categoría específica
    public static List<Producto> generarProductosParaCategoria(String categoria, int cantidad) {
        List<Producto> productos = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            String id = "P" + categoria.charAt(0) + i; // IDs como PE1, PR1, PA1...
            String nombre = NOMBRES_PRODUCTOS[random.nextInt(NOMBRES_PRODUCTOS.length)];
            int stock = 10 + random.nextInt(991);
            double precio = 1 + random.nextDouble() * 999;
            
            productos.add(new Producto(id, nombre, categoria, stock, precio));
        }
        return productos;
    }
}
