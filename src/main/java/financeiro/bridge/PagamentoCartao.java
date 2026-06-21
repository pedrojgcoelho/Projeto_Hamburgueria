package hamburgueria.financeiro.bridge;

public class PagamentoCartao extends Pagamento {
    public PagamentoCartao(ProcessadorPagamento processador) {
        super(processador);
    }

    protected String tipo() {
        return "CARTAO";
    }
}
