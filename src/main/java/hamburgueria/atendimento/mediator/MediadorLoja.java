package hamburgueria.atendimento.mediator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediadorLoja {
    private final List<String> mensagens = new ArrayList<>();

    public void avisar(Funcionario origem, String mensagem) {
        mensagens.add(origem.getNome() + ":" + mensagem);
    }

    public List<String> getMensagens() {
        return Collections.unmodifiableList(mensagens);
    }
}
