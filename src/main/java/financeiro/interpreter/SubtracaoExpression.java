package hamburgueria.financeiro.interpreter;

import java.util.Map;

public class SubtracaoExpression implements Expressao {
    private final Expressao esquerda;
    private final Expressao direita;

    public SubtracaoExpression(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public double interpretar(Map<String, Double> contexto) {
        return esquerda.interpretar(contexto) - direita.interpretar(contexto);
    }
}
