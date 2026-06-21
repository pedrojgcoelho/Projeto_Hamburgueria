package hamburgueria.atendimento.chain;

import hamburgueria.dominio.carrinho.modelo.Carrinho;

public class ValidadorCarrinho extends ValidadorPedido {
    protected ResultadoValidacao validarAtual(ContextoPedido contexto) {
        if (contexto.getQuantidadeItens() <= 0) {
            return ResultadoValidacao.erro("Carrinho vazio");
        }
        return ResultadoValidacao.ok();
    }
}
