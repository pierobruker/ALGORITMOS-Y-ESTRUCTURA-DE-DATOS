import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar el numero maximo de rectángulos al usuario
        System.out.print("Ingrese el número máximo de rectángulos a almacenar: ");
        int maxRectangulos = scanner.nextInt();
        ContainerRect contenedor = new ContainerRect(maxRectangulos);
        
        // Bucle para ingresar los rectngulos hasta el maximo permitido
        for (int i = 0; i < maxRectangulos; i++) {
            System.out.println("Ingrese las coordenadas para el rectángulo " + (i + 1) + ":");
            Coordenada c1 = ingresarCoordenada(scanner);
            Coordenada c2 = ingresarCoordenada(scanner);
            Rectangulo rect = new Rectangulo(c1, c2);
            contenedor.addRectangulo(rect);
        }
        
        // Mostrar todos los rectángulos almacenados
        System.out.println(contenedor);
    }

    public static Coordenada ingresarCoordenada(Scanner scanner) {
        System.out.print("Ingrese la coordenada x: ");
        double x = scanner.nextDouble();
        System.out.print("Ingrese la coordenada y: ");
        double y = scanner.nextDouble();
        return new Coordenada(x, y);
    }
}
