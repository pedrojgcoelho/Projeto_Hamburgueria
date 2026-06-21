package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.carrinho.command.AdicionarProdutoCommand;
import hamburgueria.dominio.carrinho.command.LimparCarrinhoCommand;
import hamburgueria.dominio.carrinho.command.RemoverUltimoProdutoCommand;
import hamburgueria.dominio.carrinho.memento.CarrinhoMemento;
import hamburgueria.dominio.carrinho.memento.HistoricoCarrinho;
import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.dominio.montagem.builder.HamburguerBuilder;
import hamburgueria.dominio.montagem.builder.HamburguerPersonalizado;
import hamburgueria.dominio.montagem.decorator.BaconDecorator;
import hamburgueria.dominio.montagem.decorator.CebolaCaramelizadaDecorator;
import hamburgueria.dominio.montagem.decorator.Hamburguer;
import hamburgueria.dominio.montagem.decorator.HamburguerBase;
import hamburgueria.dominio.montagem.decorator.MolhoEspecialDecorator;
import hamburgueria.dominio.montagem.decorator.QueijoExtraDecorator;
import hamburgueria.dominio.montagem.prototype.ComboCliente;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MontagemMaisTest {
    private Hamburguer base() { return new HamburguerBase("X", 20); }

    @Test void baseNaoAceitaNomeNulo() { assertThrows(IllegalArgumentException.class, () -> new HamburguerBase(null, 10)); }
    @Test void baseNaoAceitaNomeVazio() { assertThrows(IllegalArgumentException.class, () -> new HamburguerBase(" ", 10)); }
    @Test void baseNaoAceitaPrecoNegativo() { assertThrows(IllegalArgumentException.class, () -> new HamburguerBase("X", -1)); }
    @Test void baseRetornaNome() { assertEquals("X", base().getNome()); }
    @Test void baseRetornaDescricaoIgualAoNome() { assertEquals("X", base().getDescricao()); }
    @Test void baseAceitaPrecoZero() { assertEquals(0.0, new HamburguerBase("Cortesia", 0).getPreco(), 0.001); }
    @Test void baconNaoAceitaHamburguerNulo() { assertThrows(IllegalArgumentException.class, () -> new BaconDecorator(null)); }
    @Test void queijoNaoAceitaHamburguerNulo() { assertThrows(IllegalArgumentException.class, () -> new QueijoExtraDecorator(null)); }
    @Test void cebolaNaoAceitaHamburguerNulo() { assertThrows(IllegalArgumentException.class, () -> new CebolaCaramelizadaDecorator(null)); }
    @Test void molhoNaoAceitaHamburguerNulo() { assertThrows(IllegalArgumentException.class, () -> new MolhoEspecialDecorator(null)); }
    @Test void baconMantemNomeOriginal() { assertEquals("X", new BaconDecorator(base()).getNome()); }
    @Test void queijoMantemNomeOriginal() { assertEquals("X", new QueijoExtraDecorator(base()).getNome()); }
    @Test void cebolaMantemNomeOriginal() { assertEquals("X", new CebolaCaramelizadaDecorator(base()).getNome()); }
    @Test void molhoMantemNomeOriginal() { assertEquals("X", new MolhoEspecialDecorator(base()).getNome()); }
    @Test void baconAcrescentaPreco() { assertEquals(24.5, new BaconDecorator(base()).getPreco(), 0.001); }
    @Test void queijoAcrescentaPreco() { assertEquals(23.25, new QueijoExtraDecorator(base()).getPreco(), 0.001); }
    @Test void cebolaAcrescentaPreco() { assertEquals(22.75, new CebolaCaramelizadaDecorator(base()).getPreco(), 0.001); }
    @Test void molhoAcrescentaPreco() { assertEquals(21.8, new MolhoEspecialDecorator(base()).getPreco(), 0.001); }
    @Test void baconAcrescentaDescricao() { assertTrue(new BaconDecorator(base()).getDescricao().contains("bacon crocante")); }
    @Test void queijoAcrescentaDescricao() { assertTrue(new QueijoExtraDecorator(base()).getDescricao().contains("queijo extra")); }
    @Test void cebolaAcrescentaDescricao() { assertTrue(new CebolaCaramelizadaDecorator(base()).getDescricao().contains("cebola caramelizada")); }
    @Test void molhoAcrescentaDescricao() { assertTrue(new MolhoEspecialDecorator(base()).getDescricao().contains("molho da casa")); }
    @Test void decoradoresSomamPrecos() { Hamburguer h = new BaconDecorator(new QueijoExtraDecorator(base())); assertEquals(27.75, h.getPreco(), 0.001); }
    @Test void decoradoresCompoemDescricao() { Hamburguer h = new BaconDecorator(new QueijoExtraDecorator(base())); assertTrue(h.getDescricao().contains("queijo extra")); }
    @Test void builderExigePao() { assertThrows(IllegalStateException.class, () -> new HamburguerBuilder().comProteina("carne").montar()); }
    @Test void builderExigeProteina() { assertThrows(IllegalStateException.class, () -> new HamburguerBuilder().comPao("brioche").montar()); }
    @Test void builderUsaNomePadrao() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").montar(); assertEquals("Burger personalizado", h.getNome()); }
    @Test void builderPermiteNomeCustomizado() { HamburguerPersonalizado h = new HamburguerBuilder().chamado("Do Chef").comPao("brioche").comProteina("carne").montar(); assertEquals("Do Chef", h.getNome()); }
    @Test void builderRegistraPao() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("australiano").comProteina("carne").montar(); assertEquals("australiano", h.getPao()); }
    @Test void builderRegistraProteina() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("frango").montar(); assertEquals("frango", h.getProteina()); }
    @Test void builderUsaQueijoPadrao() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").montar(); assertEquals("sem queijo", h.getQueijo()); }
    @Test void builderRegistraQueijo() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comQueijo("cheddar").montar(); assertEquals("cheddar", h.getQueijo()); }
    @Test void builderAdicionaSalada() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comSalada("alface").montar(); assertEquals(1, h.getSaladas().size()); }
    @Test void builderAdicionaMolho() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comMolho("barbecue").montar(); assertEquals(1, h.getMolhos().size()); }
    @Test void builderCalculaPrecoBase() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").montar(); assertEquals(10.0, h.getPreco(), 0.001); }
    @Test void builderCalculaPrecoComQueijo() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comQueijo("cheddar").montar(); assertEquals(13.0, h.getPreco(), 0.001); }
    @Test void builderCalculaPrecoComSalada() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comSalada("alface").montar(); assertEquals(11.0, h.getPreco(), 0.001); }
    @Test void builderCalculaPrecoComMolho() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comMolho("barbecue").montar(); assertEquals(10.5, h.getPreco(), 0.001); }
    @Test void builderDescricaoIncluiNome() { HamburguerPersonalizado h = new HamburguerBuilder().chamado("Casa").comPao("brioche").comProteina("carne").montar(); assertTrue(h.getDescricao().contains("Casa")); }
    @Test void builderDescricaoIncluiPao() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").montar(); assertTrue(h.getDescricao().contains("brioche")); }
    @Test void saladasSaoImutaveis() { HamburguerPersonalizado h = new HamburguerPersonalizado("X", "p", "c", "q", List.of("alface"), List.of(), 1); assertThrows(UnsupportedOperationException.class, () -> h.getSaladas().add("tomate")); }
    @Test void molhosSaoImutaveis() { HamburguerPersonalizado h = new HamburguerPersonalizado("X", "p", "c", "q", List.of(), List.of("m"), 1); assertThrows(UnsupportedOperationException.class, () -> h.getMolhos().add("n")); }
    @Test void carrinhoIniciaVazio() { assertEquals(0, new Carrinho().tamanho()); }
    @Test void carrinhoTotalInicialZero() { assertEquals(0.0, new Carrinho().total(), 0.001); }
    @Test void carrinhoNaoAceitaItemNulo() { assertThrows(IllegalArgumentException.class, () -> new Carrinho().adicionar(null)); }
    @Test void carrinhoAdicionaUmItem() { Carrinho c = new Carrinho(); c.adicionar(base()); assertEquals(1, c.tamanho()); }
    @Test void carrinhoSomaTotal() { Carrinho c = new Carrinho(); c.adicionar(base()); c.adicionar(new HamburguerBase("Y", 5)); assertEquals(25.0, c.total(), 0.001); }
    @Test void carrinhoRemoveUltimo() { Carrinho c = new Carrinho(); Hamburguer h = base(); c.adicionar(h); assertSame(h, c.removerUltimo()); }
    @Test void carrinhoNaoRemoveVazio() { assertThrows(IllegalStateException.class, () -> new Carrinho().removerUltimo()); }
    @Test void carrinhoLimpaItens() { Carrinho c = new Carrinho(); c.adicionar(base()); c.limpar(); assertEquals(0, c.tamanho()); }
    @Test void carrinhoItensSaoImutaveis() { assertThrows(UnsupportedOperationException.class, () -> new Carrinho().getItens().add(base())); }
    @Test void mementoSalvaQuantidade() { Carrinho c = new Carrinho(); c.adicionar(base()); assertEquals(1, c.salvar().quantidadeSalva()); }
    @Test void carrinhoRestauraMemento() { Carrinho c = new Carrinho(); c.adicionar(base()); CarrinhoMemento m = c.salvar(); c.limpar(); c.restaurar(m); assertEquals(1, c.tamanho()); }
    @Test void carrinhoNaoRestauraMementoNulo() { assertThrows(IllegalArgumentException.class, () -> new Carrinho().restaurar(null)); }
    @Test void comboNaoAceitaHamburguerNulo() { assertThrows(IllegalArgumentException.class, () -> new ComboCliente("C", null, "refri", "batata")); }
    @Test void comboRetornaNome() { assertEquals("Combo", new ComboCliente("Combo", base(), "refri", "batata").getNome()); }
    @Test void comboRetornaBebida() { assertEquals("refri", new ComboCliente("Combo", base(), "refri", "batata").getBebida()); }
    @Test void comboRetornaAcompanhamento() { assertEquals("batata", new ComboCliente("Combo", base(), "refri", "batata").getAcompanhamento()); }
    @Test void comboRetornaHamburguer() { Hamburguer h = base(); assertSame(h, new ComboCliente("Combo", h, "refri", "batata").getHamburguer()); }
    @Test void comboSomaPrecoFixo() { assertEquals(32.0, new ComboCliente("Combo", base(), "refri", "batata").getPreco(), 0.001); }
    @Test void comboCopiaNovaInstancia() { ComboCliente c = new ComboCliente("Combo", base(), "refri", "batata"); assertNotSame(c, c.copiar()); }
    @Test void comboCopiaMesmoNome() { ComboCliente c = new ComboCliente("Combo", base(), "refri", "batata"); assertEquals("Combo", c.copiar().getNome()); }
    @Test void comandoAdicionarExecuta() { Carrinho c = new Carrinho(); new AdicionarProdutoCommand(c, base()).executar(); assertEquals(1, c.tamanho()); }
    @Test void comandoAdicionarDesfaz() { Carrinho c = new Carrinho(); AdicionarProdutoCommand cmd = new AdicionarProdutoCommand(c, base()); cmd.executar(); cmd.desfazer(); assertEquals(0, c.tamanho()); }
    @Test void comandoAdicionarNaoDesfazSemExecutar() { assertThrows(IllegalStateException.class, () -> new AdicionarProdutoCommand(new Carrinho(), base()).desfazer()); }
    @Test void comandoRemoverExecuta() { Carrinho c = new Carrinho(); c.adicionar(base()); new RemoverUltimoProdutoCommand(c).executar(); assertEquals(0, c.tamanho()); }
    @Test void comandoRemoverDesfaz() { Carrinho c = new Carrinho(); c.adicionar(base()); RemoverUltimoProdutoCommand cmd = new RemoverUltimoProdutoCommand(c); cmd.executar(); cmd.desfazer(); assertEquals(1, c.tamanho()); }
    @Test void comandoRemoverNaoDesfazSemExecutar() { assertThrows(IllegalStateException.class, () -> new RemoverUltimoProdutoCommand(new Carrinho()).desfazer()); }
    @Test void comandoLimparExecuta() { Carrinho c = new Carrinho(); c.adicionar(base()); new LimparCarrinhoCommand(c).executar(); assertEquals(0, c.tamanho()); }
    @Test void comandoLimparDesfaz() { Carrinho c = new Carrinho(); c.adicionar(base()); LimparCarrinhoCommand cmd = new LimparCarrinhoCommand(c); cmd.executar(); cmd.desfazer(); assertEquals(1, c.tamanho()); }
    @Test void comandoLimparNaoDesfazSemExecutar() { assertThrows(IllegalStateException.class, () -> new LimparCarrinhoCommand(new Carrinho()).desfazer()); }
    @Test void historicoNaoAceitaComandoNulo() { assertThrows(IllegalArgumentException.class, () -> new HistoricoCarrinho().executar(null)); }
    @Test void historicoRegistraComando() { HistoricoCarrinho h = new HistoricoCarrinho(); h.executar(new AdicionarProdutoCommand(new Carrinho(), base())); assertEquals(1, h.tamanho()); }
    @Test void historicoDesfazEsvaziaPilha() { HistoricoCarrinho h = new HistoricoCarrinho(); h.executar(new AdicionarProdutoCommand(new Carrinho(), base())); h.desfazerUltimo(); assertEquals(0, h.tamanho()); }
    @Test void historicoNaoDesfazVazio() { assertThrows(IllegalStateException.class, () -> new HistoricoCarrinho().desfazerUltimo()); }
}
