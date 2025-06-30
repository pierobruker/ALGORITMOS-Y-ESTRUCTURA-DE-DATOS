package test;

import hash.HashC;
import hash.Register;

public class TestEje2 {
    public static void main(String[] args) {
        HashC hashTable = new HashC(6); // tamaño fijo 6

        hashTable.insert(new Register(12, "X")); // 0
        hashTable.insert(new Register(18, "Y")); // 0 + 1 = 1
        hashTable.insert(new Register(24, "Z")); // 0 + 2 = 2
        hashTable.insert(new Register(30, "W")); // 0 + 3 = 3

        System.out.println("\nTabla Hash con colisiones resueltas por sondeo lineal:");
        hashTable.printTable();
    }
}
