public class ImprovedMergeSort {

    public static void mergeSort(int[] arr) {
        // Usamos Insertion Sort en subarreglos pequeños
        if (arr.length <= 10) {
            insertionSort(arr);  // O(n²): Insertion sort para arreglos pequeños
        } else {
            // Llamamos a la versión recursiva de merge sort
            mergeSortRecursive(arr, 0, arr.length - 1);  
        }
    }

    // Método recursivo para Merge Sort
    private static void mergeSortRecursive(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursive(arr, left, mid);  // Recursión para la mitad izquierda
            mergeSortRecursive(arr, mid + 1, right);  // Recursión para la mitad derecha
            merge(arr, left, mid, right);  // Fusionamos las dos mitades
        }
    }

    // Método para fusionar dos subarreglos ordenados
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];  // O(n): Usamos un arreglo temporal para la fusión
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);  // O(n): Copia el arreglo fusionado de nuevo a `arr`
    }

    // Implementación de Insertion Sort para subarreglos pequeños
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;  // O(n²): Inserta el valor en su posición correcta
        }
    }
}
