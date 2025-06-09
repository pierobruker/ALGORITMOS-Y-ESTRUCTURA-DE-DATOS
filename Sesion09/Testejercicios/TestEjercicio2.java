package Testejercicios;

import graph.GraphLink;
import graph.Vertex;

public class TestEjercicio2 {

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
        graph.insertEdgeWeight(v1, v2, 10);
        graph.insertEdgeWeight(v1, v3, 5);
        graph.insertEdgeWeight(v2, v4, 2);

        // Verificar si el grafo es conexo
        System.out.println("\nProbando si el grafo está conectado:");
        graph.isConexo();  // Debería devolver "El grafo está conectado"

        // Obtener el grado de un vértice
        System.out.println("\nProbando el grado de los vértices:");
        System.out.println("Grado del vértice A: " + graph.getDegree(v1));  // Debería ser 2 (A-B, A-C)
        System.out.println("Grado del vértice B: " + graph.getDegree(v2));  // Debería ser 2 (A-B, B-D)
        System.out.println("Grado del vértice C: " + graph.getDegree(v3));  // Debería ser 1 (A-C)
        System.out.println("Grado del vértice D: " + graph.getDegree(v4));  // Debería ser 1 (B-D)

        // Eliminar un vértice y volver a comprobar la conectividad
        graph.removeVertex(v3);
        System.out.println("\nDespués de eliminar el vértice C:");
        graph.isConexo();  // Ahora debería devolver "El grafo no está conectado" porque C ya no está
    }
}
