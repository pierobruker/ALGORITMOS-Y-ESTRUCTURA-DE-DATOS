// OperacionesMatDouble.java
public class OperacionesMatDouble implements Operable<OperacionesMatDouble> {
    private Double valor;

    public OperacionesMatDouble(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public OperacionesMatDouble suma(OperacionesMatDouble otro) {
        return new OperacionesMatDouble(this.valor + otro.valor);
    }

    @Override
    public OperacionesMatDouble resta(OperacionesMatDouble otro) {
        return new OperacionesMatDouble(this.valor - otro.valor);
    }

    @Override
    public OperacionesMatDouble producto(OperacionesMatDouble otro) {
        return new OperacionesMatDouble(this.valor * otro.valor);
    }

    @Override
    public OperacionesMatDouble division(OperacionesMatDouble otro) {
        if (otro.valor == 0.0) throw new ArithmeticException("División por cero");
        return new OperacionesMatDouble(this.valor / otro.valor);
    }
}
