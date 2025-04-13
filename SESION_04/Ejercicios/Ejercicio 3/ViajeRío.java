public class ViajeRío {

    // Función para calcular el costo mínimo entre dos embarcaderos
    public static void viajeMasBarato(int[][] T, int n) {
        int[][] C = new int[n][n];

        // Inicializar la matriz de costos
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    C[i][j] = 0;
                } else if (T[i][j] != 0) {
                    C[i][j] = T[i][j];
                } else {
                    C[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (C[i][j] > C[i][k] + C[k][j]) {
                        C[i][j] = C[i][k] + C[k][j];
                    }
                }
            }
        }

        // Imprimir la matriz de costos mínimos
        System.out.println("Matriz de costos mínimos:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (C[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(C[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] tarifas = {
            {0, 10, 20, 0, 0},
            {10, 0, 0, 50, 10},
            {20, 0, 0, 10, 30},
            {0, 50, 10, 0, 0},
            {0, 10, 30, 0, 0}
        };
        int n = tarifas.length;
        viajeMasBarato(tarifas, n);
    }
}
