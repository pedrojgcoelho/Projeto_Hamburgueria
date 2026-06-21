package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.cozinha.combos.abstractfactory.ComboArtesanalFactory;
import hamburgueria.cozinha.combos.abstractfactory.ComboSmashFactory;
import static org.junit.jupiter.api.Assertions.*;

class AbstractFactoryTest {
    @Test void smashCriaPaoBrioche() { assertEquals("Brioche", new ComboSmashFactory().criarPao().tipo()); }
    @Test void smashCriaCarneSmash() { assertEquals("Carne smash", new ComboSmashFactory().criarProteina().tipo()); }
    @Test void smashCriaBatataRustica() { assertEquals("Batata rustica", new ComboSmashFactory().criarAcompanhamento().tipo()); }
    @Test void artesanalCriaPaoAustraliano() { assertEquals("Australiano", new ComboArtesanalFactory().criarPao().tipo()); }
    @Test void artesanalCriaCarneArtesanal() { assertEquals("Carne artesanal", new ComboArtesanalFactory().criarProteina().tipo()); }
    @Test void artesanalCriaOnionRings() { assertEquals("Onion rings", new ComboArtesanalFactory().criarAcompanhamento().tipo()); }
    @Test void fabricasCriamFamiliasDiferentes() { assertNotEquals(new ComboSmashFactory().criarPao().tipo(), new ComboArtesanalFactory().criarPao().tipo()); }
}
