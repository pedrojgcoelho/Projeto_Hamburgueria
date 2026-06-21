package hamburgueria.financeiro.bridge;

public class ProcessadorOnline implements ProcessadorPagamento {
    public String processar(String tipo, double valor) {
        return "ONLINE-" + tipo + "-APROVADO";
    }
}
