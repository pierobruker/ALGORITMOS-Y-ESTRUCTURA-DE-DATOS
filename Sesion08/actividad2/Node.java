package actividad2;

public class Node<E extends Comparable<E>> {
    protected E data;
    protected Node<E> left;
    protected Node<E> right;

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
