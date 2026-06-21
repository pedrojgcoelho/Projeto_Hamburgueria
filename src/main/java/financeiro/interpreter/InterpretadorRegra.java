package hamburgueria.financeiro.interpreter;

public class InterpretadorRegra {
    public Expressao interpretar(String regra) {
        if (regra == null || regra.isBlank()) {
            throw new IllegalArgumentException("Regra obrigatoria");
        }
        String[] partes = regra.trim().split(" ");
        if (partes.length == 1) {
            return termo(partes[0]);
        }
        if (partes.length != 3) {
            throw new IllegalArgumentException("Regra deve ter formato: a + b");
        }
        Expressao esquerda = termo(partes[0]);
        Expressao direita = termo(partes[2]);
        return operador(esquerda, partes[1], direita);
    }

    private Expressao termo(String texto) {
        try {
            return new NumeroExpression(Double.parseDouble(texto));
        } catch (NumberFormatException ex) {
            return new VariavelExpression(texto);
        }
    }

    private Expressao operador(Expressao esquerda, String operador, Expressao direita) {
        if ("+".equals(operador)) {
            return new SomaExpression(esquerda, direita);
        }
        if ("-".equals(operador)) {
            return new SubtracaoExpression(esquerda, direita);
        }
        if ("*".equals(operador)) {
            return new MultiplicacaoExpression(esquerda, direita);
        }
        if ("/".equals(operador)) {
            return new DivisaoExpression(esquerda, direita);
        }
        throw new IllegalArgumentException("Operador invalido");
    }
}
