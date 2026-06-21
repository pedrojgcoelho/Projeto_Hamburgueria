package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.atendimento.chain.ContextoPedido;
import hamburgueria.atendimento.chain.ResultadoValidacao;
import hamburgueria.atendimento.chain.ValidadorCarrinho;
import hamburgueria.atendimento.chain.ValidadorCliente;
import hamburgueria.atendimento.chain.ValidadorPagamento;
import hamburgueria.atendimento.facade.TotemFacade;
import hamburgueria.atendimento.mediator.Atendente;
import hamburgueria.atendimento.mediator.Chapeiro;
import hamburgueria.atendimento.mediator.Entregador;
import hamburgueria.atendimento.mediator.MediadorLoja;
import hamburgueria.atendimento.observer.MonitorCozinha;
import hamburgueria.atendimento.observer.PainelCliente;
import hamburgueria.atendimento.observer.PedidoObservavel;
import hamburgueria.atendimento.state.EstadoEmEntrega;
import hamburgueria.atendimento.state.EstadoEmPreparo;
import hamburgueria.atendimento.state.EstadoFinalizado;
import hamburgueria.atendimento.state.EstadoPronto;
import hamburgueria.atendimento.state.EstadoRecebido;
import hamburgueria.atendimento.state.PedidoAtendimento;
import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.dominio.montagem.decorator.Hamburguer;
import hamburgueria.dominio.montagem.decorator.HamburguerBase;
import hamburgueria.financeiro.bridge.Pagamento;
import hamburgueria.financeiro.strategy.CalculadoraPreco;
import hamburgueria.financeiro.strategy.PrecoHappyHour;
import hamburgueria.financeiro.strategy.PrecoPadrao;
import static org.junit.jupiter.api.Assertions.*;

class AtendimentoMaisTest {
    private Hamburguer burger() { return new HamburguerBase("X", 20); }

