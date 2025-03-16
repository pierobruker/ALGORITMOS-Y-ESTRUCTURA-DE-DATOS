import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear dos rectángulos con coordenadas ingresadas por el usuario
        System.out.println("Ingrese las coordenadas para el primer rectángulo:");
        Coordenada c1 = ingresarCoordenada(scanner);
        Coordenada c2 = ingresarCoordenada(scanner);
        Rectangulo rectA = new Rectangulo(c1, c2);

        System.out.println("Ingrese las coordenadas para el segundo rectángulo:");
        Coordenada c3 = ingresarCoordenada(scanner);
        Coordenada c4 = ingresarCoordenada(scanner);
        Rectangulo rectB = new Rectangulo(c3, c4);

        // Mostrar información de los rectángulos
        mostrarInformacionRectangulo(rectA, "A");
        mostrarInformacionRectangulo(rectB, "B");

        // Verificar la relación entre los rectángulos
        if (Verificador.seSobreponen(rectA, rectB)) {
            System.out.println("Rectangulos A y B se sobreponen.");
            Rectangulo rectSobre = rectanguloSobre(rectA, rectB);
            System.out.println("Area de sobreposicion: " + rectSobre.calculoArea());
        } else if (Verificador.estanJuntos(rectA, rectB)) {
            System.out.println("Rectangulos A y B están juntos.");
        } else if (Verificador.sonDisjuntos(rectA, rectB)) {
            System.out.println("Rectangulos A y B son disjuntos.");
        }
    }

    // Método para ingresar una coordenada desde la consola
    public static Coordenada ingresarCoordenada(Scanner scanner) {
        System.out.print("Ingrese la coordenada x: ");
        double x = scanner.nextDouble();
        System.out.print("Ingrese la coordenada y: ");
        double y = scanner.nextDouble();
        return new Coordenada(x, y);
    }

    // Método para mostrar la información de un rectángulo
    public static void mostrarInformacionRectangulo(Rectangulo rect, String nombre) {
        System.out.println("Rectangulo " + nombre + ": " + rect.toString());
    }

    // Método para calcular el rectángulo de sobreposición
    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        double x1 = Math.max(r1.getEsquina1().getX(), r2.getEsquina1().getX());
        double y1 = Math.max(r1.getEsquina1().getY(), r2.getEsquina1().getY());
        double x2 = Math.min(r1.getEsquina2().getX(), r2.getEsquina2().getX());
        double y2 = Math.min(r1.getEsquina2().getY(), r2.getEsquina2().getY());

        Coordenada nuevaEsquina1 = new Coordenada(x1, y1);
        Coordenada nuevaEsquina2 = new Coordenada(x2, y2);

        return new Rectangulo(nuevaEsquina1, nuevaEsquina2);
    }
}