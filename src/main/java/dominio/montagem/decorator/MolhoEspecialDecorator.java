package hamburgueria.dominio.montagem.decorator;

public class MolhoEspecialDecorator extends AdicionalHamburguer {
    public MolhoEspecialDecorator(Hamburguer hamburguer) {
        super(hamburguer);
    }

    public String getDescricao() {
        return getHamburguer().getDescricao() + ", molho da casa";
    }

    public double getPreco() {
        return getHamburguer().getPreco() + 1.80;
    }
}
