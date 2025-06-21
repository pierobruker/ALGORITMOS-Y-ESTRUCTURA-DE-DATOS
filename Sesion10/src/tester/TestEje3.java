package tester;
import btree.BNode;
import btree.BTree;
import btree.ItemNoFound;

public class TestEje3 {
    public static void main(String[] args) {
        try {
            // Intenta construir el árbol a partir del archivo arbolB.txt
            BTree<Integer> tree = BTree.building_Btree("src/arbolB.txt");
            
            // Muestra el árbol
            System.out.println("Árbol B construido correctamente:");
            System.out.println(tree);

            // Verifica nuevamente que el árbol es válido
            if (tree.validateBTree()) {
                System.out.println("El árbol cumple las propiedades del B-Tree.");
            } else {
                System.out.println("El árbol NO cumple las propiedades del B-Tree.");
            }

        } catch (ItemNoFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
