package actividad2;

public class TestAVL {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        System.out.println("Inicio pruebas de inserciones con múltiples rotaciones:");
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10};

        for (int i = 0; i < valores.length; i++) {
            System.out.println("\nInsertando: " + valores[i]);
            tree.insert(valores[i]);
            System.out.println("Árbol inorden con bf después de insertar " + valores[i] + ": ");
            tree.inorder();
        }

        System.out.println("\nPruebas finalizadas.");
    }
}

