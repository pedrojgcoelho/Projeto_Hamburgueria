package hamburgueria.atendimento.mediator;

public class Chapeiro extends Funcionario {
    public Chapeiro(String nome, MediadorLoja mediador) {
        super(nome, mediador);
    }

    public String funcao() {
        return "CHAPA";
    }
}
