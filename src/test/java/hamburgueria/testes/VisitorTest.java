package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.catalogo.composite.ItemCardapio;
import hamburgueria.dominio.catalogo.composite.SecaoCardapio;
import hamburgueria.dominio.catalogo.visitor.ListadorCardapioVisitor;
import hamburgueria.dominio.catalogo.visitor.TotalizadorCardapioVisitor;
import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {
    private SecaoCardapio cardapio() { SecaoCardapio s = new SecaoCardapio("Menu", ""); s.adicionar(new ItemCardapio("Burger", "", 20, null)); s.adicionar(new ItemCardapio("Refri", "", 8, null)); return s; }
    @Test void totalizadorSomaItens() { TotalizadorCardapioVisitor v = new TotalizadorCardapioVisitor(); cardapio().aceitar(v); assertEquals(28.0, v.getTotal(), 0.001); }
    @Test void totalizadorContaSecoes() { TotalizadorCardapioVisitor v = new TotalizadorCardapioVisitor(); cardapio().aceitar(v); assertEquals(1, v.getSecoes()); }
    @Test void listadorIncluiSecao() { ListadorCardapioVisitor v = new ListadorCardapioVisitor(); cardapio().aceitar(v); assertEquals("Menu", v.getNomes().get(0)); }
    @Test void listadorIncluiPrimeiroItem() { ListadorCardapioVisitor v = new ListadorCardapioVisitor(); cardapio().aceitar(v); assertEquals("Burger", v.getNomes().get(1)); }
    @Test void listadorIncluiSegundoItem() { ListadorCardapioVisitor v = new ListadorCardapioVisitor(); cardapio().aceitar(v); assertEquals("Refri", v.getNomes().get(2)); }
    @Test void listadorContaNomes() { ListadorCardapioVisitor v = new ListadorCardapioVisitor(); cardapio().aceitar(v); assertEquals(3, v.getNomes().size()); }
    @Test void totalizadorIniciaZerado() { assertEquals(0.0, new TotalizadorCardapioVisitor().getTotal(), 0.001); }
}
