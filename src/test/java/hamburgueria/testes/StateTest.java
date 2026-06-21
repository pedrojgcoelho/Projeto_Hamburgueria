package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.atendimento.state.PedidoAtendimento;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {
    @Test void pedidoIniciaRecebido() { assertEquals("RECEBIDO", new PedidoAtendimento("P1").getStatus()); }
    @Test void pedidoVaiParaPreparo() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); assertEquals("EM_PREPARO", p.getStatus()); }
    @Test void pedidoVaiParaPronto() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); p.avancar(); assertEquals("PRONTO", p.getStatus()); }
    @Test void pedidoVaiParaEntrega() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); p.avancar(); p.avancar(); assertEquals("EM_ENTREGA", p.getStatus()); }
    @Test void pedidoVaiParaEntregue() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); p.avancar(); p.avancar(); p.avancar(); assertEquals("ENTREGUE", p.getStatus()); }
    @Test void pedidoRecebidoPodeCancelar() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.cancelar(); assertEquals("CANCELADO", p.getStatus()); }
    @Test void pedidoEmPreparoNaoPodeCancelar() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); assertThrows(IllegalStateException.class, p::cancelar); }
}
