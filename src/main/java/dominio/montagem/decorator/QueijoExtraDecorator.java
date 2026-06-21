package hamburgueria.dominio.montagem.decorator;

public class QueijoExtraDecorator extends AdicionalHamburguer {
    public QueijoExtraDecorator(Hamburguer hamburguer) {
        super(hamburguer);
    }

    public String getDescricao() {
        return getHamburguer().getDescricao() + ", queijo extra";
    }

    public double getPreco() {
        return getHamburguer().getPreco() + 3.25;
    }
}
