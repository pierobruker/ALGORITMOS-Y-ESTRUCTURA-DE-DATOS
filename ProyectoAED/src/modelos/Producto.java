package modelos;

public class Producto {
    private String codigo;
    private String nombre;
    private String lote;
    private String categoria;

    public Producto(String codigo, String nombre, String lote, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.lote = lote;
        this.categoria = categoria;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getLote() { return lote; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return "[" + categoria + "] " + nombre + " (Lote: " + lote + ")";
    }
}
