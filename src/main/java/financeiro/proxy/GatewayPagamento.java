package hamburgueria.financeiro.proxy;

public interface GatewayPagamento {
    boolean autorizar(double valor, String token);
}
