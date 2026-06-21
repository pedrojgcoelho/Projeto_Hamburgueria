package hamburgueria.cozinha.eventos.singleton;

import java.util.ArrayList;
import java.util.List;

public class CentralEventos {
    private static final CentralEventos INSTANCIA = new CentralEventos();
    private final List<OuvinteEvento> ouvintes = new ArrayList<>();
    private Evento ultimoEvento;

    private CentralEventos() {
    }

    public static CentralEventos getInstancia() {
        return INSTANCIA;
    }

    public void registrar(OuvinteEvento ouvinte) {
        if (ouvinte == null) {
            throw new IllegalArgumentException("Ouvinte obrigatorio");
        }
        ouvintes.add(ouvinte);
    }

    public void publicar(Evento evento) {
        this.ultimoEvento = evento;
        ouvintes.forEach(o -> o.receber(evento));
    }

    public Evento getUltimoEvento() {
        return ultimoEvento;
    }

    public int quantidadeOuvintes() {
        return ouvintes.size();
    }

    public void limparOuvintesParaTeste() {
        ouvintes.clear();
        ultimoEvento = null;
    }
}
