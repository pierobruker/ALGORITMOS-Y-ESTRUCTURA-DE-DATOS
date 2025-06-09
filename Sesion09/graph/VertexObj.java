package graph;

public class VertexObj<V, E> {
    protected V info;  // Información del vértice (puede ser cualquier tipo de dato)
    protected int position;  // Posición del vértice en la lista

    // Constructor
    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    // Getters y Setters
    public V getInfo() {
        return info;
    }

    public void setInfo(V info) {
        this.info = info;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Vertex{" + "info=" + info + ", position=" + position + '}';
    }
}
