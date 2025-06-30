package hash;

public class HashC {
    private class Element {
        Register register;
        boolean isAvailable;

        public Element() {
            this.register = null;
            this.isAvailable = true;
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(Register reg) {
        int pos = hash(reg.getKey());
        int startPos = pos;
        do {
            if (table[pos].register == null || !table[pos].isAvailable) {
                table[pos].register = reg;
                table[pos].isAvailable = true;
                System.out.println("Insertado en posición: " + pos);
                return;
            }
            pos = (pos + 1) % size;
        } while (pos != startPos);
        System.out.println("La tabla está llena. No se pudo insertar.");
    }

    public Register search(int key) {
        int pos = hash(key);
        int startPos = pos;
        do {
            if (table[pos].register == null) {
                return null;
            }
            if (table[pos].isAvailable && table[pos].register.getKey() == key) {
                return table[pos].register;
            }
            pos = (pos + 1) % size;
        } while (pos != startPos);
        return null;
    }

    public void delete(int key) {
        int pos = hash(key);
        int startPos = pos;
        do {
            if (table[pos].register == null) {
                return;
            }
            if (table[pos].isAvailable && table[pos].register.getKey() == key) {
                table[pos].isAvailable = false;
                System.out.println("Clave " + key + " eliminada lógicamente.");
                return;
            }
            pos = (pos + 1) % size;
        } while (pos != startPos);
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            if (table[i].register != null && table[i].isAvailable) {
                System.out.println(i + ": " + table[i].register);
            } else {
                System.out.println(i + ": vacío");
            }
        }
    }
}
