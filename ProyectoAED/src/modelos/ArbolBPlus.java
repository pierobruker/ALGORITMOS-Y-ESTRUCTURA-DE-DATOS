package modelos;

import java.util.*;

public class ArbolBPlus {
    private List<Producto> productos;

    public ArbolBPlus() {
        productos = new ArrayList<>();
    }

    public void insertar(Producto p) {
        productos.add(p);
        productos.sort(Comparator.comparing(Producto::getCodigo));
        System.out.println("Producto insertado: " + p);
    }

    public Producto buscar(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void imprimir() {
        System.out.println("Contenido del árbol B+:");
        for (Producto p : productos) {
            System.out.println("- " + p);
        }
    }
}