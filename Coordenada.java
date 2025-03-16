import java.util.*;

public class Coordenada {
    private double x;
    private double y;

    // Constructor por defecto
    public Coordenada() {
        this.x = 0;
        this.y = 0;
    }

    // Constructor con parámetros
    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Constructor de copia
    public Coordenada(Coordenada c) {
        this.x = c.x;
        this.y = c.y;
    }

    // Métodos setter
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Métodos getter
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    // Método de instancia que calcula la distancia euclideana
    public double distancia(Coordenada c) {
        return Math.sqrt(Math.pow(this.x - c.x, 2) + Math.pow(this.y - c.y, 2));
    }

    // Método de clase que calcula la distancia euclideana
    public static double distancia(Coordenada c1, Coordenada c2) {
        return Math.sqrt(Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2));
    }

    // Método que devuelve los valores de una coordenada en determinado formato
    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}