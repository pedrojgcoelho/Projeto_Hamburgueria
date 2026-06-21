package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.carrinho.memento.CarrinhoMemento;
import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.dominio.montagem.decorator.Hamburguer;
import hamburgueria.dominio.montagem.decorator.HamburguerBase;
import static org.junit.jupiter.api.Assertions.*;

class MementoTest {
    private Hamburguer burger() { return new HamburguerBase("X", 20); }
    @Test void carrinhoSalvaQuantidade() { Carrinho c = new Carrinho(); c.adicionar(burger()); assertEquals(1, c.salvar().quantidadeSalva()); }
    @Test void carrinhoRestauraQuantidade() { Carrinho c = new Carrinho(); c.adicionar(burger()); CarrinhoMemento m = c.salvar(); c.limpar(); c.restaurar(m); assertEquals(1, c.tamanho()); }
    @Test void mementoNaoMudaComCarrinho() { Carrinho c = new Carrinho(); c.adicionar(burger()); CarrinhoMemento m = c.salvar(); c.limpar(); assertEquals(1, m.quantidadeSalva()); }
    @Test void carrinhoTotalizaItens() { Carrinho c = new Carrinho(); c.adicionar(burger()); assertEquals(20.0, c.total(), 0.001); }
    @Test void carrinhoRemoveUltimo() { Carrinho c = new Carrinho(); c.adicionar(burger()); assertEquals("X", c.removerUltimo().getNome()); }
    @Test void carrinhoNaoRemoveVazio() { assertThrows(IllegalStateException.class, () -> new Carrinho().removerUltimo()); }
    @Test void carrinhoNaoRestauraMementoNulo() { assertThrows(IllegalArgumentException.class, () -> new Carrinho().restaurar(null)); }
}
