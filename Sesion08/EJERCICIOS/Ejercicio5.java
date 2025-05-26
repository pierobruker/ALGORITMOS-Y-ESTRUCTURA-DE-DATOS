package EJERCICIOS;

import actividad2.AVLTree;

public class Ejercicio5 {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        int[] valores = {50, 30, 70, 20, 40, 60, 80};

        for (int v : valores) {
            avl.insert(v);
        }

        System.out.println("Recorrido en preorden del árbol AVL:");
        avl.preorderTraversal();
    }
}