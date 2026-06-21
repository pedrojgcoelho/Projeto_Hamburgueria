package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.montagem.decorator.HamburguerBase;
import hamburgueria.dominio.montagem.prototype.ComboCliente;
import static org.junit.jupiter.api.Assertions.*;

class PrototypeTest {
    private ComboCliente combo() { return new ComboCliente("Combo 1", new HamburguerBase("X", 20), "cola", "batata"); }
    @Test void copiaNaoEhMesmoObjeto() { assertNotSame(combo(), combo().copiar()); }
    @Test void copiaMantemNome() { assertEquals("Combo 1", combo().copiar().getNome()); }
    @Test void copiaMantemBebida() { assertEquals("cola", combo().copiar().getBebida()); }
    @Test void copiaMantemAcompanhamento() { assertEquals("batata", combo().copiar().getAcompanhamento()); }
    @Test void comboCalculaPreco() { assertEquals(32.0, combo().getPreco(), 0.001); }
    @Test void comboRetornaHamburguer() { assertEquals("X", combo().getHamburguer().getNome()); }
    @Test void comboExigeHamburguer() { assertThrows(IllegalArgumentException.class, () -> new ComboCliente("C", null, "cola", "batata")); }
}
