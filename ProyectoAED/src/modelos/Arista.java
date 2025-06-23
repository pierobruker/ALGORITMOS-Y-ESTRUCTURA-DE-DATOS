package modelos;

public class Arista {
    private Nodo origen;
    private Nodo destino;
    private double peso;

    public Arista(Nodo origen, Nodo destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Nodo getOrigen() { return origen; }
    public Nodo getDestino() { return destino; }
    public double getPeso() { return peso; }

    @Override
    public String toString() {
        return origen + " -> " + destino + " [" + peso + "]";
    }
}