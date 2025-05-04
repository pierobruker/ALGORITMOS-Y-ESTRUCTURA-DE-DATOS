import java.util.Objects;

public class Tarea implements Comparable<Tarea> {
    private String descripcion;
    private int prioridad;  // 1 - más urgente, 10 - menos urgente

    public Tarea(String descripcion, int prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Tarea: " + descripcion + " | Prioridad: " + prioridad;
    }

    // Implementación de equals y hashCode para comparación
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarea tarea = (Tarea) obj;
        return prioridad == tarea.prioridad && descripcion.equals(tarea.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, prioridad);
    }

    // Implementación de compareTo para ordenar las tareas por prioridad
    @Override
    public int compareTo(Tarea otraTarea) {
        return Integer.compare(this.prioridad, otraTarea.prioridad);
    }
}
