package hamburgueria.atendimento.chain;

public class ValidadorCliente extends ValidadorPedido {
    protected ResultadoValidacao validarAtual(ContextoPedido contexto) {
        if (contexto.getCliente() == null || contexto.getCliente().isBlank()) {
            return ResultadoValidacao.erro("Cliente obrigatorio");
        }
        return ResultadoValidacao.ok();
    }
}
