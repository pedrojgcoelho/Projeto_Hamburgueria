package hamburgueria.atendimento.observer;

public interface ObservadorPedido {
    void atualizar(String codigoPedido, String status);
}
