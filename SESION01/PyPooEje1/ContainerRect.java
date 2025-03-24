class ContainerRect {
    private Rectangulo[] rectangulos;
    private double[] distancias;
    private double[] areas;
    private int n;
    private static int numRec = 0;

    public ContainerRect(int capacidad) {
        this.n = capacidad;
        rectangulos = new Rectangulo[n];
        distancias = new double[n];
        areas = new double[n];
    }

    public void addRectangulo(Rectangulo rect) {
        if (numRec < n) {
            rectangulos[numRec] = rect;
            distancias[numRec] = Coordenada.distancia(rect.getEsquina1(), rect.getEsquina2());
            areas[numRec] = rect.calculoArea();
            numRec++;
        } else {
            System.out.println("No es posible agregar más rectángulos.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rectángulo\tCoordenadas\tDistancia\tÁrea\n");
        for (int i = 0; i < numRec; i++) {
            sb.append((i + 1)).append("\t").append(rectangulos[i])
              .append("\t").append(String.format("%.3f", distancias[i]))
              .append("\t").append(String.format("%.2f", areas[i]))
              .append("\n");
        }
        return sb.toString();
    }
}
