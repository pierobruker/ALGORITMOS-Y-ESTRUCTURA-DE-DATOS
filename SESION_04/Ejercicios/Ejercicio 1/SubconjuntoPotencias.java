import java.util.*;

public class SubconjuntoPotencias {

    // Función para verificar si un número es potencia de 2
    public static boolean esPotenciaDe2(int n) {
        return (n & (n - 1)) == 0 && n > 0;
    }

    // Función que resuelve el problema del subconjunto
    public static boolean encontrarSubconjunto(int[] arr, int objetivo) {
        List<Integer> subconjunto = new ArrayList<>();

        // Agregar todos los números que son potencias de 2
        for (int num : arr) {
            if (esPotenciaDe2(num)) {
                subconjunto.add(num);
            }
        }

        // Verificar la restricción sobre múltiplos de 5
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num % 5 == 0 && i + 1 < arr.length && arr[i + 1] % 2 != 0) {
                continue;
            } else {
                subconjunto.add(num);
            }
        }

        // Usar programación dinámica para verificar si hay un subconjunto con la suma objetivo
        boolean[] dp = new boolean[objetivo + 1];
        dp[0] = true;

        // Llenar la tabla dp
        for (int num : subconjunto) {
            for (int j = objetivo; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[objetivo];
    }

    public static void main(String[] args) {
        int[] arr = {4, 8, 10, 3, 5};
        int objetivo = 27;
        System.out.println("¿Es posible encontrar un subconjunto con suma objetivo? " + encontrarSubconjunto(arr, objetivo));
    }
}
