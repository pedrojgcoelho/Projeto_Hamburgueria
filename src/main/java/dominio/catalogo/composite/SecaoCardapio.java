package hamburgueria.dominio.catalogo.composite;

import hamburgueria.dominio.catalogo.visitor.VisitanteCardapio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecaoCardapio implements ComponenteCardapio {
    private final String nome;
    private final String descricao;
    private final List<ComponenteCardapio> componentes = new ArrayList<>();

    public SecaoCardapio(String nome, String descricao) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da secao obrigatorio");
        }
        this.nome = nome;
        this.descricao = descricao == null ? "" : descricao;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return componentes.stream().mapToDouble(ComponenteCardapio::getPreco).sum();
    }

    public String getDescricao() {
        return descricao;
    }

    public List<ComponenteCardapio> getComponentes() {
        return Collections.unmodifiableList(componentes);
    }

    public void adicionar(ComponenteCardapio componente) {
        if (componente == null) {
            throw new IllegalArgumentException("Componente obrigatorio");
        }
        componentes.add(componente);
    }

    public void remover(ComponenteCardapio componente) {
        componentes.remove(componente);
    }

    public int quantidadeDeItens() {
        return componentes.stream().mapToInt(ComponenteCardapio::quantidadeDeItens).sum();
    }

    public ComponenteCardapio buscar(String nome) {
        if (getNome().equalsIgnoreCase(nome)) {
            return this;
        }
        return componentes.stream()
                .filter(c -> contem(c, nome))
                .map(c -> c.buscar(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Componente nao encontrado: " + nome));
    }

    private boolean contem(ComponenteCardapio componente, String nome) {
        try {
            componente.buscar(nome);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public void aceitar(VisitanteCardapio visitante) {
        visitante.visitarSecao(this);
        componentes.forEach(c -> c.aceitar(visitante));
    }
}
