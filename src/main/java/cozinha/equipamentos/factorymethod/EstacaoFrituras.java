package hamburgueria.cozinha.equipamentos.factorymethod;

public class EstacaoFrituras extends EstacaoPreparo {
    protected Equipamento criarEquipamento() {
        return new Fritadeira();
    }
}
