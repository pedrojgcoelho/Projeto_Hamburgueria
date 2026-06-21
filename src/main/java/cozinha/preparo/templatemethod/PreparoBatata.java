package hamburgueria.cozinha.preparo.templatemethod;

public class PreparoBatata extends PreparoTemplate {
    protected String cozinhar(String item) {
        return "fritar " + item;
    }
}
