package hamburgueria.dominio.carrinho.memento;

import hamburgueria.dominio.montagem.decorator.Hamburguer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarrinhoMemento {
    private final List<Hamburguer> itens;

    public CarrinhoMemento(List<Hamburguer> itens) {
        this.itens = new ArrayList<>(itens);
    }

    public List<Hamburguer> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public int quantidadeSalva() {
        return itens.size();
    }
}
