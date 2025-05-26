package EJERCICIOS;

import actividad2.AVLTree;
import actividad2.BSTree;

public class Ejercicio1 {

    public static void main(String[] args) {
        int[] ascendente = {10, 20, 30, 40, 50};
        int[] aleatorio = {30, 10, 50, 5, 20, 40, 60};

        // Prueba 1: Inserción ascendente
        System.out.println("Prueba 1: Inserción ascendente");

        BSTree<Integer> bst1 = new BSTree<>();
        AVLTree<Integer> avl1 = new AVLTree<>();

        for (int v : ascendente) {
            bst1.insert(v);
            avl1.insert(v);
        }

        System.out.println("BST inorder:");
        bst1.inorder();

        System.out.println("AVL inorder:");
        avl1.inorder();

        System.out.println("\nAltura BST: " + bst1.height());
        System.out.println("Altura AVL: " + avl1.height());

        // Prueba 2: Inserción aleatoria
        System.out.println("\nPrueba 2: Inserción aleatoria");

        BSTree<Integer> bst2 = new BSTree<>();
        AVLTree<Integer> avl2 = new AVLTree<>();

        for (int v : aleatorio) {
            bst2.insert(v);
            avl2.insert(v);
        }

        System.out.println("BST inorder:");
        bst2.inorder();

        System.out.println("AVL inorder:");
        avl2.inorder();

        System.out.println("\nAltura BST: " + bst2.height());
        System.out.println("Altura AVL: " + avl2.height());
    }
}
