package hamburgueria.dominio.montagem.decorator;

public class CebolaCaramelizadaDecorator extends AdicionalHamburguer {
    public CebolaCaramelizadaDecorator(Hamburguer hamburguer) {
        super(hamburguer);
    }

    public String getDescricao() {
        return getHamburguer().getDescricao() + ", cebola caramelizada";
    }

    public double getPreco() {
        return getHamburguer().getPreco() + 2.75;
    }
}
