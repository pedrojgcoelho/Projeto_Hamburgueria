package hamburgueria.dominio.catalogo.iterator;

import hamburgueria.dominio.catalogo.composite.ComponenteCardapio;
import hamburgueria.dominio.catalogo.composite.SecaoCardapio;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CardapioIterator implements Iterator<ComponenteCardapio> {
    private final ArrayDeque<ComponenteCardapio> fila = new ArrayDeque<>();

    public CardapioIterator(ComponenteCardapio raiz) {
        if (raiz == null) {
            throw new IllegalArgumentException("Raiz obrigatoria");
        }
        fila.add(raiz);
    }

    public boolean hasNext() {
        return !fila.isEmpty();
    }

    public ComponenteCardapio next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Fim do cardapio");
        }
        ComponenteCardapio atual = fila.removeFirst();
        if (atual instanceof SecaoCardapio) {
            fila.addAll(((SecaoCardapio) atual).getComponentes());
        }
        return atual;
    }
}
