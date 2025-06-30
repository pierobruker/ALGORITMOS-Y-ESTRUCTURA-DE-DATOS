package test;

import hash.HashC;
import hash.Register;

public class Test {
    public static void main(String[] args) {
        HashC hashTable = new HashC(10);

         hashTable.insert(new Register(34, "A"));   //
        hashTable.insert(new Register(3, "B"));
        hashTable.insert(new Register(7, "C"));
        hashTable.insert(new Register(30, "D"));	//0
        hashTable.insert(new Register(11, "E"));
        hashTable.insert(new Register(8, "F"));
        hashTable.insert(new Register(7, "G"));
        hashTable.insert(new Register(23, "H"));	//
        hashTable.insert(new Register(41, "I"));
        hashTable.insert(new Register(16, "J"));
        hashTable.insert(new Register(34, "K"));
        
        System.out.println("\nTabla Hash antes de eliminar:");
        hashTable.printTable();

        hashTable.delete(30);
        System.out.println("\nTabla Hash después de eliminar 30:");
        hashTable.printTable();

        Register found = hashTable.search(23);
        System.out.println("\nResultado búsqueda de 23: " + (found != null ? found : "No encontrado"));
    }
}
