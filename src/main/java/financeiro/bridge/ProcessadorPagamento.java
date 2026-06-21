package hamburgueria.financeiro.bridge;

public interface ProcessadorPagamento {
    String processar(String tipo, double valor);
}
