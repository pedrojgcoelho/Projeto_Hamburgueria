package hamburgueria.dominio.carrinho.command;

import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.dominio.montagem.decorator.Hamburguer;

public class AdicionarProdutoCommand implements ComandoCarrinho {
    private final Carrinho carrinho;
    private final Hamburguer item;
    private boolean executado;

    public AdicionarProdutoCommand(Carrinho carrinho, Hamburguer item) {
        this.carrinho = carrinho;
        this.item = item;
    }

    public void executar() {
        carrinho.adicionar(item);
        executado = true;
    }

    public void desfazer() {
        if (!executado) {
            throw new IllegalStateException("Comando nao executado");
        }
        carrinho.removerUltimo();
        executado = false;
    }
}
