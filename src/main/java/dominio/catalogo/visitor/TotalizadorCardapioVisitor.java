package hamburgueria.dominio.catalogo.visitor;

import hamburgueria.dominio.catalogo.composite.ItemCardapio;
import hamburgueria.dominio.catalogo.composite.SecaoCardapio;

public class TotalizadorCardapioVisitor implements VisitanteCardapio {
    private double total;
    private int secoes;

    public void visitarItem(ItemCardapio item) {
        total += item.getPreco();
    }

    public void visitarSecao(SecaoCardapio secao) {
        secoes++;
    }

    public double getTotal() {
        return total;
    }

    public int getSecoes() {
        return secoes;
    }
}
