package visualizador;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import estructuras.Grafo;
import estructuras.ArbolBPlus;
import estructuras.NodoB;
import modelo.Ubicacion;
import modelo.Ruta;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visualizador {

    // Método para visualizar el Grafo del almacén usando JGraphX
    public static void mostrarGrafo(Grafo grafo) {
        // Se crea la ventana principal de visualización
        JFrame frame = new JFrame("Visualización del Grafo del Almacén");

        // Se crea una instancia del grafo de la librería JGraphX
        mxGraph graph = new mxGraph();

        // El objeto 'parent' representa el nodo raíz del grafo en JGraphX
        Object parent = graph.getDefaultParent();

        // Mapa auxiliar para relacionar las ubicaciones del sistema con los vértices de JGraphX
        Map<Ubicacion, Object> vertices = new HashMap<>();

        // Se inicia una transacción para actualizar el grafo
        graph.getModel().beginUpdate();
        try {
            // Se insertan los nodos (ubicaciones) del grafo
            for (Ubicacion nodo : grafo.getListaAdyacencia().keySet()) {
                // Cada nodo se dibuja en una posición aleatoria dentro de la ventana
                Object v = graph.insertVertex(parent, null, nodo.getId(), 100 + Math.random() * 400, 100 + Math.random() * 400, 80, 30);
                vertices.put(nodo, v);  // Se guarda la referencia al vértice creado
            }

            // Se insertan las aristas (rutas) entre las ubicaciones
            for (Ubicacion origen : grafo.getListaAdyacencia().keySet()) {
                List<Ruta> rutas = grafo.getListaAdyacencia().get(origen);
                for (Ruta ruta : rutas) {
                    Ubicacion destino = ruta.getDestino();
                    // Se dibuja la arista desde el origen al destino, mostrando el peso (distancia)
                    graph.insertEdge(parent, null, ruta.getPeso(), vertices.get(origen), vertices.get(destino));
                }
            }
        } finally {
            // Se finaliza la transacción de actualización del modelo
            graph.getModel().endUpdate();
        }

        // Se coloca el componente gráfico del grafo en el JFrame
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        frame.getContentPane().add(graphComponent);

        // Se configura el tamaño de la ventana
        frame.setSize(800, 600);
        frame.setVisible(true);  // Se muestra la ventana al usuario
    }

    // Método para visualizar el Árbol B+ usando JGraphX
    public static void mostrarArbolBPlus(ArbolBPlus<?> arbol) {
        // Se crea la ventana principal para visualizar el árbol
        JFrame frame = new JFrame("Visualización del Árbol B+");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            // Solo si el árbol no está vacío, se empieza el dibujo
            if (arbol != null && arbol.getRaiz() != null) {
                // Mapa auxiliar para rastrear los nodos visualizados
                Map<NodoB<?>, Object> nodosVisibles = new HashMap<>();
                // Se llama recursivamente al método para dibujar los nodos empezando por la raíz
                dibujarNodo(graph, parent, arbol.getRaiz(), 400, 50, nodosVisibles);
            }
        } finally {
            graph.getModel().endUpdate();
        }

        // Se coloca el componente gráfico dentro de la ventana JFrame
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        frame.getContentPane().add(graphComponent);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    // Método auxiliar recursivo para dibujar cada nodo del Árbol B+ 
    private static void dibujarNodo(mxGraph graph, Object parent, NodoB<?> nodo, int x, int y, Map<NodoB<?>, Object> nodosVisibles) {
        // Se convierte la lista de claves del nodo en texto para la etiqueta visual
        String label = nodo.getClaves().toString();

        // Se inserta el nodo como un vértice en el grafo gráfico
        Object v = graph.insertVertex(parent, null, label, x, y, 80, 30);
        nodosVisibles.put(nodo, v);  // Se guarda la referencia para poder dibujar las conexiones

        // Se calcula el desplazamiento horizontal para distribuir los hijos de forma ordenada
        int offsetX = - (nodo.getHijos().size() - 1) * 100 / 2;

        // Se recorren los hijos del nodo actual para dibujarlos recursivamente
        for (int i = 0; i < nodo.getHijos().size(); i++) {
            NodoB<?> hijo = nodo.getHijos().get(i);
            int childX = x + offsetX + i * 100;  // Calcula posición horizontal del hijo
            int childY = y + 100;                // Calcula posición vertical del hijo (más abajo)
            dibujarNodo(graph, parent, hijo, childX, childY, nodosVisibles);
            // Se inserta la arista que conecta el nodo padre con su hijo
            graph.insertEdge(parent, null, "", v, nodosVisibles.get(hijo));
        }
    }
}

