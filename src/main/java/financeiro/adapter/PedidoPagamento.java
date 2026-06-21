package hamburgueria.financeiro.adapter;

public class PedidoPagamento {
    private final String codigo;
    private final double total;

    public PedidoPagamento(String codigo, double total) {
        this.codigo = codigo;
        this.total = total;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getTotal() {
        return total;
    }
}
