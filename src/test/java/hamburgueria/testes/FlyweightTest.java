package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.dominio.catalogo.flyweight.BibliotecaImagens;
import static org.junit.jupiter.api.Assertions.*;

class FlyweightTest {
    @Test void bibliotecaReutilizaMesmaImagem() { BibliotecaImagens b = new BibliotecaImagens(); assertSame(b.obter("burger"), b.obter("burger")); }
    @Test void bibliotecaCriaImagensDiferentes() { BibliotecaImagens b = new BibliotecaImagens(); assertNotSame(b.obter("burger"), b.obter("batata")); }
    @Test void bibliotecaContaCacheUnico() { BibliotecaImagens b = new BibliotecaImagens(); b.obter("burger"); b.obter("burger"); assertEquals(1, b.quantidadeEmCache()); }
    @Test void bibliotecaContaDuasChaves() { BibliotecaImagens b = new BibliotecaImagens(); b.obter("burger"); b.obter("batata"); assertEquals(2, b.quantidadeEmCache()); }
    @Test void imagemMantemChave() { BibliotecaImagens b = new BibliotecaImagens(); assertEquals("burger", b.obter("burger").getChave()); }
    @Test void imagemTemConteudoBase64() { BibliotecaImagens b = new BibliotecaImagens(); assertEquals("base64:burger", b.obter("burger").getConteudoBase64()); }
    @Test void bibliotecaNaoAceitaChaveVazia() { BibliotecaImagens b = new BibliotecaImagens(); assertThrows(IllegalArgumentException.class, () -> b.obter("")); }
}
