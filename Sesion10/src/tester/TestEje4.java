package tester;

import btree.*;

public class TestEje4 {
    public static void main(String[] args) {
        BTree<RegistroEstudiante> tree = new BTree<>(4);

        tree.insert(new RegistroEstudiante(103, "Ana"));
        tree.insert(new RegistroEstudiante(110, "Luis"));
        tree.insert(new RegistroEstudiante(101, "Carlos"));
        tree.insert(new RegistroEstudiante(120, "Lucía"));
        tree.insert(new RegistroEstudiante(115, "David"));
        tree.insert(new RegistroEstudiante(125, "Jorge"));
        tree.insert(new RegistroEstudiante(140, "Camila"));
        tree.insert(new RegistroEstudiante(108, "Rosa"));
        tree.insert(new RegistroEstudiante(132, "Ernesto"));
        tree.insert(new RegistroEstudiante(128, "Denis"));
        tree.insert(new RegistroEstudiante(145, "Enrique"));
        tree.insert(new RegistroEstudiante(122, "Karina"));
        tree.insert(new RegistroEstudiante(108, "Juan")); // Este es duplicado

        // Operaciones
        System.out.println("Buscar 115: " + tree.buscarNombre(115));
        System.out.println("Buscar 132: " + tree.buscarNombre(132));
        System.out.println("Buscar 999: " + tree.buscarNombre(999));

        System.out.println("Eliminando 101");
        tree.remove(new RegistroEstudiante(101, ""));

        System.out.println("Insertando 106 Sara");
        tree.insert(new RegistroEstudiante(106, "Sara"));

        System.out.println("Buscar 106: " + tree.buscarNombre(106));

        System.out.println("Árbol:");
        System.out.println(tree);
    }
}
