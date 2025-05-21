package actividad1;
import Exeptions.ExceptionIsEmpty;
import Exeptions.ItemDuplicated;
import Exeptions.ItemNoFound;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    private Node<E> root;

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node<E> insertRec(Node<E> node, E data) throws ItemDuplicated {
        if (node == null) return new Node<>(data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = insertRec(node.left, data);
        else if (cmp > 0) node.right = insertRec(node.right, data);
        else throw new ItemDuplicated("Elemento duplicado: " + data);
        return node;
    }

    @Override
    public E search(E data) throws ItemNoFound {
        Node<E> node = searchRec(root, data);
        if (node == null) throw new ItemNoFound("Elemento no encontrado: " + data);
        return node.data;
    }

    private Node<E> searchRec(Node<E> node, E data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);
        if (cmp < 0) return searchRec(node.left, data);
        else if (cmp > 0) return searchRec(node.right, data);
        else return node;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Esta vacio... ");
        root = deleteRec(root, data);
    }

    private Node<E> deleteRec(Node<E> node, E data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = deleteRec(node.left, data);
        else if (cmp > 0) node.right = deleteRec(node.right, data);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<E> min = findMin(node.right);
            node.data = min.data;
            node.right = deleteRec(node.right, min.data);
        }
        return node;
    }

    private Node<E> findMin(Node<E> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public void showInOrder() {
        System.out.print("InOrder: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }
 // Recorrido PreOrden: Raíz, Izquierda, Derecha
    public void showPreOrder() {
        System.out.print("PreOrden: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // Recorrido PostOrden: Izquierda, Derecha, Raíz
    public void showPostOrder() {
        System.out.print("PostOrden: ");
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
    
    
    private Node<E> findMinNode(Node<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<E> findMaxNode(Node<E> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    public E findMin() throws ItemNoFound {
    	if (isEmpty()) throw new ItemNoFound("El nodo no se encontro minimo: ");
    	return findMinNode(root).data;
    }
    public E findMax() throws ItemNoFound {
        if (isEmpty()) throw new ItemNoFound("El árbol está vacío, no se puede encontrar maximo.");
        return findMaxNode(root).data;
    }
    
    
    //EJERCICIOS 1
 // a.
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol ya está vacío.");
        root = null;
    }

    // b.
    public int countAllNodes() {
        return countNonLeafNodes(root);
    }

    private int countNonLeafNodes(Node<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 0;
        return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
    }

    // c.
    public int countNodes() {
        return countAll(root);
    }

    private int countAll(Node<E> node) {
        if (node == null) return 0;
        return 1 + countAll(node.left) + countAll(node.right);
    }

    // d.
    public int height(E x) {
        Node<E> subRoot = searchNode(root, x);
        if (subRoot == null) return -1;
        return heightIterative(subRoot);
    }

    private Node<E> searchNode(Node<E> node, E x) {
        if (node == null) return null;
        int cmp = x.compareTo(node.data);
        if (cmp == 0) return node;
        else if (cmp < 0) return searchNode(node.left, x);
        else return searchNode(node.right, x);
    }

    private int heightIterative(Node<E> node) {
        if (node == null) return 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(node);
        int height = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node<E> current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            height++;
        }
        return height - 1;
    }

    // e.
    public int amplitude(int nivel) {
        if (isEmpty() || nivel < 0 || nivel > heightIterative(root) ) return 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        int currentLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (currentLevel == nivel) return size;
            for (int i = 0; i < size; i++) {
                Node<E> current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            currentLevel++;
        }
        return 0;
    }

    //Ejercicio 2
    public int areaBST() {
        int leafNodes = countLeafNodes(root);
        int treeHeight = heightIterative(root);
        return leafNodes * treeHeight;
    }

    private int countLeafNodes(Node<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeafNodes(node.left) + countLeafNodes(node.right);
    }

    
    public void drawBST() {
        draw(root, "", true);
    }

    private void draw(Node<E> node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.data);
        draw(node.left, prefix + (isLeft ? "│   " : "    "), true);
        draw(node.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    
    public boolean sameArea(LinkedBST<E> otherTree) {
        int area1 = this.areaBST();
        int area2 = otherTree.areaBST();
        return area1 == area2;
    }

 // Método parenthesize
    public String parenthesize() {
        return parenthesizeRec(root);
    }

    private String parenthesizeRec(Node<E> node) {
        if (node == null) return "()"; // Subárbol vacío

        // Siempre empieza con el dato del nodo
        String result = node.data.toString();

        // Obtener las representaciones de los hijos
        String left = parenthesizeRec(node.left);
        String right = parenthesizeRec(node.right);

        // Solo agregar los hijos si alguno no es nulo
        if (!left.equals("()") || !right.equals("()")) {
            result += " (" + left + " " + right + ")";
        }

        return result;
    }



    
    
}
