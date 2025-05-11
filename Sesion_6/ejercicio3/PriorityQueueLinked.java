package ejercicio3;
import actividadfinal.ExceptionIsEmpty;
import actividadfinal.PriorityQueue;

public class PriorityQueueLinked<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<EntryNode>[] queues;
    private int numPriorities;

    public PriorityQueueLinked(int numPriorities) {
        this.numPriorities = numPriorities;
        this.queues = new Node[numPriorities];
    }

    @Override
    public void enqueue(E x, N pr) {
        int priority = getPriorityIndex(pr);
        if (priority < 0 || priority >= numPriorities) {
            throw new IllegalArgumentException("Prioridad fuera de rango");
        }

        EntryNode newEntry = new EntryNode(x, pr);
        Node<EntryNode> newNode = new Node<>(newEntry);

        // Insertar en la cola correspondiente
        if (this.queues[priority] == null) {
            this.queues[priority] = newNode;
        } else {
            Node<EntryNode> current = this.queues[priority];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = 0; i < numPriorities; i++) {
            if (this.queues[i] != null) {
                Node<EntryNode> node = this.queues[i];
                this.queues[i] = node.next;
                return node.data.data; 
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        for (int i = 0; i < numPriorities; i++) {
            if (this.queues[i] != null) {
                return this.queues[i].data.data;
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        for (int i = numPriorities - 1; i >= 0; i--) {
            if (this.queues[i] != null) {
                Node<EntryNode> current = this.queues[i];
                while (current.next != null) {
                    current = current.next;
                }
                return current.data.data;
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < numPriorities; i++) {
            if (this.queues[i] != null) {
                return false;
            }
        }
        return true;
    }

    private int getPriorityIndex(N pr) {
        if (pr.compareTo((N) Integer.valueOf(0)) == 0) {
            return 0;
        } else if (pr.compareTo((N) Integer.valueOf(1)) == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < numPriorities; i++) {
            if (this.queues[i] != null) {
                Node<EntryNode> current = this.queues[i];
                while (current != null) {
                    sb.append(current.data.data);
                    if (current.next != null) sb.append(", ");
                    current = current.next;
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
