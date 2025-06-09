package Testejercicios;

import graph.GraphLink;
import graph.Vertex;

public class TestEjercicio5 {

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

        // Insertar aristas
        graph.insertEdge(v1, v2, 10);
        graph.insertEdge(v2, v3, 5);
        graph.insertEdge(v3, v4, 7);

        // Verificar el tipo de grafo
        System.out.println("\n¿Es un grafo un camino?");
        System.out.println(graph.isPath());  // Debería devolver true

        System.out.println("\n¿Es un grafo un ciclo?");
        System.out.println(graph.isCycle());  // Debería devolver false

        System.out.println("\n¿Es un grafo una rueda?");
        System.out.println(graph.isWheel());  // Debería devolver false

        System.out.println("\n¿Es un grafo completo?");
        System.out.println(graph.isComplete());  // Debería devolver false
    }
}
