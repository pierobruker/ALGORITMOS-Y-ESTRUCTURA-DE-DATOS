package test;

import hash.HashC;
import hash.Register;

public class TestEje4 {
    public static void main(String[] args) {
        HashC hashTable = new HashC(7); // tamaño 7

        hashTable.insert(new Register(5, "A"));   // 5
        hashTable.insert(new Register(12, "B"));  // 5 (colisión), se va a 6
        hashTable.insert(new Register(19, "C"));  // 5 (colisión), se va a 0

        System.out.println("\nTabla Hash antes de eliminar:");
        hashTable.printTable();

        System.out.println("\nEliminando clave 12...");
        hashTable.delete(12);

        System.out.println("\nTabla Hash después de eliminar 12:");
        hashTable.printTable();

        System.out.println("\nBúsqueda de clave 19 (debe encontrarse aún):");
        Register found = hashTable.search(19);
        System.out.println(found != null ? found : "No encontrado");
    }
}
