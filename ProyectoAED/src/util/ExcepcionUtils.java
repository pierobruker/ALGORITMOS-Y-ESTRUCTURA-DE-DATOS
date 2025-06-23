package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExcepcionUtils {

    public static int leerEnteroSeguro(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.nextLine();
            }
        }
    }

    public static double leerDoubleSeguro(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número decimal válido.");
                scanner.nextLine();
            }
        }
    }

    public static String leerLinea(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}