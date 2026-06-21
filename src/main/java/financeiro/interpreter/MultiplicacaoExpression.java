package hamburgueria.financeiro.interpreter;

import java.util.Map;

public class MultiplicacaoExpression implements Expressao {
    private final Expressao esquerda;
    private final Expressao direita;

    public MultiplicacaoExpression(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public double interpretar(Map<String, Double> contexto) {
        return esquerda.interpretar(contexto) * direita.interpretar(contexto);
    }
}
