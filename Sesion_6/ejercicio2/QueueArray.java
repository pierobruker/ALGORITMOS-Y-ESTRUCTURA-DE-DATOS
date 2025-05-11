package ejercicio2;
import actividad2.ExceptionIsEmpty;
import actividad2.Queue;

public class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int front, rear, size;

    public QueueArray(int capacity) {
        this.array = (E[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(E x) {
        if (this.isFull()) {
            throw new IllegalStateException("La cola está llena");
        }
        this.rear = (this.rear + 1) % this.array.length;
        this.array[this.rear] = x;
        this.size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        E data = this.array[this.front];
        this.front = (this.front + 1) % this.array.length;
        this.size--;
        return data;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return this.array[this.front];
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return this.array[this.rear];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.array.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.array[(this.front + i) % this.array.length]);
            if (i < this.size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
