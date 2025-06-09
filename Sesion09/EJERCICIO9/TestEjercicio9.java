package EJERCICIO9;

public class TestEjercicio9 {

    public static void main(String[] args) {
        // Crear el grafo
        GraphListEdge<String, Integer> graph = new GraphListEdge<>();

        // Crear vértices
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");

        // Insertar vértices y aristas
        graph.insertVertex(v1);
        graph.insertVertex(v2);
        graph.insertVertex(v3);
        graph.insertEdge(v1, v2, 10);
        graph.insertEdge(v2, v3, 5);

        // Verificar los métodos
        System.out.println("¿Es isomorfo? " + graph.isIsomorphic(graph));  // Debería devolver true
        System.out.println("¿Está conectado? " + graph.isConnected());    // Debería devolver true
        System.out.println("¿Es auto-complementario? " + graph.isAutocomplementary()); // Debería devolver true
    }
}
