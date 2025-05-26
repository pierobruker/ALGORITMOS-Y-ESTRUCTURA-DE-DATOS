package EJERCICIOS;

import actividad2.AVLTree;

public class Ejercicio2 {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        int[] insertValues = {50, 30, 70, 20, 40, 60, 80};
        for (int v : insertValues) {
            avl.insert(v);
        }

        System.out.println("Árbol AVL después de inserciones:");
        avl.inorder();

        // Eliminamos nodos y mostramos el árbol después de cada eliminación
        int[] deleteValues = {20, 30, 50};

        for (int v : deleteValues) {
            System.out.println("\nEliminando: " + v);
            avl.delete(v);
            avl.inorder();
        }
    }
}
