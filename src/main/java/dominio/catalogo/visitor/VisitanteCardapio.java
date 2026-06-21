package hamburgueria.dominio.catalogo.visitor;

import hamburgueria.dominio.catalogo.composite.ItemCardapio;
import hamburgueria.dominio.catalogo.composite.SecaoCardapio;

public interface VisitanteCardapio {
    void visitarItem(ItemCardapio item);
    void visitarSecao(SecaoCardapio secao);
}
