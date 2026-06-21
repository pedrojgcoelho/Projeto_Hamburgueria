package hamburgueria.financeiro.proxy;

public class GatewayReal implements GatewayPagamento {
    public boolean autorizar(double valor, String token) {
        return valor > 0 && token != null && !token.isBlank();
    }
}
