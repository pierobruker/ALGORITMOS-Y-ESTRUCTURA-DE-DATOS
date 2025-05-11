package ejercicio1;
import actividad1.ExceptionIsEmpty;
import actividad1.Stack;


public class TestE1 {
    public static void main(String[] args) {
        try {
            Stack<String> stack = new StackLink<>();
            stack.push("uno");
            stack.push("dos");
            stack.push("tres");

            System.out.println("Pila: " + stack);
            System.out.println("Top: " + stack.top()); // "tres"
            System.out.println("Pop: " + stack.pop()); // Elimina "tres"
            System.out.println("Pila después del pop: " + stack);
            System.out.println("Top: " + stack.top()); // "dos"

            stack.pop(); // Elimina "dos"
            stack.pop(); // Elimina "uno"

            System.out.println("¿Está vacía? " + stack.isEmpty()); // true

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
