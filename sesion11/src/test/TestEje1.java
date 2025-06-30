package test;

import hash.HashC;
import hash.Register;

public class TestEje1 {
    public static void main(String[] args) {
        HashC hashTable = new HashC(7);

        hashTable.insert(new Register(3, "A"));
        hashTable.insert(new Register(10, "B"));
        hashTable.insert(new Register(17, "C"));
        hashTable.insert(new Register(24, "D"));

        System.out.println("Tabla Hash final sin colisiones:");
        hashTable.printTable();
    }
}
