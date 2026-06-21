package hamburgueria.atendimento.mediator;

public abstract class Funcionario {
    private final String nome;
    private final MediadorLoja mediador;

    protected Funcionario(String nome, MediadorLoja mediador) {
        this.nome = nome;
        this.mediador = mediador;
    }

    public String getNome() {
        return nome;
    }

    public void enviar(String mensagem) {
        mediador.avisar(this, mensagem);
    }

    public abstract String funcao();
}
