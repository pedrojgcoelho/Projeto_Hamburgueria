package hamburgueria.cozinha.equipamentos.factorymethod;

public abstract class EstacaoPreparo {
    public String preparar(String item) {
        return criarEquipamento().operar(item);
    }

    protected abstract Equipamento criarEquipamento();
}
