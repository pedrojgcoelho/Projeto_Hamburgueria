package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.catalogo.composite.ItemCardapio;
import hamburgueria.dominio.catalogo.composite.SecaoCardapio;
import hamburgueria.dominio.catalogo.flyweight.BibliotecaImagens;
import hamburgueria.dominio.catalogo.flyweight.ImagemProduto;
import hamburgueria.dominio.catalogo.iterator.CardapioIterator;
import hamburgueria.dominio.catalogo.visitor.ListadorCardapioVisitor;
import hamburgueria.dominio.catalogo.visitor.TotalizadorCardapioVisitor;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class CatalogoMaisTest {
    private ItemCardapio item(String nome, double preco) { return new ItemCardapio(nome, "desc", preco, null); }

    @Test void itemNaoAceitaNomeNulo() { assertThrows(IllegalArgumentException.class, () -> new ItemCardapio(null, "desc", 1, null)); }
    @Test void itemNaoAceitaNomeVazio() { assertThrows(IllegalArgumentException.class, () -> new ItemCardapio(" ", "desc", 1, null)); }
    @Test void itemAceitaDescricaoNula() { assertEquals("", new ItemCardapio("X", null, 1, null).getDescricao()); }
    @Test void itemRetornaImagemInformada() { BibliotecaImagens b = new BibliotecaImagens(); ImagemProduto img = b.obter("x"); assertSame(img, new ItemCardapio("X", "d", 1, img).getImagem()); }
    @Test void itemBuscaIgnoraMaiusculas() { assertEquals("X-Salada", item("X-Salada", 10).buscar("x-salada").getNome()); }
    @Test void itemBuscaInexistenteLancaErro() { assertThrows(IllegalArgumentException.class, () -> item("X", 10).buscar("Y")); }
    @Test void itemNaoPermiteAdicionarFilho() { assertThrows(UnsupportedOperationException.class, () -> item("X", 10).adicionar(item("Y", 5))); }
    @Test void itemNaoPermiteRemoverFilho() { assertThrows(UnsupportedOperationException.class, () -> item("X", 10).remover(item("Y", 5))); }
    @Test void itemComPrecoZeroMantemZero() { assertEquals(0.0, item("Agua", 0).getPreco(), 0.001); }
    @Test void secaoNaoAceitaNomeNulo() { assertThrows(IllegalArgumentException.class, () -> new SecaoCardapio(null, "d")); }
    @Test void secaoNaoAceitaNomeVazio() { assertThrows(IllegalArgumentException.class, () -> new SecaoCardapio(" ", "d")); }
    @Test void secaoAceitaDescricaoNula() { assertEquals("", new SecaoCardapio("Combos", null).getDescricao()); }
    @Test void secaoIniciaSemComponentes() { assertEquals(0, new SecaoCardapio("Combos", "").getComponentes().size()); }
    @Test void secaoNaoAceitaComponenteNulo() { assertThrows(IllegalArgumentException.class, () -> new SecaoCardapio("Combos", "").adicionar(null)); }
    @Test void secaoRemoveComponente() { SecaoCardapio s = new SecaoCardapio("Combos", ""); ItemCardapio x = item("X", 10); s.adicionar(x); s.remover(x); assertEquals(0, s.quantidadeDeItens()); }
    @Test void secaoRetornaListaImutavel() { SecaoCardapio s = new SecaoCardapio("Combos", ""); assertThrows(UnsupportedOperationException.class, () -> s.getComponentes().add(item("X", 10))); }
    @Test void secaoBuscaElaMesma() { SecaoCardapio s = new SecaoCardapio("Combos", ""); assertSame(s, s.buscar("combos")); }
    @Test void secaoBuscaFilhoAninhado() { SecaoCardapio raiz = new SecaoCardapio("Menu", ""); SecaoCardapio filhos = new SecaoCardapio("Burgers", ""); filhos.adicionar(item("X-Tudo", 25)); raiz.adicionar(filhos); assertEquals("X-Tudo", raiz.buscar("x-tudo").getNome()); }
    @Test void secaoBuscaInexistenteLancaErro() { assertThrows(IllegalArgumentException.class, () -> new SecaoCardapio("Menu", "").buscar("Nada")); }
    @Test void secaoSomaPrecosAninhados() { SecaoCardapio raiz = new SecaoCardapio("Menu", ""); SecaoCardapio filhos = new SecaoCardapio("Burgers", ""); filhos.adicionar(item("A", 10)); filhos.adicionar(item("B", 12)); raiz.adicionar(filhos); assertEquals(22.0, raiz.getPreco(), 0.001); }
    @Test void secaoContaItensAninhados() { SecaoCardapio raiz = new SecaoCardapio("Menu", ""); SecaoCardapio filhos = new SecaoCardapio("Burgers", ""); filhos.adicionar(item("A", 10)); filhos.adicionar(item("B", 12)); raiz.adicionar(filhos); assertEquals(2, raiz.quantidadeDeItens()); }
    @Test void secaoAceitaVisitanteEmItem() { ItemCardapio x = item("X", 10); TotalizadorCardapioVisitor v = new TotalizadorCardapioVisitor(); x.aceitar(v); assertEquals(10.0, v.getTotal(), 0.001); }
    @Test void totalizadorContaSecaoVisitada() { SecaoCardapio s = new SecaoCardapio("Menu", ""); TotalizadorCardapioVisitor v = new TotalizadorCardapioVisitor(); s.aceitar(v); assertEquals(1, v.getSecoes()); }
    @Test void totalizadorNaoSomaPrecoDeSecaoVazia() { SecaoCardapio s = new SecaoCardapio("Menu", ""); TotalizadorCardapioVisitor v = new TotalizadorCardapioVisitor(); s.aceitar(v); assertEquals(0.0, v.getTotal(), 0.001); }
    @Test void listadorIncluiNomeDaSecao() { SecaoCardapio s = new SecaoCardapio("Menu", ""); ListadorCardapioVisitor v = new ListadorCardapioVisitor(); s.aceitar(v); assertTrue(v.getNomes().contains("Menu")); }
    @Test void listadorIncluiNomeDoItem() { ItemCardapio x = item("X", 10); ListadorCardapioVisitor v = new ListadorCardapioVisitor(); x.aceitar(v); assertTrue(v.getNomes().contains("X")); }
    @Test void listadorRetornaListaImutavel() { ListadorCardapioVisitor v = new ListadorCardapioVisitor(); assertThrows(UnsupportedOperationException.class, () -> v.getNomes().add("X")); }
    @Test void bibliotecaNaoAceitaChaveNula() { assertThrows(IllegalArgumentException.class, () -> new BibliotecaImagens().obter(null)); }
    @Test void bibliotecaNaoAceitaChaveVazia() { assertThrows(IllegalArgumentException.class, () -> new BibliotecaImagens().obter(" ")); }
    @Test void bibliotecaArmazenaUmaChave() { BibliotecaImagens b = new BibliotecaImagens(); b.obter("x"); assertEquals(1, b.quantidadeEmCache()); }
    @Test void bibliotecaReaproveitaMesmaImagem() { BibliotecaImagens b = new BibliotecaImagens(); assertSame(b.obter("x"), b.obter("x")); }
    @Test void bibliotecaCriaImagensDiferentes() { BibliotecaImagens b = new BibliotecaImagens(); assertNotSame(b.obter("x"), b.obter("y")); }
    @Test void imagemRetornaChave() { assertEquals("x", new BibliotecaImagens().obter("x").getChave()); }
    @Test void imagemRetornaConteudoBase64() { assertEquals("base64:x", new BibliotecaImagens().obter("x").getConteudoBase64()); }
    @Test void iteratorNaoAceitaRaizNula() { assertThrows(IllegalArgumentException.class, () -> new CardapioIterator(null)); }
    @Test void iteratorTemProximoComRaiz() { assertTrue(new CardapioIterator(item("X", 10)).hasNext()); }
    @Test void iteratorRetornaRaizPrimeiro() { ItemCardapio x = item("X", 10); assertSame(x, new CardapioIterator(x).next()); }
    @Test void iteratorFicaSemProximoDepoisDoItem() { CardapioIterator it = new CardapioIterator(item("X", 10)); it.next(); assertFalse(it.hasNext()); }
    @Test void iteratorLancaErroNoFim() { CardapioIterator it = new CardapioIterator(item("X", 10)); it.next(); assertThrows(NoSuchElementException.class, () -> it.next()); }
    @Test void iteratorPercorrePrimeiroFilho() { SecaoCardapio s = new SecaoCardapio("Menu", ""); s.adicionar(item("X", 10)); CardapioIterator it = new CardapioIterator(s); it.next(); assertEquals("X", it.next().getNome()); }
    @Test void iteratorPercorreSecaoAntesDoItemInterno() { SecaoCardapio raiz = new SecaoCardapio("Menu", ""); SecaoCardapio sub = new SecaoCardapio("Burgers", ""); sub.adicionar(item("X", 10)); raiz.adicionar(sub); CardapioIterator it = new CardapioIterator(raiz); it.next(); assertEquals("Burgers", it.next().getNome()); }
    @Test void iteratorPercorreItemInternoDepoisDaSecao() { SecaoCardapio raiz = new SecaoCardapio("Menu", ""); SecaoCardapio sub = new SecaoCardapio("Burgers", ""); sub.adicionar(item("X", 10)); raiz.adicionar(sub); CardapioIterator it = new CardapioIterator(raiz); it.next(); it.next(); assertEquals("X", it.next().getNome()); }
    @Test void visitantePodeSerLambdaDeItem() { ItemCardapio x = item("X", 10); ListadorCardapioVisitor v = new ListadorCardapioVisitor(); x.aceitar(v); assertEquals(1, v.getNomes().size()); }
    @Test void componenteBuscaDefaultRetornaItemCorreto() { ItemCardapio x = item("X", 10); assertEquals("X", x.buscar("X").getNome()); }
}
