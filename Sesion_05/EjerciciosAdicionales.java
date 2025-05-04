import java.util.*;

public class EjerciciosAdicionales {

    // 1. Buscar un elemento genérico en una lista
    public static <T> boolean buscarElemento(List<T> lista, T valor) {
        for (T elemento : lista) {
            if (elemento.equals(valor)) {
                return true;
            }
        }
        return false;
    }

    // 2. Invertir una lista genérica
    public static <T> List<T> invertirLista(List<T> lista) {
        List<T> invertida = new ArrayList<>();
        for (int i = lista.size() - 1; i >= 0; i--) {
            invertida.add(lista.get(i));
        }
        return invertida;
    }

    // 3. Insertar un nodo al final
    public static <T> Node<T> insertarAlFinal(Node<T> head, T valor) {
        Node<T> nuevo = new Node<>(valor);
        if (head == null) return nuevo;

        Node<T> actual = head;
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }
        actual.setNext(nuevo);
        return head;
    }

    // 4. Contar los nodos
    public static <T> int contarNodos(Node<T> head) {
        int contador = 0;
        Node<T> actual = head;
        while (actual != null) {
            contador++;
            actual = actual.getNext();
        }
        return contador;
    }

    // 5. Comparar dos listas
    public static <T> boolean sonIguales(Node<T> l1, Node<T> l2) {
        Node<T> a = l1;
        Node<T> b = l2;
        while (a != null && b != null) {
            if (!a.getValue().equals(b.getValue())) {
                return false;
            }
            a = a.getNext();
            b = b.getNext();
        }
        return a == null && b == null;
    }

    // 6. Concatenar dos listas
    public static <T> Node<T> concatenarListas(Node<T> l1, Node<T> l2) {
        if (l1 == null) return l2;
        Node<T> actual = l1;
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }
        actual.setNext(l2);
        return l1;
    }
}