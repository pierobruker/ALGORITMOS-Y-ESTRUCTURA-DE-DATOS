package ejercicio2;
import actividad2.ExceptionIsEmpty;
import actividad2.Queue;

public class TestE2 {
    public static void main(String[] args) {
        try {
            Queue<String> queue = new QueueArray<>(5);

            queue.enqueue("uno");
            queue.enqueue("dos");
            queue.enqueue("tres");

            System.out.println("Cola: " + queue);
            System.out.println("Front: " + queue.front()); // "uno"
            System.out.println("Back: " + queue.back()); // "tres"

            queue.dequeue(); // Elimina "uno"
            System.out.println("Cola después del dequeue: " + queue);

            System.out.println("Front: " + queue.front()); // "dos"
            System.out.println("Back: " + queue.back()); // "tres"

            queue.dequeue(); // Elimina "dos"
            queue.dequeue(); // Elimina "tres"

            System.out.println("¿Está vacía? " + queue.isEmpty()); // true

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
