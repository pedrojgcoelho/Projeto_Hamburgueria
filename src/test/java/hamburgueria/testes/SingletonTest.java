package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.cozinha.eventos.singleton.CentralEventos;
import hamburgueria.cozinha.eventos.singleton.Evento;
import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {
    @Test void centralRetornaMesmaInstancia() { assertSame(CentralEventos.getInstancia(), CentralEventos.getInstancia()); }
    @Test void centralPublicaUltimoEvento() { CentralEventos c = CentralEventos.getInstancia(); c.limparOuvintesParaTeste(); c.publicar(new Evento("PEDIDO", "P1")); assertEquals("PEDIDO", c.getUltimoEvento().getTipo()); }
    @Test void centralRegistraOuvinte() { CentralEventos c = CentralEventos.getInstancia(); c.limparOuvintesParaTeste(); c.registrar(e -> { }); assertEquals(1, c.quantidadeOuvintes()); }
    @Test void centralNotificaOuvinte() { CentralEventos c = CentralEventos.getInstancia(); c.limparOuvintesParaTeste(); final String[] valor = {""}; c.registrar(e -> valor[0] = e.getConteudo()); c.publicar(new Evento("PEDIDO", "P1")); assertEquals("P1", valor[0]); }
    @Test void centralNaoAceitaOuvinteNulo() { assertThrows(IllegalArgumentException.class, () -> CentralEventos.getInstancia().registrar(null)); }
    @Test void eventoMantemTipo() { assertEquals("A", new Evento("A", "B").getTipo()); }
    @Test void eventoMantemConteudo() { assertEquals("B", new Evento("A", "B").getConteudo()); }
}
