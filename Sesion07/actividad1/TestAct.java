package actividad1;

import Exeptions.ItemDuplicated;
import Exeptions.ItemNoFound;

public class TestAct {
    public static void main(String[] args) {
        LinkedBST<Integer> tree1 = new LinkedBST<>();

        try {
        	// Insertar nodos en tree1
            tree1.insert(8);
            tree1.insert(3);
            tree1.insert(1);
            tree1.insert(6);
            tree1.insert(10);
            tree1.insert(14);
            tree1.insert(4);

                        
            tree1.showInOrder();
            tree1.showPreOrder();
            tree1.showPostOrder();
            
            System.out.println("El nodo maximo es: "+tree1.findMax());
            System.out.println("El nodo minimo es: "+ tree1.findMin());
            
            System.out.println("Buscar 6: " + tree1.search(6));
            System.out.println("Buscar 13: " + tree1.search(13));
            
            
            
        } catch (ItemDuplicated | ItemNoFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
