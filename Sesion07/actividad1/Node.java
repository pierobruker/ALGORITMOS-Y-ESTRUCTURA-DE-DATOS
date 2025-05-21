package actividad1;

class Node<E> {
    E data;
    Node<E> left, right;

    Node(E data) {
        this.data = data;
        this.left = this.right = null;
    }
}
