package hamburgueria.dominio.catalogo.composite;

import hamburgueria.dominio.catalogo.visitor.VisitanteCardapio;

public interface ComponenteCardapio {
    String getNome();
    double getPreco();
    String getDescricao();
    int quantidadeDeItens();
    void aceitar(VisitanteCardapio visitante);

    default void adicionar(ComponenteCardapio componente) {
        throw new UnsupportedOperationException("Item simples nao aceita filhos");
    }

    default void remover(ComponenteCardapio componente) {
        throw new UnsupportedOperationException("Item simples nao remove filhos");
    }

    default ComponenteCardapio buscar(String nome) {
        if (getNome().equalsIgnoreCase(nome)) {
            return this;
        }
        throw new IllegalArgumentException("Componente nao encontrado: " + nome);
    }
}
