package graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphListEdge<V, E> {
    private ArrayList<VertexObj<V, E>> secVertex;  // Lista de vértices
    private ArrayList<EdgeObj<V, E>> secEdge;  // Lista de aristas

    // Constructor
    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // Método para insertar un vértice
    public void insertVertex(VertexObj<V, E> v) {
        if (!searchVertex(v)) {
            secVertex.add(v);
            System.out.println("Se ha insertado el vértice: " + v.getInfo());
        } else {
            System.out.println("El vértice " + v.getInfo() + " ya existe.");
        }
    }

    // Método para insertar una arista entre dos vértices
    public void insertEdge(VertexObj<V, E> v1, VertexObj<V, E> v2, E info) {
        if (!searchEdge(v1, v2)) {
            EdgeObj<V, E> edge = new EdgeObj<>(v1, v2, info, secEdge.size());
            secEdge.add(edge);
            System.out.println("Se ha insertado la arista entre " + v1.getInfo() + " y " + v2.getInfo() + " con información: " + info);
        } else {
            System.out.println("La arista entre " + v1.getInfo() + " y " + v2.getInfo() + " ya existe.");
        }
    }

    // Método para buscar un vértice
    public boolean searchVertex(VertexObj<V, E> v) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(v.getInfo())) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar una arista
    public boolean searchEdge(VertexObj<V, E> v1, VertexObj<V, E> v2) {
        for (EdgeObj<V, E> edge : secEdge) {
            if ((edge.getEndVertex1().getInfo().equals(v1.getInfo()) && edge.getEndVertex2().getInfo().equals(v2.getInfo())) ||
                (edge.getEndVertex1().getInfo().equals(v2.getInfo()) && edge.getEndVertex2().getInfo().equals(v1.getInfo()))) {
                return true;
            }
        }
        return false;
    }

    // Método para realizar el recorrido en anchura (BFS)
    public void bfs(VertexObj<V, E> start) {
        Queue<VertexObj<V, E>> queue = new LinkedList<>();
        ArrayList<Boolean> visited = new ArrayList<>(secVertex.size());

        for (int i = 0; i < secVertex.size(); i++) {
            visited.add(false);
        }

        visited.set(secVertex.indexOf(start), true);
        queue.add(start);
        System.out.println("Recorrido BFS a partir de " + start.getInfo() + ":");

        while (!queue.isEmpty()) {
            VertexObj<V, E> vertex = queue.poll();
            System.out.print(vertex.getInfo() + " ");

            for (EdgeObj<V, E> edge : secEdge) {
                if (edge.getEndVertex1().equals(vertex) && !visited.get(secVertex.indexOf(edge.getEndVertex2()))) {
                    queue.add(edge.getEndVertex2());
                    visited.set(secVertex.indexOf(edge.getEndVertex2()), true);
                }
                if (edge.getEndVertex2().equals(vertex) && !visited.get(secVertex.indexOf(edge.getEndVertex1()))) {
                    queue.add(edge.getEndVertex1());
                    visited.set(secVertex.indexOf(edge.getEndVertex1()), true);
                }
            }
        }
        System.out.println();
    }
    
}
