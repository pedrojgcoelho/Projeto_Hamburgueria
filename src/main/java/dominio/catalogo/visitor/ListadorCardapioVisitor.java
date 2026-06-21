package hamburgueria.dominio.catalogo.visitor;

import hamburgueria.dominio.catalogo.composite.ItemCardapio;
import hamburgueria.dominio.catalogo.composite.SecaoCardapio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListadorCardapioVisitor implements VisitanteCardapio {
    private final List<String> nomes = new ArrayList<>();

    public void visitarItem(ItemCardapio item) {
        nomes.add(item.getNome());
    }

    public void visitarSecao(SecaoCardapio secao) {
        nomes.add(secao.getNome());
    }

    public List<String> getNomes() {
        return Collections.unmodifiableList(nomes);
    }
}
