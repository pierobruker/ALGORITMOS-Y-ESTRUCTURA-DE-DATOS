package ejercicio4;
import java.util.Stack;

public class SymbolBalancing {

    public static boolean symbolBalancing(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // Pruebas
        String test1 = "()()[()]";
        String test2 = "[(()]";
        String test3 = "[]{}()";
        String test4 = "[(])";
        
        System.out.println(symbolBalancing(test1)); // true
        System.out.println(symbolBalancing(test2)); // false
        System.out.println(symbolBalancing(test3)); // true
        System.out.println(symbolBalancing(test4)); // false
    }
}
