package hamburgueria.cozinha.equipamentos.factorymethod;

public class Liquidificador implements Equipamento {
    public String nome() {
        return "Liquidificador";
    }

    public String operar(String item) {
        return "batendo " + item;
    }
}
