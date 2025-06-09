package EJERCICIO9;

import java.util.List;
import java.util.ArrayList;

public class Vertex {
    private String info;
    private boolean visited;  // Atributo para marcar si el vértice ha sido visitado
    private List<Edge> edges;  // Lista de aristas conectadas al vértice

    public Vertex(String info) {
        this.info = info;
        this.visited = false;
        this.edges = new ArrayList<>();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return info.equals(vertex.info);
    }

    @Override
    public int hashCode() {
        return info.hashCode();
    }

    @Override
    public String toString() {
        return info;
    }
}