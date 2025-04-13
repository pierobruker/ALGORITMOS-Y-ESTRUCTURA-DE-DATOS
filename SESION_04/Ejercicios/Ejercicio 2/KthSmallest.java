import java.util.*;

public class KthSmallest {

    // Función para realizar el particionamiento
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Función para encontrar el k-ésimo menor usando QuickSelect
    public static int quickSelect(int[] arr, int low, int high, int k) {
        if (low <= high) {
            int pi = partition(arr, low, high);
            if (pi == k) {
                return arr[pi];
            } else if (pi < k) {
                return quickSelect(arr, pi + 1, high, k);
            } else {
                return quickSelect(arr, low, pi - 1, k);
            }
        }
        return -1; // No encontrado
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 10, 4, 17};
        int k = 3; // Queremos el 3er elemento más pequeño
        System.out.println("El " + k + "-ésimo elemento más pequeño es: " + quickSelect(arr, 0, arr.length - 1, k - 1));
    }
}
