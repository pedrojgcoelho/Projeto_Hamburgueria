package hamburgueria.cozinha.equipamentos.factorymethod;

public class Chapa implements Equipamento {
    public String nome() {
        return "Chapa";
    }

    public String operar(String item) {
        return "grelhando " + item;
    }
}
