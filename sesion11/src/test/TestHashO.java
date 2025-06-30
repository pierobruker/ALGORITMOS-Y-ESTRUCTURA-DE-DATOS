package test;

import hash.HashO;
import hash.Register;

public class TestHashO {
    public static void main(String[] args) {
        HashO hashTable = new HashO(5);

        hashTable.insert(new Register(10, "Juan"));
        hashTable.insert(new Register(13, "Ana"));
        hashTable.insert(new Register(20, "Luis"));
        hashTable.insert(new Register(23, "Rosa"));
        hashTable.insert(new Register(30, "Carlos")); // Colisión intencionada
        hashTable.insert(new Register(35, "Maria"));  // Colisión intencionada

        System.out.println("Tabla Hash después de las inserciones:");
        hashTable.printTable();

        Register found = hashTable.search(20);
        System.out.println("\nBúsqueda clave 20: " + (found != null ? found : "No encontrado"));

        // Buscar clave inexistente
        found = hashTable.search(40);
        System.out.println("Búsqueda clave 40: " + (found != null ? found : "No encontrado"));

        // Eliminar clave
        hashTable.delete(15);
        System.out.println("\nTabla Hash después de eliminar clave 15:");
        hashTable.printTable();

        found = hashTable.search(15);
        System.out.println("\nBúsqueda clave 15 tras eliminación: " + (found != null ? found : "No encontrado"));
    
    }   
}