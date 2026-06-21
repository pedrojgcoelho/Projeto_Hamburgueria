package hamburgueria.atendimento.mediator;

public class Entregador extends Funcionario {
    public Entregador(String nome, MediadorLoja mediador) {
        super(nome, mediador);
    }

    public String funcao() {
        return "ENTREGA";
    }
}
