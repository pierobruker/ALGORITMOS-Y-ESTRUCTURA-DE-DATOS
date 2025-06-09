package graph;

public class EdgeObj<V, E> {
    protected E info;  // Información adicional de la arista (peso, etc.)
    protected VertexObj<V, E> endVertex1;  // Primer vértice de la arista
    protected VertexObj<V, E> endVertex2;  // Segundo vértice de la arista
    protected int position;  // Posición de la arista en la lista

    // Constructor
    public EdgeObj(VertexObj<V, E> vert1, VertexObj<V, E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    // Getters y Setters
    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public VertexObj<V, E> getEndVertex1() {
        return endVertex1;
    }

    public void setEndVertex1(VertexObj<V, E> endVertex1) {
        this.endVertex1 = endVertex1;
    }

    public VertexObj<V, E> getEndVertex2() {
        return endVertex2;
    }

    public void setEndVertex2(VertexObj<V, E> endVertex2) {
        this.endVertex2 = endVertex2;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "info=" + info +
                ", endVertex1=" + endVertex1 +
                ", endVertex2=" + endVertex2 +
                ", position=" + position +
                '}';
    }
}
