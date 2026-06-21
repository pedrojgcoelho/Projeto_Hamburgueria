package hamburgueria.financeiro.strategy;

public class CalculadoraPreco {
    private PoliticaPreco politica;

    public CalculadoraPreco(PoliticaPreco politica) {
        setPolitica(politica);
    }

    public void setPolitica(PoliticaPreco politica) {
        if (politica == null) {
            throw new IllegalArgumentException("Politica obrigatoria");
        }
        this.politica = politica;
    }

    public double calcular(double subtotal) {
        return politica.aplicar(subtotal);
    }
}
