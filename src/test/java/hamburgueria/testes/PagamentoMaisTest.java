package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.financeiro.adapter.PagamentoJsonAdapter;
import hamburgueria.financeiro.adapter.PedidoPagamento;
import hamburgueria.financeiro.bridge.PagamentoCartao;
import hamburgueria.financeiro.bridge.PagamentoPix;
import hamburgueria.financeiro.bridge.ProcessadorLocal;
import hamburgueria.financeiro.bridge.ProcessadorOnline;
import hamburgueria.financeiro.interpreter.DivisaoExpression;
import hamburgueria.financeiro.interpreter.InterpretadorRegra;
import hamburgueria.financeiro.interpreter.MultiplicacaoExpression;
import hamburgueria.financeiro.interpreter.NumeroExpression;
import hamburgueria.financeiro.interpreter.SomaExpression;
import hamburgueria.financeiro.interpreter.SubtracaoExpression;
import hamburgueria.financeiro.interpreter.VariavelExpression;
import hamburgueria.financeiro.proxy.GatewayReal;
import hamburgueria.financeiro.proxy.GatewaySeguroProxy;
import hamburgueria.financeiro.strategy.CalculadoraPreco;
import hamburgueria.financeiro.strategy.PrecoCupomFixo;
import hamburgueria.financeiro.strategy.PrecoHappyHour;
import hamburgueria.financeiro.strategy.PrecoPadrao;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PagamentoMaisTest {
    @Test void precoPadraoMantemSubtotalZero() { assertEquals(0.0, new PrecoPadrao().aplicar(0), 0.001); }
    @Test void precoPadraoMantemSubtotalPositivo() { assertEquals(30.0, new PrecoPadrao().aplicar(30), 0.001); }
    @Test void precoPadraoNaoAceitaSubtotalNegativo() { assertThrows(IllegalArgumentException.class, () -> new PrecoPadrao().aplicar(-1)); }
    @Test void happyHourAplicaDezPorCento() { assertEquals(90.0, new PrecoHappyHour().aplicar(100), 0.001); }
    @Test void happyHourMantemZero() { assertEquals(0.0, new PrecoHappyHour().aplicar(0), 0.001); }
    @Test void happyHourNaoAceitaSubtotalNegativo() { assertThrows(IllegalArgumentException.class, () -> new PrecoHappyHour().aplicar(-1)); }
    @Test void cupomNaoAceitaDescontoNegativo() { assertThrows(IllegalArgumentException.class, () -> new PrecoCupomFixo(-1)); }
    @Test void cupomSubtraiDesconto() { assertEquals(15.0, new PrecoCupomFixo(5).aplicar(20), 0.001); }
    @Test void cupomNaoRetornaValorNegativo() { assertEquals(0.0, new PrecoCupomFixo(50).aplicar(20), 0.001); }
    @Test void cupomNaoAceitaSubtotalNegativo() { assertThrows(IllegalArgumentException.class, () -> new PrecoCupomFixo(5).aplicar(-1)); }
    @Test void calculadoraNaoAceitaPoliticaNulaNoConstrutor() { assertThrows(IllegalArgumentException.class, () -> new CalculadoraPreco(null)); }
    @Test void calculadoraNaoAceitaPoliticaNulaNoSetter() { CalculadoraPreco c = new CalculadoraPreco(new PrecoPadrao()); assertThrows(IllegalArgumentException.class, () -> c.setPolitica(null)); }
    @Test void calculadoraUsaPoliticaInicial() { assertEquals(50.0, new CalculadoraPreco(new PrecoPadrao()).calcular(50), 0.001); }
    @Test void calculadoraTrocaPolitica() { CalculadoraPreco c = new CalculadoraPreco(new PrecoPadrao()); c.setPolitica(new PrecoHappyHour()); assertEquals(45.0, c.calcular(50), 0.001); }
    @Test void numeroExpressionRetornaValor() { assertEquals(8.0, new NumeroExpression(8).interpretar(Map.of()), 0.001); }
    @Test void variavelExpressionRetornaValorDoContexto() { assertEquals(12.0, new VariavelExpression("total").interpretar(Map.of("total", 12.0)), 0.001); }
    @Test void variavelExpressionLancaErroSemVariavel() { assertThrows(IllegalArgumentException.class, () -> new VariavelExpression("x").interpretar(Map.of())); }
    @Test void somaExpressionSomaNumeros() { assertEquals(9.0, new SomaExpression(new NumeroExpression(4), new NumeroExpression(5)).interpretar(Map.of()), 0.001); }
    @Test void subtracaoExpressionSubtraiNumeros() { assertEquals(3.0, new SubtracaoExpression(new NumeroExpression(8), new NumeroExpression(5)).interpretar(Map.of()), 0.001); }
    @Test void multiplicacaoExpressionMultiplicaNumeros() { assertEquals(20.0, new MultiplicacaoExpression(new NumeroExpression(4), new NumeroExpression(5)).interpretar(Map.of()), 0.001); }
    @Test void divisaoExpressionDivideNumeros() { assertEquals(4.0, new DivisaoExpression(new NumeroExpression(20), new NumeroExpression(5)).interpretar(Map.of()), 0.001); }
    @Test void divisaoExpressionNaoAceitaZero() { assertThrows(ArithmeticException.class, () -> new DivisaoExpression(new NumeroExpression(20), new NumeroExpression(0)).interpretar(Map.of())); }
    @Test void interpretadorNaoAceitaRegraNula() { assertThrows(IllegalArgumentException.class, () -> new InterpretadorRegra().interpretar(null)); }
    @Test void interpretadorNaoAceitaRegraVazia() { assertThrows(IllegalArgumentException.class, () -> new InterpretadorRegra().interpretar(" ")); }
    @Test void interpretadorNumeroIsolado() { assertEquals(7.0, new InterpretadorRegra().interpretar("7").interpretar(Map.of()), 0.001); }
    @Test void interpretadorVariavelIsolada() { assertEquals(30.0, new InterpretadorRegra().interpretar("total").interpretar(Map.of("total", 30.0)), 0.001); }
    @Test void interpretadorSoma() { assertEquals(12.0, new InterpretadorRegra().interpretar("5 + 7").interpretar(Map.of()), 0.001); }
    @Test void interpretadorSubtracao() { assertEquals(3.0, new InterpretadorRegra().interpretar("10 - 7").interpretar(Map.of()), 0.001); }
    @Test void interpretadorMultiplicacao() { assertEquals(42.0, new InterpretadorRegra().interpretar("6 * 7").interpretar(Map.of()), 0.001); }
    @Test void interpretadorDivisao() { assertEquals(5.0, new InterpretadorRegra().interpretar("20 / 4").interpretar(Map.of()), 0.001); }
    @Test void interpretadorSomaComVariavel() { assertEquals(35.0, new InterpretadorRegra().interpretar("total + 5").interpretar(Map.of("total", 30.0)), 0.001); }
    @Test void interpretadorNaoAceitaOperadorInvalido() { assertThrows(IllegalArgumentException.class, () -> new InterpretadorRegra().interpretar("1 % 2")); }
    @Test void interpretadorNaoAceitaRegraComDuasPartes() { assertThrows(IllegalArgumentException.class, () -> new InterpretadorRegra().interpretar("1 +")); }
    @Test void gatewayRealAutorizaValorComToken() { assertTrue(new GatewayReal().autorizar(10, "abc")); }
    @Test void gatewayRealRejeitaValorZero() { assertFalse(new GatewayReal().autorizar(0, "abc")); }
    @Test void gatewayRealRejeitaTokenNulo() { assertFalse(new GatewayReal().autorizar(10, null)); }
    @Test void gatewayRealRejeitaTokenVazio() { assertFalse(new GatewayReal().autorizar(10, " ")); }
    @Test void proxyAutorizaTokenCorreto() { assertTrue(new GatewaySeguroProxy(new GatewayReal(), "seguro").autorizar(10, "seguro")); }
    @Test void proxyRejeitaTokenErrado() { assertFalse(new GatewaySeguroProxy(new GatewayReal(), "seguro").autorizar(10, "errado")); }
    @Test void proxyContaTentativaValida() { GatewaySeguroProxy p = new GatewaySeguroProxy(new GatewayReal(), "seguro"); p.autorizar(10, "seguro"); assertEquals(1, p.getTentativas()); }
    @Test void proxyContaTentativaInvalida() { GatewaySeguroProxy p = new GatewaySeguroProxy(new GatewayReal(), "seguro"); p.autorizar(10, "errado"); assertEquals(1, p.getTentativas()); }
    @Test void proxyRepassaRejeicaoDoGatewayReal() { assertFalse(new GatewaySeguroProxy(new GatewayReal(), "seguro").autorizar(0, "seguro")); }
    @Test void pagamentoPixNaoAceitaProcessadorNulo() { assertThrows(IllegalArgumentException.class, () -> new PagamentoPix(null)); }
    @Test void pagamentoCartaoNaoAceitaProcessadorNulo() { assertThrows(IllegalArgumentException.class, () -> new PagamentoCartao(null)); }
    @Test void pagamentoPixUsaTipoPix() { assertEquals("LOCAL-PIX-10.00", new PagamentoPix(new ProcessadorLocal()).pagar(10)); }
    @Test void pagamentoCartaoUsaTipoCartao() { assertEquals("LOCAL-CARTAO-10.00", new PagamentoCartao(new ProcessadorLocal()).pagar(10)); }
    @Test void pagamentoOnlineRetornaAprovado() { assertEquals("ONLINE-PIX-APROVADO", new PagamentoPix(new ProcessadorOnline()).pagar(10)); }
    @Test void pagamentoNaoAceitaValorZero() { assertThrows(IllegalArgumentException.class, () -> new PagamentoPix(new ProcessadorLocal()).pagar(0)); }
    @Test void pagamentoNaoAceitaValorNegativo() { assertThrows(IllegalArgumentException.class, () -> new PagamentoPix(new ProcessadorLocal()).pagar(-1)); }
    @Test void processadorLocalUsaDuasCasas() { assertEquals("LOCAL-PIX-10.50", new ProcessadorLocal().processar("PIX", 10.5)); }
    @Test void processadorOnlineIgnoraValorNoTexto() { assertEquals("ONLINE-CARTAO-APROVADO", new ProcessadorOnline().processar("CARTAO", 99)); }
    @Test void pedidoPagamentoRetornaCodigo() { assertEquals("P1", new PedidoPagamento("P1", 20).getCodigo()); }
    @Test void pedidoPagamentoRetornaTotal() { assertEquals(20.0, new PedidoPagamento("P1", 20).getTotal(), 0.001); }
    @Test void jsonAdapterNaoAceitaPedidoNulo() { assertThrows(IllegalArgumentException.class, () -> new PagamentoJsonAdapter(null)); }
    @Test void jsonAdapterGeraCodigo() { assertTrue(new PagamentoJsonAdapter(new PedidoPagamento("P1", 20)).paraJson().contains("P1")); }
    @Test void jsonAdapterGeraTotal() { assertTrue(new PagamentoJsonAdapter(new PedidoPagamento("P1", 20)).paraJson().contains("20.0")); }
    @Test void jsonAdapterGeraEstruturaCompleta() { assertEquals("{\"pedido\":\"P1\",\"total\":20.0}", new PagamentoJsonAdapter(new PedidoPagamento("P1", 20)).paraJson()); }
}
