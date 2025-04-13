import java.util.*;

class Limits {
    int[] a; // Arreglo del subarreglo
    int prim; // Índice inicial
    int ult;  // Índice final

    public Limits(int[] a, int prim, int ult) {
        this.a = a;
        this.prim = prim;
        this.ult = ult;
    }
}

class SetVectors {
    List<Limits> sets = new ArrayList<>();

    // Insertar un nuevo subarreglo
    public void insertar(Limits set) {
        sets.add(set);
    }

    // Extraer el subarreglo más largo
    public Limits mayor() {
        return sets.stream().max(Comparator.comparingInt(s -> s.ult - s.prim)).orElse(null);
    }

    // Obtener el tamaño de un conjunto
    public int longMayor() {
        return sets.isEmpty() ? 0 : sets.get(0).ult - sets.get(0).prim;
    }

    // Comprobar si el conjunto está vacío
    public boolean esVacio() {
        return sets.isEmpty();
    }

    // Eliminar un conjunto
    public void destruir() {
        sets.clear();
    }
}

public class Moda {

    // Función principal que implementa el algoritmo para encontrar la moda
    public int moda3(int[] a, int prim, int ult) {
        SetVectors heterogeneo = new SetVectors();
        SetVectors homogeneo = new SetVectors();

        // Crear el primer subarreglo con el arreglo completo
        Limits p = new Limits(a, prim, ult);
        heterogeneo.insertar(p);

        // Mientras el conjunto heterogéneo sea más grande que el conjunto homogéneo
        while (heterogeneo.longMayor() > homogeneo.longMayor()) {
            p = heterogeneo.mayor();  // Extraer el subarreglo más grande de heterogéneo

            // Calcular la mediana
            int mediana = a[(p.prim + p.ult) / 2];

            // Dividir el subarreglo en tres partes: menor, igual a la mediana, mayor
            int izq = p.prim;
            int der = p.ult;

            // Pivote: dividir el arreglo en 3 subarreglos
            pivote2(a, mediana, p.prim, p.ult, izq, der);

            // Crear los tres subarreglos
            Limits p1 = new Limits(a, p.prim, izq - 1);
            Limits p2 = new Limits(a, izq, der - 1);
            Limits p3 = new Limits(a, der, p.ult);

            // Actualizar los conjuntos
            if (p1.prim < p1.ult) {
                heterogeneo.insertar(p1);
            }
            if (p3.prim < p3.ult) {
                heterogeneo.insertar(p3);
            }
            if (p2.prim < p2.ult) {
                homogeneo.insertar(p2);
            }
        }

        // Si el conjunto homogéneo está vacío, devolver el primer elemento
        if (homogeneo.esVacio()) {
            return a[prim];
        }

        // Si no está vacío, devolver el elemento con la moda
        p = homogeneo.mayor();
        heterogeneo.destruir();
        homogeneo.destruir();
        return a[p.prim];
    }

    // Función para dividir el arreglo según la mediana
    private void pivote2(int[] a, int mediana, int prim, int ult, int izq, int der) {
        // Implementación del método para dividir el arreglo en tres subarreglos
        // Menor, igual y mayor que la mediana (pivote)
        while (izq <= der) {
            while (a[izq] < mediana) {
                izq++;
            }
            while (a[der] > mediana) {
                der--;
            }

            if (izq <= der) {
                int temp = a[izq];
                a[izq] = a[der];
                a[der] = temp;
                izq++;
                der--;
            }
        }
    }

    // Método principal para probar el código
    public static void main(String[] args) {
        Moda moda = new Moda();
        int[] arreglo = {1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 8};
        int resultado = moda.moda3(arreglo, 0, arreglo.length - 1);
        System.out.println("La moda es: " + resultado);
    }
}
