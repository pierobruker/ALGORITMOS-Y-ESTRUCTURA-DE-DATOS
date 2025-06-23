package estructuras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbolBPlus<T extends Comparable<T>> {

    // Nodo raíz del árbol B+
    private NodoB<T> raiz;

    // Orden del árbol (mínimo número de claves que puede manejar cada nodo)
    private final int orden;

    // Cantidad total de claves almacenadas en el árbol
    private int tamano;

    // Constructor del árbol B+, valida que el orden mínimo sea al menos 3
    public ArbolBPlus(int orden) {
        if (orden < 3) {
            throw new IllegalArgumentException("El orden debe ser al menos 3");
        }
        this.orden = orden;
        this.raiz = new NodoB<>(orden, true);  // Se inicializa con un nodo hoja vacío
        this.tamano = 0;
    }

    // Método getter para acceder a la raíz
    public NodoB<T> getRaiz() {
        return raiz;
    }

    // Método principal para insertar una clave en el árbol
    public boolean insertar(T clave) {
        // Primero se verifica si la clave ya existe (no permite duplicados)
        if (buscar(clave) != null) {
            return false;  // Si existe, no se inserta
        }
        
        NodoB<T> nodo = raiz;

        // Se busca la hoja donde debe insertarse la nueva clave
        while (!nodo.esHoja) {
            int i = 0;
            while (i < nodo.claves.size() && clave.compareTo(nodo.claves.get(i)) > 0) {
                i++;
            }
            nodo = nodo.hijos.get(i);
        }
        
        // Se realiza la inserción en la hoja
        insertarEnHoja(nodo, clave);
        tamano++;
        return true;
    }

    // Inserta la clave en una hoja específica
    private void insertarEnHoja(NodoB<T> nodo, T clave) {
        // Se busca la posición donde insertar la clave en orden usando búsqueda binaria
        int pos = Collections.binarySearch(nodo.claves, clave);
        if (pos < 0) pos = -pos - 1;
        nodo.claves.add(pos, clave);
        
        // Si la hoja excede su capacidad, se divide
        if (nodo.claves.size() > 2 * orden) {
            dividirNodo(nodo);
        }
    }

    // Método para dividir un nodo que ha excedido su capacidad máxima
    private void dividirNodo(NodoB<T> nodo) {
        int medio = nodo.claves.size() / 2;
        T claveMedio = nodo.claves.get(medio);
        
        // Se crea un nuevo nodo que recibirá la mitad superior de las claves
        NodoB<T> nuevoNodo = new NodoB<>(orden, nodo.esHoja);
        nuevoNodo.claves = new ArrayList<>(nodo.claves.subList(medio, nodo.claves.size()));
        nodo.claves = new ArrayList<>(nodo.claves.subList(0, medio));
        
        // Si el nodo es interno (no hoja), también se dividen los hijos
        if (!nodo.esHoja) {
            nuevoNodo.hijos = new ArrayList<>(nodo.hijos.subList(medio, nodo.hijos.size()));
            nodo.hijos = new ArrayList<>(nodo.hijos.subList(0, medio + 1));
            
            // Se actualiza el padre de los hijos movidos al nuevo nodo
            for (NodoB<T> hijo : nuevoNodo.hijos) {
                hijo.padre = nuevoNodo;
            }
        } else {
            // Si es hoja, se mantiene la lista enlazada entre las hojas
            nuevoNodo.siguiente = nodo.siguiente;
            if (nodo.siguiente != null) {
                nodo.siguiente.anterior = nuevoNodo;
            }
            nodo.siguiente = nuevoNodo;
            nuevoNodo.anterior = nodo;
        }
        
        // Si el nodo dividido era la raíz, se crea una nueva raíz
        if (nodo == raiz) {
            NodoB<T> nuevaRaiz = new NodoB<>(orden, false);
            nuevaRaiz.claves.add(claveMedio);
            nuevaRaiz.hijos.add(nodo);
            nuevaRaiz.hijos.add(nuevoNodo);
            
            nodo.padre = nuevaRaiz;
            nuevoNodo.padre = nuevaRaiz;
            raiz = nuevaRaiz;
        } else {
            // Si tiene padre, se propaga la división hacia arriba
            NodoB<T> padre = nodo.padre;
            insertarEnPadre(padre, claveMedio, nuevoNodo);
            nuevoNodo.padre = padre;
            
            // Si el padre también excede su capacidad, se sigue dividiendo recursivamente
            if (padre.claves.size() > 2 * orden) {
                dividirNodo(padre);
            }
        }
    }

    // Inserta una clave y un nuevo hijo en el nodo padre tras la división
    private void insertarEnPadre(NodoB<T> padre, T clave, NodoB<T> hijo) {
        int pos = 0;
        while (pos < padre.claves.size() && clave.compareTo(padre.claves.get(pos)) > 0) {
            pos++;
        }
        padre.claves.add(pos, clave);
        padre.hijos.add(pos + 1, hijo);
    }

    // Método para buscar una clave dentro del árbol B+
    public T buscar(T clave) {
        NodoB<T> nodo = raiz;
        while (nodo != null) {
            int i = 0;
            while (i < nodo.claves.size() && clave.compareTo(nodo.claves.get(i)) > 0) {
                i++;
            }
            
            if (i < nodo.claves.size() && clave.equals(nodo.claves.get(i))) {
                return nodo.claves.get(i);
            }
            
            if (nodo.esHoja) {
                return null;  // Si llegó a la hoja y no lo encontró, la clave no existe
            }
            
            nodo = nodo.hijos.get(i);
        }
        return null;
    }

    // Método de eliminación simplificada
    public boolean eliminar(T clave) {
        // Se comprueba primero si la clave existe
        T encontrado = buscar(clave);
        if (encontrado == null) return false;
        
        NodoB<T> nodo = raiz;
        // Se navega hasta la hoja donde se encuentra la clave
        while (!nodo.esHoja) {
            int i = 0;
            while (i < nodo.claves.size() && clave.compareTo(nodo.claves.get(i)) > 0) {
                i++;
            }
            nodo = nodo.hijos.get(i);
        }
        
        int pos = nodo.claves.indexOf(clave);
        if (pos >= 0) {
            nodo.claves.remove(pos);
            tamano--;
            // Nota: aquí podría agregarse lógica de balanceo tras eliminar (no implementada)
            return true;
        }
        return false;
    }

    // Devuelve todas las claves del árbol en orden ascendente
    public List<T> listar() {
        List<T> resultado = new ArrayList<>();
        NodoB<T> actual = raiz;

        // Se baja al primer nodo hoja (más a la izquierda)
        while (!actual.esHoja) {
            actual = actual.hijos.get(0);
        }
        
        // Se recorren todas las hojas de izquierda a derecha usando la lista enlazada de hojas
        while (actual != null) {
            resultado.addAll(actual.claves);
            actual = actual.siguiente;
        }
        return resultado;
    }

    // Devuelve el número total de claves almacenadas
    public int tamano() {
        return tamano;
    }

    // Verifica si el árbol está vacío
    public boolean estaVacio() {
        return tamano == 0;
    }
}