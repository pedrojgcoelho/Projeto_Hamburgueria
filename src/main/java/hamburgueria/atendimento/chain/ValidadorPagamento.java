package hamburgueria.atendimento.chain;

import hamburgueria.financeiro.bridge.Pagamento;

public class ValidadorPagamento extends ValidadorPedido {
    protected ResultadoValidacao validarAtual(ContextoPedido contexto) {
        if (!contexto.isPagamentoConfirmado()) {
            return ResultadoValidacao.erro("Pagamento pendente");
        }
        return ResultadoValidacao.ok();
    }
}
