import java.util.Arrays;  // Importa la clase Arrays

public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {  // O(1): Verifica si el arreglo tiene más de un elemento.
            int mid = arr.length / 2;  // O(1): Calcula la mitad del arreglo.

            // O(n): Copia los elementos de la primera mitad del arreglo.
            int[] left = Arrays.copyOfRange(arr, 0, mid);  
            // O(n): Copia los elementos de la segunda mitad del arreglo.
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);  

            // Llamadas recursivas: O(log n) para dividir el arreglo en subarreglos más pequeños.
            mergeSort(left);  
            mergeSort(right);  

            // O(n): Fusiona los dos subarreglos ordenados en el arreglo original.
            merge(arr, left, right);  
        }
    }

    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;  // O(1): Inicializa los índices para recorrer los subarreglos.

        // O(n): Compara y coloca los elementos de los subarreglos `left` y `right` en el arreglo `result`.
        while (i < left.length && j < right.length) {
            result[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
        }

        // O(n/2): Si aún quedan elementos en el subarreglo `left`, los copia al arreglo `result`.
        while (i < left.length) {
            result[k++] = left[i++];
        }

        // O(n/2): Si aún quedan elementos en el subarreglo `right`, los copia al arreglo `result`.
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}

