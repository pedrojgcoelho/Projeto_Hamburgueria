package hamburgueria.financeiro.strategy;

public class PrecoHappyHour implements PoliticaPreco {
    public double aplicar(double subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal invalido");
        }
        return subtotal * 0.9;
    }
}
