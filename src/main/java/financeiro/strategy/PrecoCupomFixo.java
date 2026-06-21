package hamburgueria.financeiro.strategy;

public class PrecoCupomFixo implements PoliticaPreco {
    private final double desconto;

    public PrecoCupomFixo(double desconto) {
        if (desconto < 0) {
            throw new IllegalArgumentException("Desconto invalido");
        }
        this.desconto = desconto;
    }

    public double aplicar(double subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal invalido");
        }
        return Math.max(0, subtotal - desconto);
    }
}
