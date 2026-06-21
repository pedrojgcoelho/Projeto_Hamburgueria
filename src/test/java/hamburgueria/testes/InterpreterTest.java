package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.financeiro.interpreter.DivisaoExpression;
import hamburgueria.financeiro.interpreter.Expressao;
import hamburgueria.financeiro.interpreter.InterpretadorRegra;
import hamburgueria.financeiro.interpreter.MultiplicacaoExpression;
import hamburgueria.financeiro.interpreter.NumeroExpression;
import hamburgueria.financeiro.interpreter.SomaExpression;
import hamburgueria.financeiro.interpreter.SubtracaoExpression;
import hamburgueria.financeiro.interpreter.VariavelExpression;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {
    @Test void numeroInterpretaValor() { assertEquals(5.0, new NumeroExpression(5).interpretar(Map.of()), 0.001); }
    @Test void variavelBuscaContexto() { assertEquals(10.0, new VariavelExpression("subtotal").interpretar(Map.of("subtotal", 10.0)), 0.001); }
    @Test void somaInterpretaOperacao() { Expressao e = new SomaExpression(new NumeroExpression(2), new NumeroExpression(3)); assertEquals(5.0, e.interpretar(Map.of()), 0.001); }
    @Test void subtracaoInterpretaOperacao() { Expressao e = new SubtracaoExpression(new NumeroExpression(5), new NumeroExpression(3)); assertEquals(2.0, e.interpretar(Map.of()), 0.001); }
    @Test void multiplicacaoInterpretaOperacao() { Expressao e = new MultiplicacaoExpression(new NumeroExpression(5), new NumeroExpression(3)); assertEquals(15.0, e.interpretar(Map.of()), 0.001); }
    @Test void divisaoInterpretaOperacao() { Expressao e = new DivisaoExpression(new NumeroExpression(10), new NumeroExpression(2)); assertEquals(5.0, e.interpretar(Map.of()), 0.001); }
    @Test void interpretadorMontaSomaTextual() { Expressao e = new InterpretadorRegra().interpretar("subtotal + taxa"); assertEquals(13.0, e.interpretar(Map.of("subtotal", 10.0, "taxa", 3.0)), 0.001); }
}
