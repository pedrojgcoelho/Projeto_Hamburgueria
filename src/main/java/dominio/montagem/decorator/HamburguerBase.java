package hamburgueria.dominio.montagem.decorator;

public class HamburguerBase implements Hamburguer {
    private final String nome;
    private final double preco;

    public HamburguerBase(String nome, double preco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatorio");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("Preco invalido");
        }
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
