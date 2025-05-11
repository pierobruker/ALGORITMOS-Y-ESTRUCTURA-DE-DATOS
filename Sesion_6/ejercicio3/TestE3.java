package ejercicio3;
import actividadfinal.ExceptionIsEmpty;
import actividadfinal.PriorityQueue;

public class TestE3 {
    public static void main(String[] args) {
        try {
            PriorityQueue<String, Integer> pq = new PriorityQueueLinked<>(3); // 3 niveles de prioridad

            pq.enqueue("Prueba 1", 1); // Prioridad 1 (más baja)
            pq.enqueue("Prueba 2", 0); // Prioridad 0 (más alta)
            pq.enqueue("Prueba 3", 2); // Prioridad 2 (intermedia)

            System.out.println("Front: " + pq.front()); // "Prueba 2" (Prioridad 0)
            System.out.println("Back: " + pq.back()); // "Prueba 1" (Prioridad 1)

            pq.dequeue(); // Elimina "Prueba 2"
            System.out.println("Cola después del dequeue: " + pq);

            pq.dequeue(); // Elimina "Prueba 3"
            pq.dequeue(); // Elimina "Prueba 1"

            System.out.println("¿Está vacía? " + pq.isEmpty()); // true

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
