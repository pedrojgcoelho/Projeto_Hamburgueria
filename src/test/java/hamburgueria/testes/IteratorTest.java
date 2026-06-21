package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.catalogo.composite.ItemCardapio;
import hamburgueria.dominio.catalogo.composite.SecaoCardapio;
import hamburgueria.dominio.catalogo.iterator.CardapioIterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class IteratorTest {
    private SecaoCardapio raiz() { SecaoCardapio s = new SecaoCardapio("Cardapio", ""); s.adicionar(new ItemCardapio("Burger", "", 20, null)); return s; }
    @Test void iteratorComecaComElemento() { assertTrue(new CardapioIterator(raiz()).hasNext()); }
    @Test void iteratorRetornaRaizPrimeiro() { assertEquals("Cardapio", new CardapioIterator(raiz()).next().getNome()); }
    @Test void iteratorRetornaFilhoDepoisDaRaiz() { CardapioIterator it = new CardapioIterator(raiz()); it.next(); assertEquals("Burger", it.next().getNome()); }
    @Test void iteratorTerminaAposTodosElementos() { CardapioIterator it = new CardapioIterator(raiz()); it.next(); it.next(); assertFalse(it.hasNext()); }
    @Test void iteratorNaoAceitaRaizNula() { assertThrows(IllegalArgumentException.class, () -> new CardapioIterator(null)); }
    @Test void iteratorLancaErroNoFim() { CardapioIterator it = new CardapioIterator(raiz()); it.next(); it.next(); assertThrows(NoSuchElementException.class, it::next); }
    @Test void iteratorPercorreSecaoAninhada() { SecaoCardapio r = new SecaoCardapio("R", ""); r.adicionar(new SecaoCardapio("Filha", "")); CardapioIterator it = new CardapioIterator(r); it.next(); assertEquals("Filha", it.next().getNome()); }
}
