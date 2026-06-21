package hamburgueria.atendimento.state;

public class EstadoEmPreparo implements EstadoPedido {
    public void proximo(PedidoAtendimento pedido) {
        pedido.setEstado(new EstadoPronto());
    }

    public void cancelar(PedidoAtendimento pedido) {
        throw new IllegalStateException("Pedido em preparo nao pode ser cancelado");
    }

    public String nome() {
        return "EM_PREPARO";
    }
}
