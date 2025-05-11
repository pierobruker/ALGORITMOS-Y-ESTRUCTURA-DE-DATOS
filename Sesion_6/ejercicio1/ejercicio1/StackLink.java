package ejercicio1;
import actividad1.ExceptionIsEmpty;
import actividad1.Stack;
import actividad2.Node;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }

    @Override
    public void push(E x) {
        Node<E> newNode = new Node<>(x);
        newNode.setNext(top);
        this.top = newNode;  // newnode ahora es la cima
    }

    @Override
    public E pop() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía");
        }
        E data = this.top.getData();
        this.top = this.top.getNext();  //nextnode convierte en nueva cima
        return data;
    }

    @Override
    public E top() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía");
        }
        return this.top.getData();
    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = this.top;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
