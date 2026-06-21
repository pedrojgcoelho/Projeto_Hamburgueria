package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.financeiro.adapter.PagamentoJsonAdapter;
import hamburgueria.financeiro.adapter.PedidoPagamento;
import hamburgueria.integracao.entrega.adapter.EntregaLegada;
import hamburgueria.integracao.entrega.adapter.EntregaParceiroAdapter;
import hamburgueria.integracao.entrega.adapter.PedidoEntrega;
import static org.junit.jupiter.api.Assertions.*;

class AdapterTest {
    @Test void pagamentoAdapterGeraJsonComCodigo() { assertTrue(new PagamentoJsonAdapter(new PedidoPagamento("P1", 20)).paraJson().contains("P1")); }
    @Test void pagamentoAdapterGeraJsonComTotal() { assertTrue(new PagamentoJsonAdapter(new PedidoPagamento("P1", 20)).paraJson().contains("20.0")); }
    @Test void pagamentoAdapterNaoAceitaPedidoNulo() { assertThrows(IllegalArgumentException.class, () -> new PagamentoJsonAdapter(null)); }
    @Test void entregaAdapterSolicitaColeta() { EntregaParceiroAdapter a = new EntregaParceiroAdapter(new EntregaLegada()); assertTrue(a.enviar(new PedidoEntrega("P1", "Rua A", 1.2)).startsWith("COLETA:")); }
    @Test void entregaAdapterNaoAceitaServicoNulo() { assertThrows(IllegalArgumentException.class, () -> new EntregaParceiroAdapter(null)); }
    @Test void entregaAdapterNaoAceitaPedidoNulo() { EntregaParceiroAdapter a = new EntregaParceiroAdapter(new EntregaLegada()); assertThrows(IllegalArgumentException.class, () -> a.enviar(null)); }
    @Test void servicoLegadoNaoAceitaPayloadVazio() { assertThrows(IllegalArgumentException.class, () -> new EntregaLegada().solicitarColeta("")); }
}
