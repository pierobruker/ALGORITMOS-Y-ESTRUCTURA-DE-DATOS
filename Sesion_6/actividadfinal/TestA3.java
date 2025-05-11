package actividadfinal;

public class TestA3 {
    public static void main(String[] args) {
        try {
            PriorityQueue<String, Integer> queue = new PriorityQueueLinkSort<>();

            queue.enqueue("comando 1", 3); 
            queue.enqueue("comando 2", 1);
            queue.enqueue("comando 3", 2);

            System.out.println("Front: " + queue.front());
            System.out.println("Back: " + queue.back());

            System.out.println("Dequeue: " + queue.dequeue());
            System.out.println("Dequeue: " + queue.dequeue());
            System.out.println("Dequeue: " + queue.dequeue());

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
