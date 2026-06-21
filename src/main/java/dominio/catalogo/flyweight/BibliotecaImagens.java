package hamburgueria.dominio.catalogo.flyweight;

import java.util.HashMap;
import java.util.Map;

public class BibliotecaImagens {
    private final Map<String, ImagemProduto> cache = new HashMap<>();

    public ImagemProduto obter(String chave) {
        if (chave == null || chave.isBlank()) {
            throw new IllegalArgumentException("Chave obrigatoria");
        }
        return cache.computeIfAbsent(chave, c -> new ImagemProduto(c, "base64:" + c));
    }

    public int quantidadeEmCache() {
        return cache.size();
    }
}
