package hamburgueria.financeiro.bridge;

public abstract class Pagamento {
    private final ProcessadorPagamento processador;

    protected Pagamento(ProcessadorPagamento processador) {
        if (processador == null) {
            throw new IllegalArgumentException("Processador obrigatorio");
        }
        this.processador = processador;
    }

    public String pagar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        return processador.processar(tipo(), valor);
    }

    protected abstract String tipo();
}
