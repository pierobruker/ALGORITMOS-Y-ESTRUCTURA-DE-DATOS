package actividad1;

import Exeptions.ExceptionIsEmpty;
import Exeptions.ItemDuplicated;

public class copiaetst {
	public static void main(String[] args) {
        LinkedBST<Integer> tree1 = new LinkedBST<>();
        LinkedBST<Integer> tree2 = new LinkedBST<>();

        // --- Ejercicio 1a: Método destroyNodes() ---
        try {
            tree1.insert(8);
            tree1.insert(3);
            tree1.insert(1);
            tree1.insert(6);
            tree1.insert(10);
            tree1.insert(14);
            tree1.insert(4);
            System.out.println("Antes de destruir los nodos:");
            tree1.showInOrder();

            tree1.destroyNodes(); 
            System.out.println("Después de destruir los nodos:");
            tree1.showInOrder(); 
        } catch (ExceptionIsEmpty | ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // --- Ejercicio 1b: Método countAllNodes() ---
        try {
        	tree1.insert(8);
            tree1.insert(3);
            tree1.insert(1);
            tree1.insert(6);
            tree1.insert(10);
            tree1.insert(14);
            tree1.insert(4);
            System.out.println("Total de nodos no hoja (countAllNodes): " + tree1.countAllNodes()); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- Ejercicio 1c: Método countNodes() ---
        try {
            System.out.println("Total de nodos (countNodes): " + tree1.countNodes()); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- Ejercicio 1d: Método height(x) ---
        try {
            System.out.println("Altura del subárbol con raíz 3: " + tree1.height(3)); 
            System.out.println("Altura del subárbol con raíz 10: " + tree1.height(10)); 
        } catch (Exception e) {
            System.out.println("Error de búsqueda: " + e.getMessage());
        }

        // --- Ejercicio 1e: Método amplitude(nivel) ---
        try {
        	
            System.out.println("Amplitud del nivel 1: " + tree1.amplitude(1)); 
            System.out.println("Amplitud del nivel 2: " + tree1.amplitude(2)); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- Ejercicio 2: Comparar áreas de dos árboles ---
        try {
            tree2.insert(8);
            tree2.insert(3);
            tree2.insert(1);
            tree2.insert(7);
            tree2.insert(10);
            tree2.insert(14);
            tree2.insert(4);
            System.out.println("¿Tienen el mismo área los árboles? " + tree1.sameArea(tree2));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- Ejercicio 3: Representación parentética ---

        System.out.println("Representación parentética del árbol 2: ");
        System.out.println(tree2.parenthesize());
    }
	
}
