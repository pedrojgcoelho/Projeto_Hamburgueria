package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.cozinha.equipamentos.factorymethod.Chapa;
import hamburgueria.cozinha.equipamentos.factorymethod.EstacaoBebidas;
import hamburgueria.cozinha.equipamentos.factorymethod.EstacaoFrituras;
import hamburgueria.cozinha.equipamentos.factorymethod.EstacaoGrelhados;
import hamburgueria.cozinha.equipamentos.factorymethod.Fritadeira;
import hamburgueria.cozinha.equipamentos.factorymethod.Liquidificador;
import static org.junit.jupiter.api.Assertions.*;

class FactoryMethodTest {
    @Test void chapaTemNome() { assertEquals("Chapa", new Chapa().nome()); }
    @Test void fritadeiraTemNome() { assertEquals("Fritadeira", new Fritadeira().nome()); }
    @Test void liquidificadorTemNome() { assertEquals("Liquidificador", new Liquidificador().nome()); }
    @Test void estacaoGrelhadosUsaChapa() { assertEquals("grelhando burger", new EstacaoGrelhados().preparar("burger")); }
    @Test void estacaoFriturasUsaFritadeira() { assertEquals("fritando batata", new EstacaoFrituras().preparar("batata")); }
    @Test void estacaoBebidasUsaLiquidificador() { assertEquals("batendo milkshake", new EstacaoBebidas().preparar("milkshake")); }
    @Test void equipamentoOperaItem() { assertEquals("grelhando carne", new Chapa().operar("carne")); }
}
