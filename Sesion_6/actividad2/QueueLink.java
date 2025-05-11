package actividad2;
public class QueueLink<E> implements Queue<E> {
    private Node<E> first; // Apunta al primer nodo de la cola
    private Node<E> last;  // Apunta al último nodo de la cola

    public QueueLink() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void enqueue(E x) {
        Node<E> aux = new Node<>(x); // Crea un nuevo nodo con el elemento
        if (isEmpty()) {
            first = aux;  // Si la cola está vacía, el primer y último nodo son el mismo
        } else {
            last.setNext(aux); 
        }
        last = aux;  
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        E element = first.getData();
        first = first.getNext();  
        if (first == null) {
            last = null;  
        }
        return element;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        return first.getData();
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        return last.getData();
    }

    @Override
    public boolean isEmpty() {
        return first == null; 
    }

    // Devuelve una representación de la cola como una cadena
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