    @Test void contextoRetornaCliente() { assertEquals("Ana", new ContextoPedido("Ana", 1, true).getCliente()); }
    @Test void contextoRetornaQuantidade() { assertEquals(2, new ContextoPedido("Ana", 2, true).getQuantidadeItens()); }
    @Test void contextoRetornaPagamentoConfirmado() { assertTrue(new ContextoPedido("Ana", 1, true).isPagamentoConfirmado()); }
    @Test void contextoRetornaPagamentoPendente() { assertFalse(new ContextoPedido("Ana", 1, false).isPagamentoConfirmado()); }
    @Test void resultadoOkEhValido() { assertTrue(ResultadoValidacao.ok().isValido()); }
    @Test void resultadoOkMensagemPadrao() { assertEquals("OK", ResultadoValidacao.ok().getMensagem()); }
    @Test void resultadoErroNaoEhValido() { assertFalse(ResultadoValidacao.erro("erro").isValido()); }
    @Test void resultadoErroRetornaMensagem() { assertEquals("erro", ResultadoValidacao.erro("erro").getMensagem()); }
    @Test void validadorClienteAprovaCliente() { assertTrue(new ValidadorCliente().validar(new ContextoPedido("Ana", 1, true)).isValido()); }
    @Test void validadorClienteReprovaClienteNulo() { assertEquals("Cliente obrigatorio", new ValidadorCliente().validar(new ContextoPedido(null, 1, true)).getMensagem()); }
    @Test void validadorClienteReprovaClienteVazio() { assertEquals("Cliente obrigatorio", new ValidadorCliente().validar(new ContextoPedido(" ", 1, true)).getMensagem()); }
    @Test void validadorCarrinhoAprovaItem() { assertTrue(new ValidadorCarrinho().validar(new ContextoPedido("Ana", 1, true)).isValido()); }
    @Test void validadorCarrinhoReprovaZeroItem() { assertEquals("Carrinho vazio", new ValidadorCarrinho().validar(new ContextoPedido("Ana", 0, true)).getMensagem()); }
    @Test void validadorCarrinhoReprovaItemNegativo() { assertEquals("Carrinho vazio", new ValidadorCarrinho().validar(new ContextoPedido("Ana", -1, true)).getMensagem()); }
    @Test void validadorPagamentoAprovaPagamento() { assertTrue(new ValidadorPagamento().validar(new ContextoPedido("Ana", 1, true)).isValido()); }
    @Test void validadorPagamentoReprovaPagamentoPendente() { assertEquals("Pagamento pendente", new ValidadorPagamento().validar(new ContextoPedido("Ana", 1, false)).getMensagem()); }
    @Test void cadeiaRetornaProximoLigado() { ValidadorCliente a = new ValidadorCliente(); ValidadorCarrinho b = new ValidadorCarrinho(); assertSame(b, a.ligarCom(b)); }
    @Test void cadeiaAprovaPedidoCompleto() { ValidadorCliente a = new ValidadorCliente(); a.ligarCom(new ValidadorCarrinho()).ligarCom(new ValidadorPagamento()); assertTrue(a.validar(new ContextoPedido("Ana", 1, true)).isValido()); }
    @Test void cadeiaParaNoClienteInvalido() { ValidadorCliente a = new ValidadorCliente(); a.ligarCom(new ValidadorCarrinho()).ligarCom(new ValidadorPagamento()); assertEquals("Cliente obrigatorio", a.validar(new ContextoPedido("", 0, false)).getMensagem()); }
    @Test void cadeiaParaNoCarrinhoInvalido() { ValidadorCliente a = new ValidadorCliente(); a.ligarCom(new ValidadorCarrinho()).ligarCom(new ValidadorPagamento()); assertEquals("Carrinho vazio", a.validar(new ContextoPedido("Ana", 0, false)).getMensagem()); }
    @Test void cadeiaParaNoPagamentoInvalido() { ValidadorCliente a = new ValidadorCliente(); a.ligarCom(new ValidadorCarrinho()).ligarCom(new ValidadorPagamento()); assertEquals("Pagamento pendente", a.validar(new ContextoPedido("Ana", 1, false)).getMensagem()); }
    @Test void pedidoNaoAceitaCodigoNulo() { assertThrows(IllegalArgumentException.class, () -> new PedidoAtendimento(null)); }
    @Test void pedidoNaoAceitaCodigoVazio() { assertThrows(IllegalArgumentException.class, () -> new PedidoAtendimento(" ")); }
    @Test void pedidoIniciaRecebido() { assertEquals("RECEBIDO", new PedidoAtendimento("P1").getStatus()); }
    @Test void pedidoRetornaCodigo() { assertEquals("P1", new PedidoAtendimento("P1").getCodigo()); }
    @Test void pedidoAvancaParaPreparo() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); assertEquals("EM_PREPARO", p.getStatus()); }
    @Test void pedidoAvancaParaPronto() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); p.avancar(); assertEquals("PRONTO", p.getStatus()); }
    @Test void pedidoAvancaParaEntrega() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); p.avancar(); p.avancar(); assertEquals("EM_ENTREGA", p.getStatus()); }
    @Test void pedidoAvancaParaEntregue() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); p.avancar(); p.avancar(); p.avancar(); assertEquals("ENTREGUE", p.getStatus()); }
    @Test void pedidoCancelaRecebido() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.cancelar(); assertEquals("CANCELADO", p.getStatus()); }
    @Test void pedidoNaoCancelaEmPreparo() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); assertThrows(IllegalStateException.class, () -> p.cancelar()); }
    @Test void pedidoNaoCancelaPronto() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); p.avancar(); assertThrows(IllegalStateException.class, () -> p.cancelar()); }
    @Test void pedidoNaoCancelaEmEntrega() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.avancar(); p.avancar(); p.avancar(); assertThrows(IllegalStateException.class, () -> p.cancelar()); }
    @Test void pedidoFinalizadoNaoAvanca() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.cancelar(); assertThrows(IllegalStateException.class, () -> p.avancar()); }
    @Test void pedidoFinalizadoNaoCancela() { PedidoAtendimento p = new PedidoAtendimento("P1"); p.cancelar(); assertThrows(IllegalStateException.class, () -> p.cancelar()); }
    @Test void estadoRecebidoRetornaNome() { assertEquals("RECEBIDO", new EstadoRecebido().nome()); }
    @Test void estadoPreparoRetornaNome() { assertEquals("EM_PREPARO", new EstadoEmPreparo().nome()); }
    @Test void estadoProntoRetornaNome() { assertEquals("PRONTO", new EstadoPronto().nome()); }
    @Test void estadoEntregaRetornaNome() { assertEquals("EM_ENTREGA", new EstadoEmEntrega().nome()); }
    @Test void estadoFinalizadoRetornaNomeCustomizado() { assertEquals("OK", new EstadoFinalizado("OK").nome()); }
    @Test void observavelIniciaAberto() { assertEquals("ABERTO", new PedidoObservavel("P1").getStatus()); }
    @Test void observavelAdicionaObservador() { PedidoObservavel p = new PedidoObservavel("P1"); p.adicionarObservador(new PainelCliente()); assertEquals(1, p.quantidadeObservadores()); }
    @Test void observavelNaoAceitaObservadorNulo() { assertThrows(IllegalArgumentException.class, () -> new PedidoObservavel("P1").adicionarObservador(null)); }
    @Test void observavelAlteraStatus() { PedidoObservavel p = new PedidoObservavel("P1"); p.alterarStatus("PRONTO"); assertEquals("PRONTO", p.getStatus()); }
    @Test void painelRecebeAtualizacao() { PainelCliente painel = new PainelCliente(); painel.atualizar("P1", "PRONTO"); assertEquals("P1:PRONTO", painel.getUltimaMensagem()); }
    @Test void monitorRecebeAtualizacao() { MonitorCozinha monitor = new MonitorCozinha(); monitor.atualizar("P1", "PRONTO"); assertEquals("PRONTO->P1", monitor.getPedidoAtual()); }
    @Test void observavelNotificaPainel() { PedidoObservavel p = new PedidoObservavel("P1"); PainelCliente painel = new PainelCliente(); p.adicionarObservador(painel); p.alterarStatus("PRONTO"); assertEquals("P1:PRONTO", painel.getUltimaMensagem()); }
    @Test void observavelNotificaMonitor() { PedidoObservavel p = new PedidoObservavel("P1"); MonitorCozinha monitor = new MonitorCozinha(); p.adicionarObservador(monitor); p.alterarStatus("PRONTO"); assertEquals("PRONTO->P1", monitor.getPedidoAtual()); }
    @Test void mediadorRegistraMensagem() { MediadorLoja m = new MediadorLoja(); new Atendente("Ana", m).enviar("pedido novo"); assertEquals(1, m.getMensagens().size()); }
    @Test void mediadorMensagemContemFuncionario() { MediadorLoja m = new MediadorLoja(); new Atendente("Ana", m).enviar("pedido novo"); assertEquals("Ana:pedido novo", m.getMensagens().get(0)); }
    @Test void mediadorMensagensSaoImutaveis() { assertThrows(UnsupportedOperationException.class, () -> new MediadorLoja().getMensagens().add("x")); }
    @Test void atendenteRetornaNome() { assertEquals("Ana", new Atendente("Ana", new MediadorLoja()).getNome()); }
    @Test void atendenteRetornaFuncao() { assertEquals("ATENDIMENTO", new Atendente("Ana", new MediadorLoja()).funcao()); }
    @Test void chapeiroRetornaFuncao() { assertEquals("CHAPA", new Chapeiro("Beto", new MediadorLoja()).funcao()); }
    @Test void entregadorRetornaFuncao() { assertEquals("ENTREGA", new Entregador("Carlos", new MediadorLoja()).funcao()); }
    @Test void totemIniciaSemItens() { assertEquals(0, new TotemFacade(new Carrinho(), new CalculadoraPreco(new PrecoPadrao())).itensNoPedido()); }
    @Test void totemAdicionaItem() { TotemFacade t = new TotemFacade(new Carrinho(), new CalculadoraPreco(new PrecoPadrao())); t.adicionarAoPedido(burger()); assertEquals(1, t.itensNoPedido()); }
    @Test void totemNaoFechaVazio() { assertThrows(IllegalStateException.class, () -> new TotemFacade(new Carrinho(), new CalculadoraPreco(new PrecoPadrao())).fecharPedido()); }
    @Test void totemFechaComPrecoPadrao() { TotemFacade t = new TotemFacade(new Carrinho(), new CalculadoraPreco(new PrecoPadrao())); t.adicionarAoPedido(burger()); assertEquals(20.0, t.fecharPedido(), 0.001); }
    @Test void totemFechaComHappyHour() { TotemFacade t = new TotemFacade(new Carrinho(), new CalculadoraPreco(new PrecoHappyHour())); t.adicionarAoPedido(burger()); assertEquals(18.0, t.fecharPedido(), 0.001); }
}
