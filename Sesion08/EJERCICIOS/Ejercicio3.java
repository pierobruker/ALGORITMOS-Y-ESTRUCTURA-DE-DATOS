package EJERCICIOS;

import actividad2.AVLTree;

public class Ejercicio3 {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        // Insertamos algunos valores para formar un árbol equilibrado
        int[] valores = {50, 30, 70, 20, 40, 60, 80};

        for (int v : valores) {
            avl.insert(v);
        }

        System.out.println("Recorrido por niveles (BFS) del árbol AVL:");

        avl.breadthFirstTraversal();
    }
}
