package btree;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    protected int idNode;

    public BNode(int n, int idNode) {
        this.keys = new ArrayList<>(n);
        this.childs = new ArrayList<>(n + 1);
        this.count = 0;
        this.idNode = idNode;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }

    public boolean nodeFull(int order) {
        return this.count == order;
    }

    public boolean nodeEmpty() {
        return this.count == 0;
    }

    // Realiza la búsqueda de una clave en el nodo
    public boolean searchNode(E key, int[] pos) {
        for (int i = 0; i < this.count; i++) {
            if (key.compareTo(this.keys.get(i)) == 0) {
                pos[0] = i;
                return true;
            }
            if (key.compareTo(this.keys.get(i)) < 0) {
                pos[0] = i;
                return false; // Clave no encontrada, pero posición hallada
            }
        }
        pos[0] = this.count;
        return false; // Clave no encontrada, posición al final
    }

    @Override
    public String toString() {
        return "Node " + idNode + ": " + keys.toString();
    }
}
