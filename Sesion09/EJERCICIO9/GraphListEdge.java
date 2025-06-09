package EJERCICIO9;

import java.util.*;

public class GraphListEdge<V, E> {
    List<Vertex> secVertex;  // Lista de vértices
    List<Edge> secEdge;      // Lista de aristas

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // Agregar vértice
    public void insertVertex(Vertex v) {
        secVertex.add(v);
    }

    // Agregar arista
    public void insertEdge(Vertex v1, Vertex v2, int weight) {
        secEdge.add(new Edge(v1, v2, weight));
        v1.addEdge(new Edge(v1, v2, weight));
        v2.addEdge(new Edge(v2, v1, weight));  // Grafo no dirigido
    }

    // Ejercicio 9.a - Verificar si los grafos son isomorfos
    public boolean isIsomorphic(GraphListEdge<V, E> otherGraph) {
        if (this.secVertex.size() != otherGraph.secVertex.size()) {
            return false;
        }

        for (Vertex vertex : this.secVertex) {
            boolean matchFound = false;
            for (Vertex otherVertex : otherGraph.secVertex) {
                if (compareEdges(vertex, otherVertex)) {
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                return false;
            }
        }
        return true;
    }

    private boolean compareEdges(Vertex vertex1, Vertex vertex2) {
        if (vertex1.getEdges().size() != vertex2.getEdges().size()) {
            return false;
        }
        for (Edge edge1 : vertex1.getEdges()) {
            boolean edgeMatch = false;
            for (Edge edge2 : vertex2.getEdges()) {
                if (edge1.equals(edge2)) {
                    edgeMatch = true;
                    break;
                }
            }
            if (!edgeMatch) {
                return false;
            }
        }
        return true;
    }

    // Ejercicio 9.b - Verificar si el grafo es plano (simplificado, no implementado completamente)
    public boolean isPlanar() {
        for (Edge edge : secEdge) {
            if (hasCrossingEdge(edge)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCrossingEdge(Edge edge) {
        return false; // Este es solo un esbozo. Implementar un algoritmo de detección de cruces
    }

    // Ejercicio 9.c - Verificar si el grafo es conexo
    public boolean isConnected() {
        Vertex startVertex = secVertex.get(0);
        dfs(startVertex);

        for (Vertex vertex : secVertex) {
            if (!vertex.isVisited()) {
                return false;
            }
        }
        return true;
    }

    private void dfs(Vertex vertex) {
        vertex.setVisited(true);
        for (Edge edge : vertex.getEdges()) {
            Vertex adjacent = (edge.getV1().equals(vertex)) ? edge.getV2() : edge.getV1();
            if (!adjacent.isVisited()) {
                dfs(adjacent);
            }
        }
    }

    // Ejercicio 9.d - Verificar si el grafo es auto-complementario
    public boolean isAutocomplementary() {
        GraphListEdge<V, E> complementGraph = generateComplementGraph();
        return this.isIsomorphic(complementGraph);
    }

    private GraphListEdge<V, E> generateComplementGraph() {
        GraphListEdge<V, E> complementGraph = new GraphListEdge<>();

        for (Vertex vertex : secVertex) {
            complementGraph.insertVertex(new Vertex(vertex.getInfo()));
        }

        for (Vertex vertex1 : secVertex) {
            for (Vertex vertex2 : secVertex) {
                if (!vertex1.equals(vertex2) && !searchEdge(vertex1, vertex2)) {
                    complementGraph.insertEdge(vertex1, vertex2, 0);  // Peso = 0 por simplicidad
                }
            }
        }
        return complementGraph;
    }

    private boolean searchEdge(Vertex v1, Vertex v2) {
        for (Edge edge : secEdge) {
            if ((edge.getV1().equals(v1) && edge.getV2().equals(v2)) || 
                (edge.getV1().equals(v2) && edge.getV2().equals(v1))) {
                return true;
            }
        }
        return false;
    }
}

