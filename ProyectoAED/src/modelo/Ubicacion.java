package modelo;

public class Ubicacion {

    // Identificador único de la ubicación
    private final String id;

    // Nombre descriptivo de la ubicación
    private String nombre;

    // Tipo de ubicación (por ejemplo: ESTANTERIA, PASILLO, CARGA, etc.)
    private String tipo;

    // Constructor: inicializa todos los campos de la ubicación
    public Ubicacion(String id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Representación textual de la ubicación
    
    @Override
    public String toString() {
        return nombre + " (" + id + ") - " + tipo;
    }

    // Igualdad y hashCode basados en el ID único
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return id.equals(ubicacion.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
