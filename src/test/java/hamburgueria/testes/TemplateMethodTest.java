package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.cozinha.preparo.templatemethod.PreparoBatata;
import hamburgueria.cozinha.preparo.templatemethod.PreparoBebida;
import hamburgueria.cozinha.preparo.templatemethod.PreparoHamburguer;
import static org.junit.jupiter.api.Assertions.*;

class TemplateMethodTest {
    @Test void preparoTemQuatroEtapas() { assertEquals(4, new PreparoHamburguer().executar("burger").size()); }
    @Test void preparoPrimeiraEtapaSepara() { assertEquals("separar burger", new PreparoHamburguer().executar("burger").get(0)); }
    @Test void hamburguerPersonalizaCozimento() { assertEquals("selar carne do burger", new PreparoHamburguer().executar("burger").get(1)); }
    @Test void batataPersonalizaCozimento() { assertEquals("fritar batata", new PreparoBatata().executar("batata").get(1)); }
    @Test void bebidaPersonalizaCozimento() { assertEquals("misturar suco", new PreparoBebida().executar("suco").get(1)); }
    @Test void preparoUltimaEtapaEmbala() { assertEquals("embalar burger", new PreparoHamburguer().executar("burger").get(3)); }
    @Test void preparoNaoAceitaItemVazio() { assertThrows(IllegalArgumentException.class, () -> new PreparoHamburguer().executar("")); }
}
