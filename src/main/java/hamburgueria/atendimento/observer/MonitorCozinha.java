package hamburgueria.atendimento.observer;

public class MonitorCozinha implements ObservadorPedido {
    private String pedidoAtual = "";

    public void atualizar(String codigoPedido, String status) {
        pedidoAtual = status + "->" + codigoPedido;
    }

    public String getPedidoAtual() {
        return pedidoAtual;
    }
}
