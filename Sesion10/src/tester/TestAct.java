package tester;


import btree.BTree;

public class TestAct {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(5);
        System.out.println("Test de insercion: ");
        int [] keysToInsert = {
            100, 50, 20, 70, 10, 30, 80, 90, 200, 25, 15, 5, 65, 35, 60, 18, 93,94
        };
        for ( int key:keysToInsert){
            bTree.insert(key);
            System.out.println("Arbol despues de insertar" + key+":");
            System.out.println(bTree);
        }

        //EJERCICIO1

        System.out.println("\n test de busqueda: ");
        System.out.println("Buscar 70: " );
        boolean found70 = bTree.search(70);
        
        //EJERCICIO2
        System.out.println("\n test de borrar: ");
        bTree.remove(50);
        System.out.println(bTree);

    }
}
