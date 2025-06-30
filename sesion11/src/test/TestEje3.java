package test;

import hash.HashO;
import hash.Register;

public class TestEje3 {
    public static void main(String[] args) {
        HashO hashTable = new HashO(5); // tamaño 5

        hashTable.insert(new Register(10, "Juan"));
        hashTable.insert(new Register(15, "Ana"));
        hashTable.insert(new Register(20, "Luis"));
        hashTable.insert(new Register(25, "Rosa"));

        System.out.println("Contenido de la tabla Hash Abierto:");
        hashTable.printTable();

        System.out.println("\nBúsqueda de la clave 20:");
        Register found = hashTable.search(20);
        System.out.println(found != null ? found : "No encontrado");

        System.out.println("\nBúsqueda de la clave 30:");
        found = hashTable.search(30);
        System.out.println(found != null ? found : "No encontrado");
    }
}
