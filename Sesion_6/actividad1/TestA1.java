package actividad1;
public class TestA1 {
    public static void main(String[] args) {
        // Pila de enteros
        Stack<Integer> stack1 = new StackArray<>(5);
        
        try {
            stack1.push(100);
            stack1.push(200);
            stack1.push(300);
            System.out.println("Pila de enteros: " + stack1);
            System.out.println("Elemento en el tope: " + stack1.top());
            
            stack1.pop();
            System.out.println("Pila de enteros después de eliminar el tope: " + stack1);
            
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
        
        // Pila de cadenas de texto
        Stack<String> stack2 = new StackArray<>(3);
        
        try {
            stack2.push("Hola");
            stack2.push("Juan");
            System.out.println("Pila de cadenas: " + stack2);
            System.out.println("Elemento en el tope: " + stack2.top());
            
            stack2.pop();
            System.out.println("Pila de cadenas después de eliminar el tope: " + stack2);
            
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
