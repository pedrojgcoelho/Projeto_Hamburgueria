package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.atendimento.chain.ContextoPedido;
import hamburgueria.atendimento.chain.ResultadoValidacao;
import hamburgueria.atendimento.chain.ValidadorCarrinho;
import hamburgueria.atendimento.chain.ValidadorCliente;
import hamburgueria.atendimento.chain.ValidadorPagamento;
import hamburgueria.atendimento.chain.ValidadorPedido;
import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.financeiro.bridge.Pagamento;
import static org.junit.jupiter.api.Assertions.*;

class ChainTest {
    private ValidadorPedido cadeia() { ValidadorPedido c = new ValidadorCliente(); c.ligarCom(new ValidadorCarrinho()).ligarCom(new ValidadorPagamento()); return c; }
    @Test void cadeiaAprovaPedidoValido() { assertTrue(cadeia().validar(new ContextoPedido("Ana", 1, true)).isValido()); }
    @Test void cadeiaReprovaClienteVazio() { assertFalse(cadeia().validar(new ContextoPedido("", 1, true)).isValido()); }
    @Test void cadeiaReprovaCarrinhoVazio() { assertEquals("Carrinho vazio", cadeia().validar(new ContextoPedido("Ana", 0, true)).getMensagem()); }
    @Test void cadeiaReprovaPagamentoPendente() { assertEquals("Pagamento pendente", cadeia().validar(new ContextoPedido("Ana", 1, false)).getMensagem()); }
    @Test void resultadoOkTemMensagemOk() { assertEquals("OK", ResultadoValidacao.ok().getMensagem()); }
    @Test void resultadoErroNaoEhValido() { assertFalse(ResultadoValidacao.erro("erro").isValido()); }
    @Test void contextoMantemCliente() { assertEquals("Ana", new ContextoPedido("Ana", 1, true).getCliente()); }
}
