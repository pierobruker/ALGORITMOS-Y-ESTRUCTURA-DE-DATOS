package EJERCICIO9;

public class Edge {
    private Vertex v1, v2;
    private int weight;

    public Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return (v1.equals(edge.v1) && v2.equals(edge.v2)) || (v1.equals(edge.v2) && v2.equals(edge.v1));
    }

    @Override
    public int hashCode() {
        return v1.hashCode() + v2.hashCode();
    }

    @Override
    public String toString() {
        return v1.getInfo() + " <-> " + v2.getInfo() + " (weight: " + weight + ")";
    }
}

