package hamburgueria.financeiro.interpreter;

import java.util.Map;

public class VariavelExpression implements Expressao {
    private final String nome;

    public VariavelExpression(String nome) {
        this.nome = nome;
    }

    public double interpretar(Map<String, Double> contexto) {
        if (!contexto.containsKey(nome)) {
            throw new IllegalArgumentException("Variavel ausente: " + nome);
        }
        return contexto.get(nome);
    }
}
