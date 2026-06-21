package hamburgueria.cozinha.equipamentos.factorymethod;

public class EstacaoGrelhados extends EstacaoPreparo {
    protected Equipamento criarEquipamento() {
        return new Chapa();
    }
}
