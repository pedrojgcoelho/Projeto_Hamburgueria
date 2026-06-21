package hamburgueria.cozinha.equipamentos.factorymethod;

public class EstacaoBebidas extends EstacaoPreparo {
    protected Equipamento criarEquipamento() {
        return new Liquidificador();
    }
}
