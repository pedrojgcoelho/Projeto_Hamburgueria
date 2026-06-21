package hamburgueria.cozinha.combos.abstractfactory;

public class ComboSmashFactory implements ComboFactory {
    public Pao criarPao() {
        return new PaoBrioche();
    }

    public Proteina criarProteina() {
        return new CarneSmash();
    }

    public Acompanhamento criarAcompanhamento() {
        return new BatataRustica();
    }
}
