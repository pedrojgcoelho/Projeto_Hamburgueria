package hamburgueria.atendimento.state;

public class EstadoPronto implements EstadoPedido {
    public void proximo(PedidoAtendimento pedido) {
        pedido.setEstado(new EstadoEmEntrega());
    }

    public void cancelar(PedidoAtendimento pedido) {
        throw new IllegalStateException("Pedido pronto nao pode ser cancelado");
    }

    public String nome() {
        return "PRONTO";
    }
}
