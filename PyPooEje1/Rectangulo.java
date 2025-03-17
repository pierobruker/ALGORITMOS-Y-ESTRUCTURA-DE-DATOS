
public class Rectangulo {
    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor
    public Rectangulo(Coordenada c1, Coordenada c2) {
        double minX = Math.min(c1.getX(), c2.getX());
        double minY = Math.min(c1.getY(), c2.getY());
        double maxX = Math.max(c1.getX(), c2.getX());
        double maxY = Math.max(c1.getY(), c2.getY());
    
        this.esquina1 = new Coordenada(minX, minY);
        this.esquina2 = new Coordenada(maxX, maxY);
    }


    // Métodos setter
    public void setEsquina1(Coordenada coo) {
        this.esquina1 = coo;
    }

    public void setEsquina2(Coordenada coo) {
        this.esquina2 = coo;
    }

    // Métodos getter
    public Coordenada getEsquina1() {
        return this.esquina1;
    }

    public Coordenada getEsquina2() {
        return this.esquina2;
    }

    // Método que calcula el área del rectángulo
    public double calculoArea() {
        double base = Math.abs(this.esquina1.getX() - this.esquina2.getX());
        double altura = Math.abs(this.esquina1.getY() - this.esquina2.getY());
        return base * altura;
    }

    // Método que devuelve una representación en cadena del rectángulo
    @Override
    public String toString() {
        return "Rectangulo: (" + this.esquina1.toString() + ", " + this.esquina2.toString() + ")";
    }
}