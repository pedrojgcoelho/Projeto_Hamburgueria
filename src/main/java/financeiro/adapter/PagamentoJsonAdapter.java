package hamburgueria.financeiro.adapter;

public class PagamentoJsonAdapter {
    private final PedidoPagamento pedido;

    public PagamentoJsonAdapter(PedidoPagamento pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido obrigatorio");
        }
        this.pedido = pedido;
    }

    public String paraJson() {
        return "{\"pedido\":\"" + pedido.getCodigo() + "\",\"total\":" + pedido.getTotal() + "}";
    }
}
