package hamburgueria.atendimento.state;

public class EstadoFinalizado implements EstadoPedido {
    private final String nome;

    public EstadoFinalizado(String nome) {
        this.nome = nome;
    }

    public void proximo(PedidoAtendimento pedido) {
        throw new IllegalStateException("Pedido finalizado");
    }

    public void cancelar(PedidoAtendimento pedido) {
        throw new IllegalStateException("Pedido finalizado");
    }

    public String nome() {
        return nome;
    }
}
