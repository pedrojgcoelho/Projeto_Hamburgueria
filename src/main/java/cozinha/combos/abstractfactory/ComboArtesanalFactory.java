package hamburgueria.cozinha.combos.abstractfactory;

public class ComboArtesanalFactory implements ComboFactory {
    public Pao criarPao() {
        return new PaoAustraliano();
    }

    public Proteina criarProteina() {
        return new CarneArtesanal();
    }

    public Acompanhamento criarAcompanhamento() {
        return new OnionRings();
    }
}
