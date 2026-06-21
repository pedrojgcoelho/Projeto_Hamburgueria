package hamburgueria.atendimento.chain;

public class ContextoPedido {
    private final String cliente;
    private final int quantidadeItens;
    private final boolean pagamentoConfirmado;

    public ContextoPedido(String cliente, int quantidadeItens, boolean pagamentoConfirmado) {
        this.cliente = cliente;
        this.quantidadeItens = quantidadeItens;
        this.pagamentoConfirmado = pagamentoConfirmado;
    }

    public String getCliente() {
        return cliente;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public boolean isPagamentoConfirmado() {
        return pagamentoConfirmado;
    }
}
