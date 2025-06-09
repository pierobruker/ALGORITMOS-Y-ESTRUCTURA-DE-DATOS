package Testejercicios;
//NO SE PUDO AGREGAR LA LIBRERIA


import org.jgrapht.*;
import org.jgrapht.Graph;.*;

public class TestEjercicio4 {

    public static void main(String[] args) {
        // Crear un grafo no ponderado
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Agregar vértices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Agregar aristas (sin peso)
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");

        // Mostrar los vértices y aristas
        System.out.println("Vértices: " + graph.vertexSet());
        System.out.println("Aristas: " + graph.edgeSet());

        // Recorrido en profundidad (DFS) - usando una implementación básica de DFS
        System.out.println("\nRealizando DFS (profundidad) comenzando desde A:");
        depthFirstSearch(graph, "A");

        // Recorrido en anchura (BFS) - usando una implementación básica de BFS
        System.out.println("\nRealizando BFS (anchura) comenzando desde A:");
        breadthFirstSearch(graph, "A");
    }

    // Recorrido en profundidad (DFS)
    public static void depthFirstSearch(Graph<String, DefaultEdge> graph, String startVertex) {
        // Utilizando una pila para DFS
        java.util.Stack<String> stack = new java.util.Stack<>();
        java.util.Set<String> visited = new java.util.HashSet<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                // Agregar vecinos del vértice a la pila
                for (String neighbor : graph.neighborsOf(vertex)) {
                    stack.push(neighbor);
                }
            }
        }
        System.out.println();
    }

    // Recorrido en anchura (BFS)
    public static void breadthFirstSearch(Graph<String, DefaultEdge> graph, String startVertex) {
        // Utilizando una cola para BFS
        java.util.Queue<String> queue = new java.util.LinkedList<>();
        java.util.Set<String> visited = new java.util.HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            System.out.print(vertex + " ");
            // Agregar vecinos del vértice a la cola
            for (String neighbor : graph.neighborsOf(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
}
