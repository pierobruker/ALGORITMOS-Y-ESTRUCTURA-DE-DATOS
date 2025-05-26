package EJERCICIOS;

import actividad2.AVLTree;

public class Ejercicio6 {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        System.out.println("Insertando nodos:");
        int[] insertValues = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35};

        for (int v : insertValues) {
            System.out.println("\nAntes de insertar " + v + ":");
            avl.inorder();

            avl.insert(v);

            System.out.println("Después de insertar " + v + ":");
            avl.inorder();
        }

        System.out.println("\nEliminando nodos:");

        int[] deleteValues = {20, 30, 50};

        for (int v : deleteValues) {
            System.out.println("\nAntes de eliminar " + v + ":");
            avl.inorder();

            avl.delete(v);

            System.out.println("Después de eliminar " + v + ":");
            avl.inorder();
        }
    }
}
