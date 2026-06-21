package hamburgueria.atendimento.state;

public interface EstadoPedido {
    void proximo(PedidoAtendimento pedido);
    void cancelar(PedidoAtendimento pedido);
    String nome();
}
