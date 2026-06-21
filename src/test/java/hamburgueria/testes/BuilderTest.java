package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.montagem.builder.HamburguerBuilder;
import hamburgueria.dominio.montagem.builder.HamburguerPersonalizado;
import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {
    @Test void builderMontaNome() { HamburguerPersonalizado h = new HamburguerBuilder().chamado("Casa").comPao("brioche").comProteina("carne").montar(); assertEquals("Casa", h.getNome()); }
    @Test void builderMontaPao() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("australiano").comProteina("frango").montar(); assertEquals("australiano", h.getPao()); }
    @Test void builderMontaProteina() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").montar(); assertEquals("carne", h.getProteina()); }
    @Test void builderAdicionaQueijo() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comQueijo("cheddar").montar(); assertEquals("cheddar", h.getQueijo()); }
    @Test void builderAdicionaSalada() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comSalada("alface").montar(); assertEquals(1, h.getSaladas().size()); }
    @Test void builderAdicionaMolho() { HamburguerPersonalizado h = new HamburguerBuilder().comPao("brioche").comProteina("carne").comMolho("barbecue").montar(); assertEquals(1, h.getMolhos().size()); }
    @Test void builderExigePaoEProteina() { assertThrows(IllegalStateException.class, () -> new HamburguerBuilder().montar()); }
}
