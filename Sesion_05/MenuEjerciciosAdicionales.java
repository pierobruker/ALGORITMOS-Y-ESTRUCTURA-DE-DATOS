import java.util.*;

public class MenuEjerciciosAdicionales {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> listaGenerica = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
        Node<Integer> lista1 = new Node<>(1);
        lista1.setNext(new Node<>(2));
        lista1.getNext().setNext(new Node<>(3));

        Node<Integer> lista2 = new Node<>(4);
        lista2.setNext(new Node<>(5));

        while (true) {
            System.out.println("\n--- Menú de Ejercicios Adicionales ---");
            System.out.println("1. Buscar elemento en una lista genérica");
            System.out.println("2. Invertir una lista genérica");
            System.out.println("3. Insertar nodo al final de una lista enlazada");
            System.out.println("4. Contar nodos de una lista enlazada");
            System.out.println("5. Comparar dos listas enlazadas");
            System.out.println("6. Concatenar dos listas enlazadas");
            System.out.println("7. Ver contenido de listas");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el número a buscar en listaGenerica (10,20,30,40): ");
                    int valor = scanner.nextInt();
                    boolean encontrado = EjerciciosAdicionales.buscarElemento(listaGenerica, valor);
                    System.out.println(encontrado ? "Elemento encontrado." : "Elemento no encontrado.");
                    break;

                case 2:
                    List<Integer> invertida = EjerciciosAdicionales.invertirLista(listaGenerica);
                    System.out.println("Lista invertida: " + invertida);
                    break;

                case 3:
                    System.out.print("Ingresa valor para insertar al final de lista1: ");
                    int nuevoValor = scanner.nextInt();
                    lista1 = EjerciciosAdicionales.insertarAlFinal(lista1, nuevoValor);
                    imprimirLista(lista1);
                    break;

                case 4:
                    System.out.println("Cantidad de nodos en lista1: " + EjerciciosAdicionales.contarNodos(lista1));
                    break;

                case 5:
                    boolean iguales = EjerciciosAdicionales.sonIguales(lista1, lista2);
                    System.out.println(iguales ? "Listas iguales." : "Listas diferentes.");
                    break;

                case 6:
                    Node<Integer> concatenada = EjerciciosAdicionales.concatenarListas(lista1, lista2);
                    System.out.println("Lista concatenada:");
                    imprimirLista(concatenada);
                    break;

                case 7:
                    System.out.println("Contenido de listaGenerica: " + listaGenerica);
                    System.out.print("Contenido de lista1: ");
                    imprimirLista(lista1);
                    System.out.print("Contenido de lista2: ");
                    imprimirLista(lista2);
                    break;

                case 8:
                    System.out.println("Saliendo del programa.");
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public static <T> void imprimirLista(Node<T> head) {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.getValue() + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}

