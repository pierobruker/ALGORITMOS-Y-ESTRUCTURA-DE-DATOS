package Testejercicios;

import graph.GraphLink;
import graph.Vertex;

public class TestEjercicio7 {

    public static void main(String[] args) {
        // Crear el grafo
        GraphLink graph = new GraphLink();

        // Agregar vértices
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");

        graph.insertVertex(v1);
        graph.insertVertex(v2);
        graph.insertVertex(v3);
        graph.insertVertex(v4);

        // Agregar aristas
        graph.insertEdge(v1, v2, 10);
        graph.insertEdge(v2, v3, 5);
        graph.insertEdge(v3, v4, 7);

        // Verificar el grado de los vértices
        System.out.println("Grado de A: " + graph.getDegree(v1));
        System.out.println("Grado de B: " + graph.getDegree(v2));
        System.out.println("Grado de C: " + graph.getDegree(v3));
        System.out.println("Grado de D: " + graph.getDegree(v4));

        // Verificar el tipo de grafo
        System.out.println("¿Es un camino? " + graph.isPath());
        System.out.println("¿Es un ciclo? " + graph.isCycle());
        System.out.println("¿Es una rueda? " + graph.isWheel());
        System.out.println("¿Es un grafo completo? " + graph.isComplete());
    }
}
