package hamburgueria.atendimento.mediator;

public class Atendente extends Funcionario {
    public Atendente(String nome, MediadorLoja mediador) {
        super(nome, mediador);
    }

    public String funcao() {
        return "ATENDIMENTO";
    }
}
