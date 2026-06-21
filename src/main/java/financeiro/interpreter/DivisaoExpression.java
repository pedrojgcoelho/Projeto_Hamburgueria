package hamburgueria.financeiro.interpreter;

import java.util.Map;

public class DivisaoExpression implements Expressao {
    private final Expressao esquerda;
    private final Expressao direita;

    public DivisaoExpression(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public double interpretar(Map<String, Double> contexto) {
        double divisor = direita.interpretar(contexto);
        if (divisor == 0) {
            throw new ArithmeticException("Divisao por zero");
        }
        return esquerda.interpretar(contexto) / divisor;
    }
}
