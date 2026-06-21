package hamburgueria.cozinha.preparo.templatemethod;

public class PreparoHamburguer extends PreparoTemplate {
    protected String cozinhar(String item) {
        return "selar carne do " + item;
    }
}
