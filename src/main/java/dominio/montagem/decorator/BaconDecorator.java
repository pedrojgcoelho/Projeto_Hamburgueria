package hamburgueria.dominio.montagem.decorator;

public class BaconDecorator extends AdicionalHamburguer {
    public BaconDecorator(Hamburguer hamburguer) {
        super(hamburguer);
    }

    public String getDescricao() {
        return getHamburguer().getDescricao() + ", bacon crocante";
    }

    public double getPreco() {
        return getHamburguer().getPreco() + 4.50;
    }
}
