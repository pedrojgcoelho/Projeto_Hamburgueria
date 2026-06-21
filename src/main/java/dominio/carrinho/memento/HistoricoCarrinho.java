package hamburgueria.dominio.carrinho.memento;

import hamburgueria.dominio.carrinho.command.ComandoCarrinho;
import java.util.ArrayDeque;

public class HistoricoCarrinho {
    private final ArrayDeque<ComandoCarrinho> comandos = new ArrayDeque<>();

    public void executar(ComandoCarrinho comando) {
        if (comando == null) {
            throw new IllegalArgumentException("Comando obrigatorio");
        }
        comando.executar();
        comandos.push(comando);
    }

    public void desfazerUltimo() {
        if (comandos.isEmpty()) {
            throw new IllegalStateException("Historico vazio");
        }
        comandos.pop().desfazer();
    }

    public int tamanho() {
        return comandos.size();
    }
}
