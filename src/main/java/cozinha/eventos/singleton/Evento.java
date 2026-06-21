package hamburgueria.cozinha.eventos.singleton;

public class Evento {
    private final String tipo;
    private final String conteudo;

    public Evento(String tipo, String conteudo) {
        this.tipo = tipo;
        this.conteudo = conteudo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getConteudo() {
        return conteudo;
    }
}
