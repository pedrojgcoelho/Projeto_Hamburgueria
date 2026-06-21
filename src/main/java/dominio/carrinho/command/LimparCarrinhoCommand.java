package hamburgueria.dominio.carrinho.command;

import hamburgueria.dominio.carrinho.memento.CarrinhoMemento;
import hamburgueria.dominio.carrinho.modelo.Carrinho;

public class LimparCarrinhoCommand implements ComandoCarrinho {
    private final Carrinho carrinho;
    private CarrinhoMemento backup;

    public LimparCarrinhoCommand(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public void executar() {
        backup = carrinho.salvar();
        carrinho.limpar();
    }

    public void desfazer() {
        if (backup == null) {
            throw new IllegalStateException("Backup inexistente");
        }
        carrinho.restaurar(backup);
    }
}
