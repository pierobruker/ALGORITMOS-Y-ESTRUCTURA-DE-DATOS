package modelos;

import java.util.*;

public class BPlusNode {
    boolean esHoja;
    List<Producto> productos;
    List<BPlusNode> hijos;

    public BPlusNode(boolean esHoja) {
        this.esHoja = esHoja;
        this.productos = new ArrayList<>();
        this.hijos = new ArrayList<>();
    }
}
