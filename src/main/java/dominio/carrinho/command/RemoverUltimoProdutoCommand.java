package hamburgueria.dominio.carrinho.command;

import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.dominio.montagem.decorator.Hamburguer;

public class RemoverUltimoProdutoCommand implements ComandoCarrinho {
    private final Carrinho carrinho;
    private Hamburguer removido;

    public RemoverUltimoProdutoCommand(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public void executar() {
        removido = carrinho.removerUltimo();
    }

    public void desfazer() {
        if (removido == null) {
            throw new IllegalStateException("Nada para restaurar");
        }
        carrinho.adicionar(removido);
        removido = null;
    }
}
