package hamburgueria.cozinha.preparo.templatemethod;

public class PreparoBebida extends PreparoTemplate {
    protected String cozinhar(String item) {
        return "misturar " + item;
    }
}
