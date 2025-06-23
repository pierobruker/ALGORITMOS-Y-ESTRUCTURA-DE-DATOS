package modelo;

import estructuras.ArbolBPlus;
import java.util.List;

public class Categoria {

    // Nombre de la categoría (identificador principal)
    private final String nombre;

    // Árbol B+ que almacena los productos de esta categoría
    private final ArbolBPlus<Producto> arbolProductos;

    // Constructor: recibe el nombre de la categoría y el orden para el árbol B+
    public Categoria(String nombre, int orden) {
        this.nombre = nombre;
        this.arbolProductos = new ArbolBPlus<>(orden);
    }

    // Métodos de gestión de productos por categoría

    // Agrega un nuevo producto al árbol B+ de la categoría
    public boolean agregarProducto(Producto producto) {
        return arbolProductos.insertar(producto);
    }

    // Elimina un producto por su ID (crea un objeto temporal solo con el ID)
    public boolean eliminarProducto(String id) {
        Producto producto = new Producto(id, "", "", 0, 0.0);
        return arbolProductos.eliminar(producto);
    }

    // Busca un producto por su ID
    public Producto buscarProducto(String id) {
        Producto producto = new Producto(id, "", "", 0, 0.0);
        return arbolProductos.buscar(producto);
    }

    // Devuelve la lista completa de productos almacenados en la categoría
    public List<Producto> listarProductos() {
        return arbolProductos.listar();
    }

    // Getters de la clase

    public String getNombre() {
        return nombre;
    }

    public ArbolBPlus<Producto> getArbolProductos() {
        return arbolProductos;
    }

    // Representación textual de la categoría
    @Override
    public String toString() {
        return nombre + " (" + arbolProductos.tamano() + " productos)";
    }
}
