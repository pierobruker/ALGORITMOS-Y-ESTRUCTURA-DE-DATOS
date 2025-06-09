package Testejercicios;

import graph.GraphLink;
import graph.Vertex;

import java.util.List;

public class TestEjercicio1 {

    public static void main(String[] args) {
        // Crear instancias de vértices
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");

        // Crear el grafo
        GraphLink graph = new GraphLink();

        // Insertar vértices
        graph.insertVertex(v1);
        graph.insertVertex(v2);
        graph.insertVertex(v3);
        graph.insertVertex(v4);

        // Insertar aristas (con peso)
        graph.insertEdgeWeight(v1, v2, 10);
        graph.insertEdgeWeight(v1, v3, 5);
        graph.insertEdgeWeight(v2, v4, 2);
        graph.insertEdgeWeight(v3, v4, 7);

        // Realizar BFS para encontrar el camino entre dos vértices
        System.out.println("\nProbando BFS para encontrar el camino entre A y D:");
        graph.bfsPath(v1, v4);  // Debería devolver el camino de A a D

        // Realizar Dijkstra para encontrar el camino más corto entre dos vértices
        System.out.println("\nProbando Dijkstra para encontrar el camino más corto entre A y D:");
        List<Vertex> shortestPath = graph.shortestPath(v1, v4);  // Debería devolver el camino más corto de A a D
    }
}
