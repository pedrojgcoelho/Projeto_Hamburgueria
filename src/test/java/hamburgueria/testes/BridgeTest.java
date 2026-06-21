package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.financeiro.bridge.PagamentoCartao;
import hamburgueria.financeiro.bridge.PagamentoPix;
import hamburgueria.financeiro.bridge.ProcessadorLocal;
import hamburgueria.financeiro.bridge.ProcessadorOnline;
import static org.junit.jupiter.api.Assertions.*;

class BridgeTest {
    @Test void pixUsaProcessadorLocal() { assertEquals("LOCAL-PIX-20.00", new PagamentoPix(new ProcessadorLocal()).pagar(20)); }
    @Test void cartaoUsaProcessadorLocal() { assertEquals("LOCAL-CARTAO-20.00", new PagamentoCartao(new ProcessadorLocal()).pagar(20)); }
    @Test void pixUsaProcessadorOnline() { assertEquals("ONLINE-PIX-APROVADO", new PagamentoPix(new ProcessadorOnline()).pagar(20)); }
    @Test void cartaoUsaProcessadorOnline() { assertEquals("ONLINE-CARTAO-APROVADO", new PagamentoCartao(new ProcessadorOnline()).pagar(20)); }
    @Test void pagamentoNaoAceitaValorZero() { assertThrows(IllegalArgumentException.class, () -> new PagamentoPix(new ProcessadorLocal()).pagar(0)); }
    @Test void pagamentoNaoAceitaProcessadorNulo() { assertThrows(IllegalArgumentException.class, () -> new PagamentoPix(null)); }
    @Test void processadorOnlineIgnoraValorNoRetorno() { assertEquals("ONLINE-PIX-APROVADO", new ProcessadorOnline().processar("PIX", 33)); }
}
