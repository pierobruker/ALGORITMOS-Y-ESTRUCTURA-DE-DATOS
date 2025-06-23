package estructuras;

import java.util.ArrayList;
import java.util.List;

public class NodoB<T extends Comparable<T>> {

    // Lista de claves almacenadas en el nodo (valores ordenados)
    public List<T> claves;

    // Lista de hijos (solo se usa si el nodo NO es hoja)
    public List<NodoB<T>> hijos;

    // Referencia al padre de este nodo
    public NodoB<T> padre;

    // Indica si el nodo es una hoja o un nodo interno
    public boolean esHoja;

    // Punteros para lista enlazada horizontal entre nodos hoja (para recorrido secuencial eficiente)
    public NodoB<T> siguiente; 
    public NodoB<T> anterior;

    // El orden del árbol B+ (define el mínimo y máximo de claves permitidas)
    private final int orden;

    // Constructor del nodo, requiere conocer el orden y si es hoja o no
    public NodoB(int orden, boolean esHoja) {
        this.orden = orden;
        this.claves = new ArrayList<>(2 * orden);      // Capacidad máxima de claves
        this.hijos = new ArrayList<>(2 * orden + 1);   // Capacidad máxima de hijos (n+1)
        this.padre = null;
        this.esHoja = esHoja;
        this.siguiente = null;
        this.anterior = null;
    }

    // Verifica si el nodo ya está lleno (excede su capacidad máxima de claves)
    public boolean estaLleno() {
        return claves.size() >= 2 * orden;
    }

    // Verifica si el nodo cumple con el mínimo de claves permitidas
    public boolean tieneMinimo() {
        return claves.size() >= orden;
    }
    
    // Getter para acceder a las claves del nodo
    public List<T> getClaves() {
        return claves;
    }

    // Getter para acceder a los hijos del nodo
    public List<NodoB<T>> getHijos() {
        return hijos;
    }
}
