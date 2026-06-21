package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.financeiro.strategy.CalculadoraPreco;
import hamburgueria.financeiro.strategy.PrecoCupomFixo;
import hamburgueria.financeiro.strategy.PrecoHappyHour;
import hamburgueria.financeiro.strategy.PrecoPadrao;
import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {
    @Test void precoPadraoMantemSubtotal() { assertEquals(50.0, new PrecoPadrao().aplicar(50), 0.001); }
    @Test void happyHourAplicaDesconto() { assertEquals(45.0, new PrecoHappyHour().aplicar(50), 0.001); }
    @Test void cupomFixoDescontaValor() { assertEquals(40.0, new PrecoCupomFixo(10).aplicar(50), 0.001); }
    @Test void cupomNaoDeixaNegativo() { assertEquals(0.0, new PrecoCupomFixo(100).aplicar(50), 0.001); }
    @Test void calculadoraUsaPoliticaInicial() { assertEquals(45.0, new CalculadoraPreco(new PrecoHappyHour()).calcular(50), 0.001); }
    @Test void calculadoraTrocaPolitica() { CalculadoraPreco c = new CalculadoraPreco(new PrecoPadrao()); c.setPolitica(new PrecoCupomFixo(5)); assertEquals(45.0, c.calcular(50), 0.001); }
    @Test void calculadoraNaoAceitaPoliticaNula() { assertThrows(IllegalArgumentException.class, () -> new CalculadoraPreco(null)); }
}
