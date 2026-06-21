package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.atendimento.facade.TotemFacade;
import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.dominio.montagem.decorator.HamburguerBase;
import hamburgueria.financeiro.strategy.CalculadoraPreco;
import hamburgueria.financeiro.strategy.PrecoHappyHour;
import hamburgueria.financeiro.strategy.PrecoPadrao;
import static org.junit.jupiter.api.Assertions.*;

class FacadeTest {
    private TotemFacade totem() { return new TotemFacade(new Carrinho(), new CalculadoraPreco(new PrecoPadrao())); }
    @Test void facadeAdicionaItem() { TotemFacade t = totem(); t.adicionarAoPedido(new HamburguerBase("X", 20)); assertEquals(1, t.itensNoPedido()); }
    @Test void facadeFechaPedido() { TotemFacade t = totem(); t.adicionarAoPedido(new HamburguerBase("X", 20)); assertEquals(20.0, t.fecharPedido(), 0.001); }
    @Test void facadeAplicaPolitica() { TotemFacade t = new TotemFacade(new Carrinho(), new CalculadoraPreco(new PrecoHappyHour())); t.adicionarAoPedido(new HamburguerBase("X", 20)); assertEquals(18.0, t.fecharPedido(), 0.001); }
    @Test void facadeNaoFechaPedidoVazio() { assertThrows(IllegalStateException.class, () -> totem().fecharPedido()); }
    @Test void facadeContaZeroInicial() { assertEquals(0, totem().itensNoPedido()); }
    @Test void facadeAdicionaDoisItens() { TotemFacade t = totem(); t.adicionarAoPedido(new HamburguerBase("A", 10)); t.adicionarAoPedido(new HamburguerBase("B", 10)); assertEquals(2, t.itensNoPedido()); }
    @Test void facadeTotalizaDoisItens() { TotemFacade t = totem(); t.adicionarAoPedido(new HamburguerBase("A", 10)); t.adicionarAoPedido(new HamburguerBase("B", 15)); assertEquals(25.0, t.fecharPedido(), 0.001); }
}
