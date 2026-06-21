package hamburgueria.dominio.montagem.decorator;

public abstract class AdicionalHamburguer implements Hamburguer {
    private final Hamburguer hamburguer;

    protected AdicionalHamburguer(Hamburguer hamburguer) {
        if (hamburguer == null) {
            throw new IllegalArgumentException("Hamburguer obrigatorio");
        }
        this.hamburguer = hamburguer;
    }

    protected Hamburguer getHamburguer() {
        return hamburguer;
    }

    public String getNome() {
        return hamburguer.getNome();
    }
}
