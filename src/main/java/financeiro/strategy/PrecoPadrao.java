package hamburgueria.financeiro.strategy;

public class PrecoPadrao implements PoliticaPreco {
    public double aplicar(double subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal invalido");
        }
        return subtotal;
    }
}
