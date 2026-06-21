package hamburgueria.dominio.catalogo.flyweight;

public class ImagemProduto {
    private final String chave;
    private final String conteudoBase64;

    ImagemProduto(String chave, String conteudoBase64) {
        this.chave = chave;
        this.conteudoBase64 = conteudoBase64;
    }

    public String getChave() {
        return chave;
    }

    public String getConteudoBase64() {
        return conteudoBase64;
    }
}
