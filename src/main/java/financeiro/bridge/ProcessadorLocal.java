package hamburgueria.financeiro.bridge;

public class ProcessadorLocal implements ProcessadorPagamento {
    public String processar(String tipo, double valor) {
        return "LOCAL-" + tipo + "-" + String.format(java.util.Locale.US, "%.2f", valor);
    }
}
