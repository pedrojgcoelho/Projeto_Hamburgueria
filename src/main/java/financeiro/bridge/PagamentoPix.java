package hamburgueria.financeiro.bridge;

public class PagamentoPix extends Pagamento {
    public PagamentoPix(ProcessadorPagamento processador) {
        super(processador);
    }

    protected String tipo() {
        return "PIX";
    }
}
