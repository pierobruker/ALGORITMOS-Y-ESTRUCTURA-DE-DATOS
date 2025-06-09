package Testejercicios;

import graph.GraphListEdge;
import graph.VertexObj;

public class TestEjercicio3 {

    public static void main(String[] args) {
        // Crear instancias de vértices
        VertexObj<String, Integer> v1 = new VertexObj<>("A", 0);
        VertexObj<String, Integer> v2 = new VertexObj<>("B", 1);
        VertexObj<String, Integer> v3 = new VertexObj<>("C", 2);
        VertexObj<String, Integer> v4 = new VertexObj<>("D", 3);

        // Crear el grafo
        GraphListEdge<String, Integer> graph = new GraphListEdge<>();

        // Insertar vértices
        graph.insertVertex(v1);
        graph.insertVertex(v2);
        graph.insertVertex(v3);
        graph.insertVertex(v4);

        // Insertar aristas (con peso)
        graph.insertEdge(v1, v2, 10);
        graph.insertEdge(v1, v3, 5);
        graph.insertEdge(v2, v4, 2);

        // Realizar BFS a partir de un vértice
        graph.bfs(v1);  // Recorrido BFS desde el vértice "A"
    }
}
