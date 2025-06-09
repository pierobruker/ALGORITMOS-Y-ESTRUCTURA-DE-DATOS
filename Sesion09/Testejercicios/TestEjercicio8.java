package Testejercicios;

import graph.GraphLink;
import graph.Vertex;

public class TestEjercicio8 {

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

        // Imprimir la representación Formal
        graph.printFormal();  // Debería imprimir la representación formal de las aristas

        // Imprimir la Lista de Adyacencia
        graph.printAdjacencyList();  // Debería imprimir la lista de adyacencia

        // Imprimir la Matriz de Adyacencia
        graph.printAdjacencyMatrix();  // Debería imprimir la matriz de adyacencia
    }
}
