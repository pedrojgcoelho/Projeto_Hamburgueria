package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.montagem.decorator.BaconDecorator;
import hamburgueria.dominio.montagem.decorator.CebolaCaramelizadaDecorator;
import hamburgueria.dominio.montagem.decorator.Hamburguer;
import hamburgueria.dominio.montagem.decorator.HamburguerBase;
import hamburgueria.dominio.montagem.decorator.MolhoEspecialDecorator;
import hamburgueria.dominio.montagem.decorator.QueijoExtraDecorator;
import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {
    @Test void baseRetornaPreco() { assertEquals(20.0, new HamburguerBase("X", 20).getPreco(), 0.001); }
    @Test void baconSomaPreco() { assertEquals(24.5, new BaconDecorator(new HamburguerBase("X", 20)).getPreco(), 0.001); }
    @Test void queijoSomaPreco() { assertEquals(23.25, new QueijoExtraDecorator(new HamburguerBase("X", 20)).getPreco(), 0.001); }
    @Test void cebolaSomaPreco() { assertEquals(22.75, new CebolaCaramelizadaDecorator(new HamburguerBase("X", 20)).getPreco(), 0.001); }
    @Test void molhoSomaPreco() { assertEquals(21.8, new MolhoEspecialDecorator(new HamburguerBase("X", 20)).getPreco(), 0.001); }
    @Test void decoradoresPodemSerCombinados() { Hamburguer h = new BaconDecorator(new QueijoExtraDecorator(new HamburguerBase("X", 20))); assertEquals(27.75, h.getPreco(), 0.001); }
    @Test void decoratorNaoAceitaHamburguerNulo() { assertThrows(IllegalArgumentException.class, () -> new BaconDecorator(null)); }
}
