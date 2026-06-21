package hamburgueria.atendimento.observer;

import java.util.ArrayList;
import java.util.List;

public class PedidoObservavel {
    private final String codigo;
    private final List<ObservadorPedido> observadores = new ArrayList<>();
    private String status = "ABERTO";

    public PedidoObservavel(String codigo) {
        this.codigo = codigo;
    }

    public void adicionarObservador(ObservadorPedido observador) {
        if (observador == null) {
            throw new IllegalArgumentException("Observador obrigatorio");
        }
        observadores.add(observador);
    }

    public void alterarStatus(String status) {
        this.status = status;
        observadores.forEach(o -> o.atualizar(codigo, status));
    }

    public String getStatus() {
        return status;
    }

    public int quantidadeObservadores() {
        return observadores.size();
    }
}
