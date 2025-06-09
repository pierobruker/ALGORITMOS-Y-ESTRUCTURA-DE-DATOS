package graph;

import java.util.List;

public class TestGraph {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");

        GraphLink graph = new GraphLink();

        graph.insertVertex(v1);
        graph.insertVertex(v2);
        graph.insertVertex(v3);
        graph.insertVertex(v4);

        graph.insertEdge(v1, v2, 10);
        graph.insertEdge(v1, v3, 5);
        graph.insertEdge(v2, v4, 2);
        graph.insertEdge(v3, v4, 7);

        System.out.println("Probando búsqueda de vértices y aristas:");
        System.out.println("¿Existe el vértice A? " + graph.searchVertex(v1));  // True
        System.out.println("¿Existe la arista A-B? " + graph.searchEdge(v1, v2));  // True
        System.out.println("¿Existe la arista B-C? " + graph.searchEdge(v2, v3));  // False

        System.out.println("\nRealizando DFS desde el vértice A:");
        graph.dfs(v1);  // Debería visitar los vértices A -> B -> D -> C

        // Realizar recorrido BFS
        System.out.println("\nRealizando BFS desde el vértice A:");
        graph.bfs(v1);  // Debería visitar los vértices A -> B -> C -> D

        // Obtener el grado de un vértice
        System.out.println("\nGrado del vértice A: " + graph.getDegree(v1));  // Debería ser 2 (A-B, A-C)

        // Verificar si el grafo es conexo
        System.out.println("\n¿Está el grafo conectado? " + graph.isConexo());  // Debería devolver true

        // Eliminar un vértice y sus aristas
        graph.removeVertex(v3);
        System.out.println("\nDespués de eliminar el vértice C:");
        System.out.println("¿Existe el vértice C? " + graph.searchVertex(v3));  // Debería ser false
        System.out.println("¿Existe la arista A-C? " + graph.searchEdge(v1, v3));  // Debería ser false
        graph.dfs(v1);  // Ahora solo debería visitar A -> B -> D

        // Probar el método shortestPath
        System.out.println("\nCamino más corto de A a D:");
        List<Vertex> path = graph.shortestPath(v1, v4);  // Debería devolver el camino más corto de A a D
        for (Vertex v : path) {
            System.out.print(v.getId() + " ");  // Debería imprimir el camino: A -> B -> D
        }

        // Eliminar una arista y verificar
        graph.removeEdge(v1, v2);
        System.out.println("\nDespués de eliminar la arista A-B:");
        System.out.println("¿Existe la arista A-B? " + graph.searchEdge(v1, v2));  // Debería ser false
    }
}

