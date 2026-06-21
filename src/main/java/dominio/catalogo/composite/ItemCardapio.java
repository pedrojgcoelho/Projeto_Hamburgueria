package hamburgueria.dominio.catalogo.composite;

import hamburgueria.dominio.catalogo.flyweight.ImagemProduto;
import hamburgueria.dominio.catalogo.visitor.VisitanteCardapio;

public class ItemCardapio implements ComponenteCardapio {
    private final String nome;
    private final String descricao;
    private final double preco;
    private final ImagemProduto imagem;

    public ItemCardapio(String nome, String descricao, double preco, ImagemProduto imagem) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatorio");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("Preco nao pode ser negativo");
        }
        this.nome = nome;
        this.descricao = descricao == null ? "" : descricao;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public ImagemProduto getImagem() {
        return imagem;
    }

    public int quantidadeDeItens() {
        return 1;
    }

    public void aceitar(VisitanteCardapio visitante) {
        visitante.visitarItem(this);
    }
}
