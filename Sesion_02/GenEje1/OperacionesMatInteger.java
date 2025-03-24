// OperacionesMatInteger.java
public class OperacionesMatInteger implements Operable<OperacionesMatInteger> {
    private Integer valor;

    public OperacionesMatInteger(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }

    @Override
    public OperacionesMatInteger suma(OperacionesMatInteger otro) {
        return new OperacionesMatInteger(this.valor + otro.valor);
    }

    @Override
    public OperacionesMatInteger resta(OperacionesMatInteger otro) {
        return new OperacionesMatInteger(this.valor - otro.valor);
    }

    @Override
    public OperacionesMatInteger producto(OperacionesMatInteger otro) {
        return new OperacionesMatInteger(this.valor * otro.valor);
    }

    @Override
    public OperacionesMatInteger division(OperacionesMatInteger otro) {
        if (otro.valor == 0) throw new ArithmeticException("División por cero");
        return new OperacionesMatInteger(this.valor / otro.valor);
    }
}
