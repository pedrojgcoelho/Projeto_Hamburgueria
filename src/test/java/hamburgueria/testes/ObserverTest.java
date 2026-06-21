package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.atendimento.observer.MonitorCozinha;
import hamburgueria.atendimento.observer.PainelCliente;
import hamburgueria.atendimento.observer.PedidoObservavel;
import static org.junit.jupiter.api.Assertions.*;

class ObserverTest {
    @Test void pedidoAdicionaObservador() { PedidoObservavel p = new PedidoObservavel("P1"); p.adicionarObservador(new PainelCliente()); assertEquals(1, p.quantidadeObservadores()); }
    @Test void painelRecebeAtualizacao() { PedidoObservavel p = new PedidoObservavel("P1"); PainelCliente painel = new PainelCliente(); p.adicionarObservador(painel); p.alterarStatus("PRONTO"); assertEquals("P1:PRONTO", painel.getUltimaMensagem()); }
    @Test void monitorRecebeAtualizacao() { PedidoObservavel p = new PedidoObservavel("P1"); MonitorCozinha monitor = new MonitorCozinha(); p.adicionarObservador(monitor); p.alterarStatus("PREPARO"); assertEquals("PREPARO->P1", monitor.getPedidoAtual()); }
    @Test void pedidoAtualizaStatusInterno() { PedidoObservavel p = new PedidoObservavel("P1"); p.alterarStatus("PRONTO"); assertEquals("PRONTO", p.getStatus()); }
    @Test void observadorNuloGeraErro() { assertThrows(IllegalArgumentException.class, () -> new PedidoObservavel("P1").adicionarObservador(null)); }
    @Test void painelIniciaSemMensagem() { assertEquals("", new PainelCliente().getUltimaMensagem()); }
    @Test void monitorIniciaSemPedido() { assertEquals("", new MonitorCozinha().getPedidoAtual()); }
}
