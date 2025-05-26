package EJERCICIOS;

import actividad2.AVLTree;

public class Ejercicio4 {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};

        for (int v : valores) {
            avl.insert(v);
        }

        System.out.println("Recorrido por niveles (BFS) del árbol AVL:");

        avl.breadthFirstTraversal();
    }
}
