package util;

import java.util.Scanner;

public class EntradaUsuario {

    // Scanner global estático para leer la entrada estándar (consola)
    private static final Scanner scanner = new Scanner(System.in);

    // Método para leer un String desde consola
    public static String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim(); // Elimina espacios extras al inicio y al final
    }

    // Método para leer un número entero validado dentro de un rango
    public static int leerEntero(String mensaje, int min, int max) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                // Si el número está fuera de rango
                System.out.println("Error: Ingrese un valor entre " + min + " y " + max);
            } catch (NumberFormatException e) {
                // Si el usuario ingresa algo que no es número
                System.out.println("Error: Ingrese un número válido");
            }
        }
    }

    // Método para leer un número decimal (double) validado dentro de un rango
    public static double leerDouble(String mensaje, double min, double max) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                // Si está fuera de rango
                System.out.println("Error: Ingrese un valor entre " + min + " y " + max);
            } catch (NumberFormatException e) {
                // Si el usuario ingresa un texto inválido
                System.out.println("Error: Ingrese un número válido");
            }
        }
    }

    // Método para leer un valor booleano (sí/no)
    public static boolean leerBooleano(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (s/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("s")) return true;
            if (input.equals("n")) return false;
            System.out.println("Error: Ingrese 's' o 'n'");
        }
    }
    
    public static String leerTipoUbicacion(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String tipo = scanner.nextLine().trim().toUpperCase();
            if (tipo.equals("ESTANTERIA") || tipo.equals("PASILLO") || tipo.equals("CARGA")) {
                return tipo;
            }
            System.out.println("Error: Tipo inválido. Debe ingresar 'ESTANTERIA', 'PASILLO' o 'CARGA'");
        }
    }
}
