package hamburgueria.atendimento.chain;

public abstract class ValidadorPedido {
    private ValidadorPedido proximo;

    public ValidadorPedido ligarCom(ValidadorPedido proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public ResultadoValidacao validar(ContextoPedido contexto) {
        ResultadoValidacao resultado = validarAtual(contexto);
        if (!resultado.isValido()) {
            return resultado;
        }
        if (proximo == null) {
            return ResultadoValidacao.ok();
        }
        return proximo.validar(contexto);
    }

    protected abstract ResultadoValidacao validarAtual(ContextoPedido contexto);
}
