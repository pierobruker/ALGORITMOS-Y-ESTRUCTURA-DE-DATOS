package actividad2;

public class BSTree<E extends Comparable<E>> {
    protected Node<E> root;

    public BSTree() {
        root = null;
    }

    public void insert(E key) {
        root = insert(root, key);
    }

    protected Node<E> insert(Node<E> node, E key) {
        if (node == null) {
            return new Node<>(key);
        }
        int cmp = key.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, key);
        } else if (cmp > 0) {
            node.right = insert(node.right, key);
        } // ignoramos duplicados
        return node;
    }

    public boolean search(E key) {
        return search(root, key);
    }

    private boolean search(Node<E> node, E key) {
        if (node == null) return false;
        int cmp = key.compareTo(node.data);
        if (cmp == 0) return true;
        else if (cmp < 0) return search(node.left, key);
        else return search(node.right, key);
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<E> node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node + " ");
            inorder(node.right);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void delete(E key) {
        root = delete(root, key);
    }

    protected Node<E> delete(Node<E> node, E key) {
        return node;
    }
    
    public void breadthFirstTraversal() {
        int h = height();
        for (int i = 0; i < h; i++) {
            printLevel(root, i);
        }
        System.out.println();
    }

    private void printLevel(Node<E> node, int level) {
        if (node == null) return;
        if (level == 0) {
            System.out.print(node + " ");
        } else {
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }


}
