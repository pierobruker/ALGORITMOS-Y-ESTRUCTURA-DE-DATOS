package controlador;

import modelo.Almacen; 
import estructuras.ArbolBPlus;
import modelo.Producto;
import util.EntradaUsuario;
import visualizador.Visualizador;

import java.util.*;

public class ControladorVisualizacion {

    // El controlador tiene acceso al objeto Almacen
    private final Almacen almacen;

    // Constructor que recibe la instancia de Almacen
    public ControladorVisualizacion(Almacen almacen) {
        this.almacen = almacen;
    }

    // Método para visualizar el grafo de rutas
    public void mostrarGrafo() {
        // Primero se valida que existan datos en el grafo
        if (almacen.getGrafo().getListaAdyacencia().isEmpty()) {
            System.out.println("El grafo está vacío.");
            return;
        }
        // Si existen datos, se llama al visualizador gráfico
        Visualizador.mostrarGrafo(almacen.getGrafo());
    }

    // Método para visualizar el árbol B+ de productos por categoría
    public void mostrarArbolBPlus() {
        // Se verifica que existan categorías registradas
        if (almacen.getCategorias().isEmpty()) {
            System.out.println("No existen categorías cargadas.");
            return;
        }

        // Se obtiene una lista con los nombres de todas las categorías disponibles
        List<String> listaCategorias = new ArrayList<>(almacen.getCategorias().keySet());
        System.out.println("\n=== Categorías disponibles ===");
        for (int i = 0; i < listaCategorias.size(); i++) {
            // Se muestra la lista de categorías para que el usuario pueda elegir
            System.out.println((i + 1) + ". " + listaCategorias.get(i));
        }

        // El usuario selecciona la categoría que desea visualizar
        int opcion = EntradaUsuario.leerEntero("Seleccione la categoría: ", 1, listaCategorias.size());
        String categoriaSeleccionada = listaCategorias.get(opcion - 1);

        // Se obtiene el árbol B+ correspondiente a la categoría seleccionada
        ArbolBPlus<Producto> arbol = almacen.getArbolDeCategoria(categoriaSeleccionada);
        // Se valida si el árbol contiene datos antes de visualizarlo
        if (arbol == null || arbol.getRaiz() == null || arbol.getRaiz().getClaves().isEmpty()) {
            System.out.println("El Árbol B+ de la categoría seleccionada está vacío.");
        } else {
            // Si hay datos, se envía el árbol al visualizador
            Visualizador.mostrarArbolBPlus(arbol);
        }
    }
}