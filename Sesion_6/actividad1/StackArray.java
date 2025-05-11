package actividad1;
public class StackArray<E> implements Stack<E> {
    private E[] array; // generico
    private int top; //indice

    // Constructor que recibe el tamaño de la pila
    public StackArray(int n) {
        this.array = (E[]) new Object[n];
        this.top = -1;
    }

    // Inserta un elemento en la pila
    @Override
    public void push(E x) {
        if (isFull()) {
            System.out.println("La pila está llena.");
        } else {
            array[++top] = x;
        }
    }

    // Elimina y devuelve el elemento en el tope de la pila
    @Override
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía.");
        }
        E element = array[top];
        array[top--] = null;
        return element;
    }

    // Devuelve el elemento en el tope sin eliminarlo
    @Override
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía.");
        }
        return array[top];
    }

    // Verifica si la pila está vacía
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    // Verifica si la pila está llena
    public boolean isFull() {
        return top == array.length - 1;
    }

    // Devuelve una representación de la pila como una cadena
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(array[i]);
            if (i < top) sb.append(", ");//separa con ","
        }
        sb.append("]");
        return sb.toString();
    }
}
