package graph;

public class Edge {
    private Vertex v1, v2;
    private int weight;  // Peso de la arista (para grafos ponderados)

    public Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // Método equals para comparar dos aristas
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        // La arista es la misma si v1 y v2 son iguales (sin importar el orden)
        return (v1.equals(edge.v1) && v2.equals(edge.v2)) || (v1.equals(edge.v2) && v2.equals(edge.v1));
    }

    @Override
    public int hashCode() {
        return v1.hashCode() + v2.hashCode();
    }

    @Override
    public String toString() {
        return "(" + v1.getId() + " -> " + v2.getId() + ", weight: " + weight + ")";
    }
}
