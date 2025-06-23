package modelo;

public class Ruta {

    // Nodo de origen de la ruta (ubicación de partida)
    private final Ubicacion origen;

    // Nodo de destino de la ruta (ubicación de llegada)
    private final Ubicacion destino;

    // Peso de la ruta (puede representar distancia, tiempo, costo, etc.)
    private double peso;

    // Constructor principal: inicializa la ruta con origen, destino y peso
    public Ruta(Ubicacion origen, Ubicacion destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    // Getters de los atributos
   
    public Ubicacion getOrigen() {
        return origen;
    }

    public Ubicacion getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    // Setter para actualizar el peso de la ruta

    public void setPeso(double peso) {
        this.peso = peso;
    }

    // Representación textual de la ruta

    @Override
    public String toString() {
        return origen.getId() + " → " + destino.getId() + " (" + peso + ")";
    }
}
