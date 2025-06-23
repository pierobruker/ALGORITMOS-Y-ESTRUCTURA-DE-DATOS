package modelo;

public class Producto implements Comparable<Producto> {

    // Atributos principales del producto
    private final String id;       // Identificador único del producto
    private String nombre;         // Nombre descriptivo del producto
    private String categoria;      // Nombre de la categoría a la que pertenece
    private int stock;             // Cantidad disponible en inventario
    private double precio;         // Precio del producto

    // Constructor que inicializa todos los campos del producto
    public Producto(String id, String nombre, String categoria, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    // Getters y Setters de la clase

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Representación en texto del producto (útil para imprimir)

    @Override
    public String toString() {
        return nombre + " (" + id + ") - Stock: " + stock + " - $" + precio;
    }

    // Implementación de la interfaz Comparable (para ordenar en el árbol B+)
    @Override
    public int compareTo(Producto otro) {
        // Se compara usando el ID (identificador único)
        return this.id.compareTo(otro.id);
    }

    // Métodos equals() y hashCode() para identificar productos por su ID	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id.equals(producto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
