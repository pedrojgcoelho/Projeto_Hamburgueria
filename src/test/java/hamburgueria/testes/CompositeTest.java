package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.catalogo.composite.ItemCardapio;
import hamburgueria.dominio.catalogo.composite.SecaoCardapio;
import static org.junit.jupiter.api.Assertions.*;

class CompositeTest {
    private ItemCardapio item(String nome, double preco) {
        return new ItemCardapio(nome, "desc", preco, null);
    }

    @Test void itemRetornaNome() { assertEquals("X-Bacon", item("X-Bacon", 20).getNome()); }
    @Test void itemRetornaPreco() { assertEquals(20.0, item("X", 20).getPreco(), 0.001); }
    @Test void itemContaUmItem() { assertEquals(1, item("X", 20).quantidadeDeItens()); }
    @Test void itemNaoAceitaPrecoNegativo() { assertThrows(IllegalArgumentException.class, () -> item("X", -1)); }
    @Test void secaoSomaPrecoDosFilhos() { SecaoCardapio s = new SecaoCardapio("Burgers", ""); s.adicionar(item("A", 10)); s.adicionar(item("B", 12)); assertEquals(22.0, s.getPreco(), 0.001); }
    @Test void secaoContaItensFolha() { SecaoCardapio s = new SecaoCardapio("Burgers", ""); s.adicionar(item("A", 10)); s.adicionar(item("B", 12)); assertEquals(2, s.quantidadeDeItens()); }
    @Test void secaoBuscaFilhoPorNome() { SecaoCardapio s = new SecaoCardapio("Burgers", ""); s.adicionar(item("A", 10)); assertEquals("A", s.buscar("A").getNome()); }
}
