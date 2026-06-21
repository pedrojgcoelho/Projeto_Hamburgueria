package hamburgueria.cozinha.equipamentos.factorymethod;

public class Fritadeira implements Equipamento {
    public String nome() {
        return "Fritadeira";
    }

    public String operar(String item) {
        return "fritando " + item;
    }
}
