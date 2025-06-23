package modelo;

import estructuras.ArbolBPlus; 
import estructuras.Grafo;
import java.util.*;

public class Almacen {

    // Grafo para modelar las ubicaciones y sus rutas de conexión
    private final Grafo grafo;

    // Mapa de categorías de productos (cada categoría contiene su propio árbol B+)
    private final Map<String, Categoria> categorias;

    // Mapa de ubicaciones para acceso rápido por su ID
    private final Map<String, Ubicacion> ubicacionesMap;

    // Constructor principal del Almacén: inicializa el grafo, categorías y ubicaciones
    public Almacen() {
        this.grafo = new Grafo();
        this.categorias = new HashMap<>();
        this.ubicacionesMap = new HashMap<>();
    }

    // Permite obtener el grafo completo
    public Grafo getGrafo() {
        return this.grafo;
    }

    // Métodos de gestión de Ubicaciones (nodos del grafo)

    public void agregarUbicacion(Ubicacion ubicacion) {
        grafo.agregarNodo(ubicacion);
        ubicacionesMap.put(ubicacion.getId(), ubicacion);
    }

    public boolean eliminarUbicacion(String id) {
        Ubicacion ubicacion = ubicacionesMap.get(id);
        if (ubicacion != null) {
            grafo.eliminarNodo(ubicacion);
            ubicacionesMap.remove(id);
            return true;
        }
        return false;
    }

    public List<Ubicacion> getUbicaciones() {
        return new ArrayList<>(ubicacionesMap.values());
    }

    public Ubicacion buscarUbicacion(String id) {
        return ubicacionesMap.get(id);
    }

    // Métodos de gestión de Rutas (aristas del grafo)

    public boolean agregarRuta(String origenId, String destinoId, double peso) {
        Ubicacion origen = ubicacionesMap.get(origenId);
        Ubicacion destino = ubicacionesMap.get(destinoId);
        if (origen != null && destino != null) {
            grafo.agregarArista(origen, destino, peso);
            return true;
        }
        return false;
    }

    public boolean eliminarRuta(String origenId, String destinoId) {
        Ubicacion origen = ubicacionesMap.get(origenId);
        Ubicacion destino = ubicacionesMap.get(destinoId);
        if (origen != null && destino != null) {
            return grafo.eliminarArista(origen, destino);
        }
        return false;
    }

    public boolean modificarPesoRuta(String origenId, String destinoId, double nuevoPeso) {
        Ubicacion origen = ubicacionesMap.get(origenId);
        Ubicacion destino = ubicacionesMap.get(destinoId);
        if (origen != null && destino != null) {
            return grafo.modificarPesoArista(origen, destino, nuevoPeso);
        }
        return false;
    }

    public List<Ruta> getRutas() {
        return grafo.getAristas();
    }

    // Métodos de gestión de Categorías y Producto

    // Agregar nueva categoría de productos
    public boolean agregarCategoria(String nombre, int orden) {
        if (!categorias.containsKey(nombre)) {
            categorias.put(nombre, new Categoria(nombre, orden));
            return true;
        }
        return false;
    }

    // Agregar nuevo producto a su respectiva categoría
    public boolean agregarProducto(Producto producto) {
        Categoria categoria = categorias.get(producto.getCategoria());
        if (categoria != null) {
            return categoria.agregarProducto(producto);
        }
        return false;
    }

    // Buscar un producto por su ID recorriendo todas las categorías
    public Producto buscarProducto(String id) {
        for (Categoria categoria : categorias.values()) {
            Producto producto = categoria.buscarProducto(id);
            if (producto != null) {
                return producto;
            }
        }
        return null;
    }

    public boolean eliminarProducto(String id) {
        for (Categoria categoria : categorias.values()) {
            if (categoria.eliminarProducto(id)) {
                return true;
            }
        }
        return false;
    }

    // Obtener el árbol B+ de una categoría específica
    public ArbolBPlus<Producto> getArbolDeCategoria(String nombreCategoria) {
        Categoria categoria = categorias.get(nombreCategoria);
        if (categoria != null) {
            return categoria.getArbolProductos();
        }
        return null;
    }

    public Categoria getCategoria(String nombre) {
        return categorias.get(nombre);
    }

    public Map<String, Categoria> getCategorias() {
        return categorias;
    }

    // Obtener el primer árbol B+ existente (para algunos algoritmos de visualización)
    public ArbolBPlus<Producto> getPrimerArbolBPlus() {
        if (!categorias.isEmpty()) {
            String primeraCategoria = categorias.keySet().iterator().next();
            return categorias.get(primeraCategoria).getArbolProductos();
        }
        return null;
    }

    // Algoritmos aplicados sobre el grafo

    public List<Ubicacion> calcularRutaOptima(String origenId, String destinoId) {
        Ubicacion origen = ubicacionesMap.get(origenId);
        Ubicacion destino = ubicacionesMap.get(destinoId);
        if (origen != null && destino != null) {
            return grafo.calcularRutaOptima(origen, destino);
        }
        return Collections.emptyList();
    }

    public void ejecutarBFS(String inicioId) {
        Ubicacion inicio = ubicacionesMap.get(inicioId);
        if (inicio != null) {
            grafo.recorridoBFS(inicio);
        }
    }

    public void ejecutarDFS(String inicioId) {
        Ubicacion inicio = ubicacionesMap.get(inicioId);
        if (inicio != null) {
            grafo.recorridoDFS(inicio);
        }
    }

    public boolean detectarCiclos() {
        return grafo.tieneCiclos();
    }

    public int contarComponentesConexas() {
        return grafo.componentesConexas();
    }

    public List<Ubicacion> identificarZonasAisladas() {
        return grafo.zonasAisladas();
    }

    // Métodos de simulación

    public void cerrarRuta(String origenId, String destinoId) {
        eliminarRuta(origenId, destinoId);
    }

    public void modificarDemanda(String idProducto, int nuevaDemanda) {
        Producto producto = buscarProducto(idProducto);
        if (producto != null) {
            producto.setStock(nuevaDemanda);
        }
    }

    public void reubicarZona(String idZona) {
        // Aquí se colocaría la lógica avanzada de reubicación de zonas (simulada)
        System.out.println("Reubicando zona: " + idZona);
    }

    // Método auxiliar para cálculo de distancia de una ruta
    public double getDistanciaRuta(List<Ubicacion> ruta) {
        double distancia = 0.0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Ruta arista = grafo.getArista(ruta.get(i), ruta.get(i + 1));
            if (arista != null) {
                distancia += arista.getPeso();
            }
        }
        return distancia;
    }
}
