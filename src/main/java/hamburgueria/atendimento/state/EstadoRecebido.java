package hamburgueria.atendimento.state;

public class EstadoRecebido implements EstadoPedido {
    public void proximo(PedidoAtendimento pedido) {
        pedido.setEstado(new EstadoEmPreparo());
    }

    public void cancelar(PedidoAtendimento pedido) {
        pedido.setEstado(new EstadoFinalizado("CANCELADO"));
    }

    public String nome() {
        return "RECEBIDO";
    }
}
