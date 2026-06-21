package hamburgueria.financeiro.proxy;

public class GatewaySeguroProxy implements GatewayPagamento {
    private final GatewayPagamento real;
    private final String tokenEsperado;
    private int tentativas;

    public GatewaySeguroProxy(GatewayPagamento real, String tokenEsperado) {
        this.real = real;
        this.tokenEsperado = tokenEsperado;
    }

    public boolean autorizar(double valor, String token) {
        tentativas++;
        if (!tokenEsperado.equals(token)) {
            return false;
        }
        return real.autorizar(valor, token);
    }

    public int getTentativas() {
        return tentativas;
    }
}
