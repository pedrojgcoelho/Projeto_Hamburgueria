package hamburgueria.financeiro.interpreter;

import java.util.Map;

public interface Expressao {
    double interpretar(Map<String, Double> contexto);
}
