package graph;

import listlinked.ListLinked;
import listlinked.Node;
import java.util.*;

public class GraphLink {
    private ListLinked<Vertex> vertices;
    private ListLinked<Edge> edges;

    public GraphLink() {
        this.vertices = new ListLinked<>();
        this.edges = new ListLinked<>();
    }

    public void insertVertex(Vertex v) {
        if (!vertices.contains(v)) {
            vertices.insert(v);
        }
    }

    // Inserción de una arista entre dos vértices con peso
    public void insertEdge(Vertex v1, Vertex v2, int weight) {
        if (!edgeExists(v1, v2)) {
            edges.insert(new Edge(v1, v2, weight));
        }
    }

    public boolean edgeExists(Vertex v1, Vertex v2) {
        Node<Edge> current = edges.head;
        while (current != null) {
            if ((current.getData().getV1().equals(v1) && current.getData().getV2().equals(v2)) || 
                (current.getData().getV1().equals(v2) && current.getData().getV2().equals(v1))) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void removeVertex(Vertex v) {
        Node<Edge> current = edges.head;
        while (current != null) {
            if (current.getData().getV1().equals(v) || current.getData().getV2().equals(v)) {
                edges.remove(current.getData());
            }
            current = current.getNext();
        }
        vertices.remove(v);
    }

    // Eliminar una arista entre dos vértices
    public void removeEdge(Vertex v1, Vertex v2) {
        Node<Edge> current = edges.head;
        while (current != null) {
            if (current.getData().getV1().equals(v1) && current.getData().getV2().equals(v2) || 
                current.getData().getV1().equals(v2) && current.getData().getV2().equals(v1)) {
                edges.remove(current.getData());
            }
            current = current.getNext();
        }
    }

    public boolean searchVertex(Vertex v) {
        Node<Vertex> current = vertices.head;
        while (current != null) {
            if (current.getData().equals(v)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean searchEdge(Vertex v, Vertex z) {
        Node<Edge> current = edges.head;
        while (current != null) {
            if ((current.getData().getV1().equals(v) && current.getData().getV2().equals(z)) || 
                (current.getData().getV1().equals(z) && current.getData().getV2().equals(v))) {
                return true;  // La arista existe
            }
            current = current.getNext();
        }
        return false;  // La arista no existe
    }

    // Recorrido en profundidad (DFS)
    public void dfs(Vertex start) {
        Set<Vertex> visited = new HashSet<>();
        System.out.println("DFS starting from vertex: " + start.getId());
        dfsHelper(start, visited);
    }

    private void dfsHelper(Vertex vertex, Set<Vertex> visited) {
        if (visited.contains(vertex)) {
            return;
        }
        visited.add(vertex);
        System.out.print(vertex.getId() + " ");

        Node<Edge> currentEdge = edges.head;
        while (currentEdge != null) {
            if (currentEdge.getData().getV1().equals(vertex)) {
                Vertex adjacentVertex = currentEdge.getData().getV2();
                if (!visited.contains(adjacentVertex)) {
                    dfsHelper(adjacentVertex, visited);
                }
            } else if (currentEdge.getData().getV2().equals(vertex)) {
                Vertex adjacentVertex = currentEdge.getData().getV1();
                if (!visited.contains(adjacentVertex)) {
                    dfsHelper(adjacentVertex, visited);
                }
            }
            currentEdge = currentEdge.getNext();
        }
    }

    // Recorrido en anchura (BFS)
    public void bfs(Vertex start) {
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        
        visited.add(start);
        queue.add(start);

        System.out.println("BFS starting from vertex: " + start.getId());

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            System.out.print(vertex.getId() + " ");

            // Recorrer todos los vértices adyacentes
            Node<Edge> currentEdge = edges.head;
            while (currentEdge != null) {
                if (currentEdge.getData().getV1().equals(vertex)) {
                    Vertex adjacentVertex = currentEdge.getData().getV2();
                    if (!visited.contains(adjacentVertex)) {
                        visited.add(adjacentVertex);
                        queue.add(adjacentVertex);
                    }
                } else if (currentEdge.getData().getV2().equals(vertex)) {
                    Vertex adjacentVertex = currentEdge.getData().getV1();
                    if (!visited.contains(adjacentVertex)) {
                        visited.add(adjacentVertex);
                        queue.add(adjacentVertex);
                    }
                }
                currentEdge = currentEdge.getNext();
            }
        }
    }

    private int getVertexCount() {
        int count = 0;
        Node<Vertex> current = vertices.head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }


    
    //EJERCICIOS
    public void insertEdgeWeight(Vertex v1, Vertex v2, int weight) {
        if (!edgeExists(v1, v2)) {
            edges.insert(new Edge(v1, v2, weight));
            System.out.println("Se ha insertado la arista entre " + v1.getId() + " y " + v2.getId() + " con peso " + weight);
        } else {
            System.out.println("La arista entre " + v1.getId() + " y " + v2.getId() + " ya existe.");
        }
    }

    
    public List<Vertex> bfsPath(Vertex start, Vertex end) {
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Vertex> prev = new HashMap<>();
        
        visited.add(start);
        queue.add(start);
        
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current.equals(end)) break;

            Node<Edge> currentEdge = edges.head;
            while (currentEdge != null) {
                if (currentEdge.getData().getV1().equals(current)) {
                    Vertex adjacent = currentEdge.getData().getV2();
                    if (!visited.contains(adjacent)) {
                        visited.add(adjacent);
                        prev.put(adjacent, current);
                        queue.add(adjacent);
                    }
                }
                currentEdge = currentEdge.getNext();
            }
        }

        // Reconstruir el camino
        List<Vertex> path = new LinkedList<>();
        Vertex curr = end;
        while (curr != null) {
            path.add(curr);
            curr = prev.get(curr);
        }
        Collections.reverse(path);
        
        // Mostrar el camino encontrado
        System.out.print("Camino encontrado entre " + start.getId() + " y " + end.getId() + ": ");
        for (Vertex v : path) {
            System.out.print(v.getId() + " ");
        }
        System.out.println(); 
        return path;
    }

    public List<Vertex> shortestPath(Vertex start, Vertex end) {
        // Algoritmo de Dijkstra
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Node<Vertex> current = vertices.head;
        while (current != null) {
            distances.put(current.getData(), Integer.MAX_VALUE);
            previous.put(current.getData(), null);
            current = current.getNext();
        }

        distances.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex currentVertex = pq.poll();
            if (visited.contains(currentVertex)) continue;
            visited.add(currentVertex);

            Node<Edge> edgeNode = edges.head;
            while (edgeNode != null) {
                Edge edge = edgeNode.getData();
                if (edge.getV1().equals(currentVertex)) {
                    Vertex neighbor = edge.getV2();
                    int newDist = distances.get(currentVertex) + edge.getWeight();
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        previous.put(neighbor, currentVertex);
                        pq.add(neighbor);
                    }
                }
                edgeNode = edgeNode.getNext();
            }
        }

        List<Vertex> path = new LinkedList<>();
        for (Vertex at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.print("Camino más corto entre " + start.getId() + " y " + end.getId() + ": ");
        for (Vertex v : path) {
            System.out.print(v.getId() + " ");
        }
        System.out.println();  
        return path;
    }
    //Ejercicio 2
    public boolean isConexo() {
        if (vertices.head == null) return true;

        Set<Vertex> visited = new HashSet<>();
        dfsHelper(vertices.head.getData(), visited);

        if (visited.size() == getVertexCount()) {
            System.out.println("El grafo está conectado.");
            return true;
        } else {
            System.out.println("El grafo no está conectado.");
            return false;
        }
    }

    
    public int getDegree(Vertex v) {
        int degree = 0;
        Node<Edge> current = edges.head;
        while (current != null) {
            if (current.getData().getV1().equals(v) || current.getData().getV2().equals(v)) {
                degree++;
            }
            current = current.getNext();
        }
        return degree;
    }

    //Ejercicio 5
    public List<Vertex> getVertices() {
        List<Vertex> vertexList = new ArrayList<>();
        Node<Vertex> current = vertices.head;
        while (current != null) {
            vertexList.add(current.getData());
            current = current.getNext();
        }
        return vertexList;
    }
    
    public boolean isPath() {
        int numVertices = getVertexCount();
        int numEdges = edges.size();

        return numEdges == numVertices - 1;
    }

    public boolean isCycle() {
        int numVertices = getVertexCount();
        int numEdges = edges.size();
        if (numEdges != numVertices) {
            return false;
        }
        for (Vertex v : getVertices()) {
            if (getDegree(v) != 2) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isWheel() {
        int numVertices = getVertexCount();
        Vertex centralVertex = null;
        for (Vertex v : getVertices()) {
            if (getDegree(v) == numVertices - 1) {
                centralVertex = v;
                break;
            }
        }  if (centralVertex == null) {
            return false;
        }
        for (Vertex v : getVertices()) {
            if (!v.equals(centralVertex) && getDegree(v) != 2) {
                return false;
            }
        }
        return true;
    }

	public boolean isComplete() {
        int numVertices = getVertexCount();
        int numEdges = edges.size();
        int expectedEdges = numVertices * (numVertices - 1) / 2;
        return numEdges == expectedEdges;
    }

	//Ejercicio 6
	public void printFormal() {
	    System.out.println("Representacion Formal:");
	    Node<Vertex> vertexNode = vertices.head;
	    while (vertexNode != null) {
	        Vertex vertex = vertexNode.getData();
	        System.out.print(vertex.getId() + ": ");
	        Node<Edge> edgeNode = edges.head;
	        while (edgeNode != null) {
	            Edge edge = edgeNode.getData();
	            if (edge.getV1().equals(vertex)) {
	                System.out.print(edge.getV2().getId() + " ");
	            } else if (edge.getV2().equals(vertex)) {
	                System.out.print(edge.getV1().getId() + " ");
	            }
	            edgeNode = edgeNode.getNext();
	        }
	        System.out.println();
	        vertexNode = vertexNode.getNext();
	    }
	}
	public void printAdjacencyList() {
	    System.out.println("\nLista de Adyacencia:");
	    Node<Vertex> vertexNode = vertices.head;
	    while (vertexNode != null) {
	        Vertex vertex = vertexNode.getData();
	        System.out.print(vertex.getId() + " --> ");
	        Node<Edge> edgeNode = edges.head;
	        while (edgeNode != null) {
	            Edge edge = edgeNode.getData();
	            if (edge.getV1().equals(vertex)) {
	                System.out.print(edge.getV2().getId() + " ");
	            } else if (edge.getV2().equals(vertex)) {
	                System.out.print(edge.getV1().getId() + " ");
	            }
	            edgeNode = edgeNode.getNext();
	        }
	        System.out.println();
	        vertexNode = vertexNode.getNext();
	    }
	}
	public void printAdjacencyMatrix() {
	    System.out.println("\nMatriz de Adyacencia:");
	    int size = getVertexCount();
	    int[][] matrix = new int[size][size];
	    Node<Edge> edgeNode = edges.head;
	    while (edgeNode != null) {
	        Edge edge = edgeNode.getData();
	        int startIdx = getVertexIndex(edge.getV1());
	        int endIdx = getVertexIndex(edge.getV2());
	        
	        matrix[startIdx][endIdx] = 1;
	        matrix[endIdx][startIdx] = 1; 
	        edgeNode = edgeNode.getNext();
	    }

	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            System.out.print(matrix[i][j] + " ");
	        }
	        System.out.println();
	    }
	}
	public int getVertexIndex(Vertex v) {
	    int index = 0;
	    Node<Vertex> node = vertices.head;
	    while (node != null) {
	        if (node.getData().equals(v)) {
	            return index;
	        }
	        node = node.getNext();
	        index++;
	    }
	    return -1;
	}


	//EJERCICIO 09
	public boolean isIsomorphic(GraphListEdge<V, E> otherGraph) {
	    if (this.vertices.size() != otherGraph.vertices.size()) {
	        return false;
	    }

	    for (Vertex vertex : this.vertices) {
	        boolean matchFound = false;
	        for (Vertex otherVertex : otherGraph.vertices) {
	            if (compareEdges(vertex, otherVertex)) {
	                matchFound = true;
	                break;
	            }
	        }
	        if (!matchFound) {
	            return false;
	        }
	    }
	    return true;
	}

	private boolean compareEdges(Vertex vertex1, Vertex vertex2) {
	    // Compara el número de aristas de ambos vértices
	    if (vertex1.getEdges().size() != vertex2.getEdges().size()) {
	        return false;
	    }

	    // Compara las aristas
	    for (Edge edge1 : vertex1.getEdges()) {
	        boolean edgeMatch = false;
	        for (Edge edge2 : vertex2.getEdges()) {
	            if (edge1.equals(edge2)) {
	                edgeMatch = true;
	                break;
	            }
	        }
	        if (!edgeMatch) {
	            return false;
	        }
	    }
	    return true;
	}
	public boolean isPlanar() {
	    for (Edge edge : edges) {
	        if (hasCrossingEdge(edge)) {
	            return false;
	        }
	    }
	    return true;
	}

	private boolean hasCrossingEdge(Edge edge) {
	    // Este es un método básico que siempre devuelve falso,
	    // pero normalmente aquí iría un algoritmo de detección de cruces en el grafo.
	    return false;  // Este es solo un esbozo
	}

	
	public boolean isConnected() {
	    // Comienza con el primer vértice
	    Vertex startVertex = vertices.head.getData();
	    dfs(startVertex);

	    for (Vertex vertex : vertices) {
	        if (!vertex.isVisited()) {
	            return false;  // Si algún vértice no ha sido visitado, el grafo no es conexo
	        }
	    }
	    return true;
	}

	private void dfs(Vertex vertex) {
	    vertex.setVisited(true);  // Marcar el vértice como visitado

	    // Recorrer los vecinos del vértice
	    for (Edge edge : vertex.getEdges()) {
	        Vertex adjacent = (edge.getV1().equals(vertex)) ? edge.getV2() : edge.getV1();
	        if (!adjacent.isVisited()) {
	            dfs(adjacent);
	        }
	    }
	}

	
	public boolean isAutocomplementary() {
	    GraphListEdge<V, E> complementGraph = generateComplementGraph();

	    return this.isIsomorphic(complementGraph);
	}

	private GraphListEdge<V, E> generateComplementGraph() {
	    GraphListEdge<V, E> complementGraph = new GraphListEdge<>();

	    // Crear los vértices en el complemento
	    for (Vertex vertex : vertices) {
	        complementGraph.insertVertex(new Vertex(vertex.getInfo()));
	    }

	    // Añadir las aristas en el complemento
	    for (Vertex vertex1 : vertices) {
	        for (Vertex vertex2 : vertices) {
	            if (!vertex1.equals(vertex2) && !searchEdge(vertex1, vertex2)) {
	                complementGraph.insertEdge(vertex1, vertex2, null);  // Usamos null como placeholder para el peso
	            }
	        }
	    }
	    return complementGraph;
	}

	    

}
