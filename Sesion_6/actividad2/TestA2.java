package actividad2;
public class TestA2 {
    public static void main(String[] args) {
        Queue<Integer> queue = new QueueLink<>();
        try {
            queue.enqueue(10);
            queue.enqueue(20);
            queue.enqueue(30);
            queue.enqueue(40);
            System.out.println("Cola despues de insertar :"+queue);
            System.out.println("Elemento al frente: " + queue.front());
            System.out.println("Elemnto final: "+ queue.back());
            queue.dequeue();
            System.out.println("cola despues de eliminar frente: "+ queue);
            

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
    }
    }
}
