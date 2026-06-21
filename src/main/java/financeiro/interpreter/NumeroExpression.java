package hamburgueria.financeiro.interpreter;

import java.util.Map;

public class NumeroExpression implements Expressao {
    private final double valor;

    public NumeroExpression(double valor) {
        this.valor = valor;
    }

    public double interpretar(Map<String, Double> contexto) {
        return valor;
    }
}
