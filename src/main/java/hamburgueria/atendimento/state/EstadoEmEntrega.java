package hamburgueria.atendimento.state;

public class EstadoEmEntrega implements EstadoPedido {
    public void proximo(PedidoAtendimento pedido) {
        pedido.setEstado(new EstadoFinalizado("ENTREGUE"));
    }

    public void cancelar(PedidoAtendimento pedido) {
        throw new IllegalStateException("Pedido em entrega nao pode ser cancelado");
    }

    public String nome() {
        return "EM_ENTREGA";
    }
}
