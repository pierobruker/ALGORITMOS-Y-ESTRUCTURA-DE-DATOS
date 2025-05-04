public class GestorDeTareas<T> {
    private Node<T> head;

    public GestorDeTareas() {
        this.head = null;
    }

    public void agregarTarea(T tarea) {
        Node<T> newNode = new Node<>(tarea);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public boolean eliminarTarea(T tarea) {
        if (head == null) return false;

        if (head.getValue().equals(tarea)) {
            head = head.getNext();
            return true;
        }

        Node<T> current = head;
        while (current.getNext() != null && !current.getNext().getValue().equals(tarea)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            return true;
        }

        return false;
    }

    public boolean contieneTarea(T tarea) {
        Node<T> current = head;
        while (current != null) {
            if (current.getValue().equals(tarea)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void imprimirTareas() {
        Node<T> current = head;
        if (current == null) {
            System.out.println("(sin tareas)");
        }
        while (current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }

    public int contarTareas() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public T obtenerTareaMasPrioritaria() {
        if (head == null) return null;

        Node<T> current = head;
        T mejor = current.getValue();

        while (current != null) {
            if (((Tarea) current.getValue()).getPrioridad() > ((Tarea) mejor).getPrioridad()) {
                mejor = current.getValue();
            }
            current = current.getNext();
        }
        return mejor;
    }

    public void invertirTareas() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    public Node<T> getHead() {
        return head;
    }
}