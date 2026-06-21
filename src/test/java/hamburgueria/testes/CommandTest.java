package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.carrinho.command.AdicionarProdutoCommand;
import hamburgueria.dominio.carrinho.command.LimparCarrinhoCommand;
import hamburgueria.dominio.carrinho.command.RemoverUltimoProdutoCommand;
import hamburgueria.dominio.carrinho.memento.HistoricoCarrinho;
import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.dominio.montagem.decorator.Hamburguer;
import hamburgueria.dominio.montagem.decorator.HamburguerBase;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    private Hamburguer burger() { return new HamburguerBase("X", 20); }
    @Test void comandoAdicionaItem() { Carrinho c = new Carrinho(); new AdicionarProdutoCommand(c, burger()).executar(); assertEquals(1, c.tamanho()); }
    @Test void comandoAdicionarDesfaz() { Carrinho c = new Carrinho(); AdicionarProdutoCommand cmd = new AdicionarProdutoCommand(c, burger()); cmd.executar(); cmd.desfazer(); assertEquals(0, c.tamanho()); }
    @Test void comandoRemoverDesfaz() { Carrinho c = new Carrinho(); c.adicionar(burger()); RemoverUltimoProdutoCommand cmd = new RemoverUltimoProdutoCommand(c); cmd.executar(); cmd.desfazer(); assertEquals(1, c.tamanho()); }
    @Test void comandoLimparEsvaziaCarrinho() { Carrinho c = new Carrinho(); c.adicionar(burger()); new LimparCarrinhoCommand(c).executar(); assertEquals(0, c.tamanho()); }
    @Test void historicoExecutaComando() { Carrinho c = new Carrinho(); HistoricoCarrinho h = new HistoricoCarrinho(); h.executar(new AdicionarProdutoCommand(c, burger())); assertEquals(1, h.tamanho()); }
    @Test void historicoDesfazUltimo() { Carrinho c = new Carrinho(); HistoricoCarrinho h = new HistoricoCarrinho(); h.executar(new AdicionarProdutoCommand(c, burger())); h.desfazerUltimo(); assertEquals(0, c.tamanho()); }
    @Test void historicoNaoDesfazVazio() { assertThrows(IllegalStateException.class, () -> new HistoricoCarrinho().desfazerUltimo()); }
}
