import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tipo;

        do {
            System.out.println("Selecciona tipo de dato:");
            System.out.println("1. Integer");
            System.out.println("2. Double");
            tipo = leerEntero(sc, "Opción: ");
        } while (tipo != 1 && tipo != 2);

        boolean salir = false;

        while (!salir) {
            System.out.println("\nMENÚ DE OPERACIONES");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz Cuadrada");
            System.out.println("7. Raíz Cúbica");
            System.out.println("8. Salir");

            int op = leerEntero(sc, "Elige una opción: ");

            if (op == 8) {
                salir = true;
                continue;
            }

            if (tipo == 1) { // Integer
                int a = leerEntero(sc, "Ingrese el primer número (entero): ");
                OperacionesMatInteger num1 = new OperacionesMatInteger(a);

                if (op >= 1 && op <= 4) {
                    int b = leerEntero(sc, "Ingrese el segundo número (entero): ");
                    OperacionesMatInteger num2 = new OperacionesMatInteger(b);

                    switch (op) {
                        case 1 -> UtilidadesGenericas.mostrarResultado(num1.suma(num2).getValor());
                        case 2 -> UtilidadesGenericas.mostrarResultado(num1.resta(num2).getValor());
                        case 3 -> UtilidadesGenericas.mostrarResultado(num1.producto(num2).getValor());
                        case 4 -> {
                            if (num2.getValor() == 0) {
                                System.out.println("Error: No se puede dividir por cero.");
                            } else {
                                UtilidadesGenericas.mostrarResultado(num1.division(num2).getValor());
                            }
                        }
                    }
                } else {
                    switch (op) {
                        case 5 -> {
                            int exp = leerEntero(sc, "Exponente: ");
                            UtilidadesGenericas.mostrarResultado(
                                    OperacionesAvanzadas.potencia(a, exp)
                            );
                        }
                        case 6 -> UtilidadesGenericas.mostrarResultado(
                                OperacionesAvanzadas.raizCuadrada(a)
                        );
                        case 7 -> UtilidadesGenericas.mostrarResultado(
                                OperacionesAvanzadas.raizCubica(a)
                        );
                    }
                }

            } else { // Double
                double a = leerDouble(sc, "Ingrese el primer número (decimal): ");
                OperacionesMatDouble num1 = new OperacionesMatDouble(a);

                if (op >= 1 && op <= 4) {
                    double b = leerDouble(sc, "Ingrese el segundo número (decimal): ");
                    OperacionesMatDouble num2 = new OperacionesMatDouble(b);

                    switch (op) {
                        case 1 -> UtilidadesGenericas.mostrarResultado(num1.suma(num2).getValor());
                        case 2 -> UtilidadesGenericas.mostrarResultado(num1.resta(num2).getValor());
                        case 3 -> UtilidadesGenericas.mostrarResultado(num1.producto(num2).getValor());
                        case 4 -> {
                            if (num2.getValor() == 0.0) {
                                System.out.println("Error: No se puede dividir por cero.");
                            } else {
                                UtilidadesGenericas.mostrarResultado(num1.division(num2).getValor());
                            }
                        }
                    }
                } else {
                    switch (op) {
                        case 5 -> {
                            double exp = leerDouble(sc, "Exponente: ");
                            UtilidadesGenericas.mostrarResultado(
                                    OperacionesAvanzadas.potencia(a, exp)
                            );
                        }
                        case 6 -> UtilidadesGenericas.mostrarResultado(
                                OperacionesAvanzadas.raizCuadrada(a)
                        );
                        case 7 -> UtilidadesGenericas.mostrarResultado(
                                OperacionesAvanzadas.raizCubica(a)
                        );
                    }
                }
            }
        }

        System.out.println("Programa finalizado.");
        sc.close();
    }

    // Metodo genrico de lectura integer
    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("Entrada inválida. Ingresa un número entero.");
                sc.next(); // limpia entrada no válida
            }
        }
    }

    // Metodo genérico de lectura Double
    public static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            } else {
                System.out.println("Entrada inválida. Ingresa un número decimal.");
                sc.next();
            }
        }
    }
}
