package hamburgueria.dominio.carrinho.modelo;

import hamburgueria.dominio.carrinho.memento.CarrinhoMemento;
import hamburgueria.dominio.montagem.decorator.Hamburguer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {
    private List<Hamburguer> itens = new ArrayList<>();

    public void adicionar(Hamburguer item) {
        if (item == null) {
            throw new IllegalArgumentException("Item obrigatorio");
        }
        itens.add(item);
    }

    public Hamburguer removerUltimo() {
        if (itens.isEmpty()) {
            throw new IllegalStateException("Carrinho vazio");
        }
        return itens.remove(itens.size() - 1);
    }

    public void limpar() {
        itens.clear();
    }

    public double total() {
        return itens.stream().mapToDouble(Hamburguer::getPreco).sum();
    }

    public int tamanho() {
        return itens.size();
    }

    public List<Hamburguer> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public CarrinhoMemento salvar() {
        return new CarrinhoMemento(itens);
    }

    public void restaurar(CarrinhoMemento memento) {
        if (memento == null) {
            throw new IllegalArgumentException("Memento obrigatorio");
        }
        this.itens = new ArrayList<>(memento.getItens());
    }
}
