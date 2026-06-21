package hamburgueria.atendimento.facade;

import hamburgueria.dominio.carrinho.modelo.Carrinho;
import hamburgueria.dominio.montagem.decorator.Hamburguer;
import hamburgueria.financeiro.strategy.CalculadoraPreco;

public class TotemFacade {
    private final Carrinho carrinho;
    private final CalculadoraPreco calculadora;

    public TotemFacade(Carrinho carrinho, CalculadoraPreco calculadora) {
        this.carrinho = carrinho;
        this.calculadora = calculadora;
    }

    public void adicionarAoPedido(Hamburguer hamburguer) {
        carrinho.adicionar(hamburguer);
    }

    public double fecharPedido() {
        if (carrinho.tamanho() == 0) {
            throw new IllegalStateException("Pedido vazio");
        }
        return calculadora.calcular(carrinho.total());
    }

    public int itensNoPedido() {
        return carrinho.tamanho();
    }
}
