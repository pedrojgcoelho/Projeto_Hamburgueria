package hamburgueria.atendimento.chain;

public class ResultadoValidacao {
    private final boolean valido;
    private final String mensagem;

    private ResultadoValidacao(boolean valido, String mensagem) {
        this.valido = valido;
        this.mensagem = mensagem;
    }

    public static ResultadoValidacao ok() {
        return new ResultadoValidacao(true, "OK");
    }

    public static ResultadoValidacao erro(String mensagem) {
        return new ResultadoValidacao(false, mensagem);
    }

    public boolean isValido() {
        return valido;
    }

    public String getMensagem() {
        return mensagem;
    }
}
