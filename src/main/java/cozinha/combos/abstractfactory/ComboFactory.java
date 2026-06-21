package hamburgueria.cozinha.combos.abstractfactory;

public interface ComboFactory {
    Pao criarPao();
    Proteina criarProteina();
    Acompanhamento criarAcompanhamento();
}
