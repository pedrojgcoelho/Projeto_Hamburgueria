package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.cozinha.combos.abstractfactory.BatataRustica;
import hamburgueria.cozinha.combos.abstractfactory.CarneArtesanal;
import hamburgueria.cozinha.combos.abstractfactory.CarneSmash;
import hamburgueria.cozinha.combos.abstractfactory.ComboArtesanalFactory;
import hamburgueria.cozinha.combos.abstractfactory.ComboSmashFactory;
import hamburgueria.cozinha.combos.abstractfactory.OnionRings;
import hamburgueria.cozinha.combos.abstractfactory.PaoAustraliano;
import hamburgueria.cozinha.combos.abstractfactory.PaoBrioche;
import hamburgueria.cozinha.equipamentos.factorymethod.Chapa;
import hamburgueria.cozinha.equipamentos.factorymethod.EstacaoBebidas;
import hamburgueria.cozinha.equipamentos.factorymethod.EstacaoFrituras;
import hamburgueria.cozinha.equipamentos.factorymethod.EstacaoGrelhados;
import hamburgueria.cozinha.equipamentos.factorymethod.Fritadeira;
import hamburgueria.cozinha.equipamentos.factorymethod.Liquidificador;
import hamburgueria.cozinha.eventos.singleton.CentralEventos;
import hamburgueria.cozinha.eventos.singleton.Evento;
import hamburgueria.cozinha.preparo.templatemethod.PreparoBatata;
import hamburgueria.cozinha.preparo.templatemethod.PreparoBebida;
import hamburgueria.cozinha.preparo.templatemethod.PreparoHamburguer;
import hamburgueria.integracao.entrega.adapter.EntregaLegada;
import hamburgueria.integracao.entrega.adapter.EntregaParceiroAdapter;
import hamburgueria.integracao.entrega.adapter.PedidoEntrega;
import static org.junit.jupiter.api.Assertions.*;

class CozinhaEntregaMaisTest {
    @Test void batataRusticaRetornaTipo() { assertEquals("Batata rustica", new BatataRustica().tipo()); }
    @Test void onionRingsRetornaTipo() { assertEquals("Onion rings", new OnionRings().tipo()); }
    @Test void carneSmashRetornaTipo() { assertEquals("Carne smash", new CarneSmash().tipo()); }
    @Test void carneArtesanalRetornaTipo() { assertEquals("Carne artesanal", new CarneArtesanal().tipo()); }
    @Test void paoBriocheRetornaTipo() { assertEquals("Brioche", new PaoBrioche().tipo()); }
    @Test void paoAustralianoRetornaTipo() { assertEquals("Australiano", new PaoAustraliano().tipo()); }
    @Test void comboSmashCriaPaoBrioche() { assertEquals("Brioche", new ComboSmashFactory().criarPao().tipo()); }
    @Test void comboSmashCriaCarneSmash() { assertEquals("Carne smash", new ComboSmashFactory().criarProteina().tipo()); }
    @Test void comboSmashCriaBatata() { assertEquals("Batata rustica", new ComboSmashFactory().criarAcompanhamento().tipo()); }
    @Test void comboArtesanalCriaPaoAustraliano() { assertEquals("Australiano", new ComboArtesanalFactory().criarPao().tipo()); }
    @Test void comboArtesanalCriaCarneArtesanal() { assertEquals("Carne artesanal", new ComboArtesanalFactory().criarProteina().tipo()); }
    @Test void comboArtesanalCriaOnion() { assertEquals("Onion rings", new ComboArtesanalFactory().criarAcompanhamento().tipo()); }
    @Test void chapaRetornaNome() { assertEquals("Chapa", new Chapa().nome()); }
    @Test void chapaOperaItem() { assertEquals("grelhando carne", new Chapa().operar("carne")); }
    @Test void fritadeiraRetornaNome() { assertEquals("Fritadeira", new Fritadeira().nome()); }
    @Test void fritadeiraOperaItem() { assertEquals("fritando batata", new Fritadeira().operar("batata")); }
    @Test void liquidificadorRetornaNome() { assertEquals("Liquidificador", new Liquidificador().nome()); }
    @Test void liquidificadorOperaItem() { assertEquals("batendo milkshake", new Liquidificador().operar("milkshake")); }
    @Test void estacaoGrelhadosPreparaNaChapa() { assertEquals("grelhando burger", new EstacaoGrelhados().preparar("burger")); }
    @Test void estacaoFriturasPreparaNaFritadeira() { assertEquals("fritando batata", new EstacaoFrituras().preparar("batata")); }
    @Test void estacaoBebidasPreparaNoLiquidificador() { assertEquals("batendo suco", new EstacaoBebidas().preparar("suco")); }
    @Test void eventoRetornaTipo() { assertEquals("COZINHA", new Evento("COZINHA", "pedido").getTipo()); }
    @Test void eventoRetornaConteudo() { assertEquals("pedido", new Evento("COZINHA", "pedido").getConteudo()); }
    @Test void centralEventosEhSingleton() { assertSame(CentralEventos.getInstancia(), CentralEventos.getInstancia()); }
    @Test void centralEventosIniciaLimpaAposLimpeza() { CentralEventos c = CentralEventos.getInstancia(); c.limparOuvintesParaTeste(); assertEquals(0, c.quantidadeOuvintes()); }
    @Test void centralEventosNaoAceitaOuvinteNulo() { CentralEventos c = CentralEventos.getInstancia(); c.limparOuvintesParaTeste(); assertThrows(IllegalArgumentException.class, () -> c.registrar(null)); }
    @Test void centralEventosRegistraOuvinte() { CentralEventos c = CentralEventos.getInstancia(); c.limparOuvintesParaTeste(); c.registrar(e -> e.getTipo()); assertEquals(1, c.quantidadeOuvintes()); }
    @Test void centralEventosGuardaUltimoEvento() { CentralEventos c = CentralEventos.getInstancia(); c.limparOuvintesParaTeste(); Evento e = new Evento("TIPO", "texto"); c.publicar(e); assertSame(e, c.getUltimoEvento()); }
    @Test void centralEventosUltimoEventoNuloAposLimpeza() { CentralEventos c = CentralEventos.getInstancia(); c.limparOuvintesParaTeste(); assertNull(c.getUltimoEvento()); }
    @Test void preparoHamburguerTemQuatroEtapas() { assertEquals(4, new PreparoHamburguer().executar("X").size()); }
    @Test void preparoBatataTemQuatroEtapas() { assertEquals(4, new PreparoBatata().executar("batata").size()); }
    @Test void preparoBebidaTemQuatroEtapas() { assertEquals(4, new PreparoBebida().executar("suco").size()); }
    @Test void preparoHamburguerCozinhaCarne() { assertEquals("selar carne do X", new PreparoHamburguer().executar("X").get(1)); }
    @Test void preparoBatataCozinhaFritando() { assertEquals("fritar batata", new PreparoBatata().executar("batata").get(1)); }
    @Test void preparoBebidaCozinhaMisturando() { assertEquals("misturar suco", new PreparoBebida().executar("suco").get(1)); }
    @Test void preparoPrimeiraEtapaSepara() { assertEquals("separar X", new PreparoHamburguer().executar("X").get(0)); }
    @Test void preparoTerceiraEtapaMonta() { assertEquals("montar X", new PreparoHamburguer().executar("X").get(2)); }
    @Test void preparoQuartaEtapaEmbala() { assertEquals("embalar X", new PreparoHamburguer().executar("X").get(3)); }
    @Test void preparoNaoAceitaItemNulo() { assertThrows(IllegalArgumentException.class, () -> new PreparoHamburguer().executar(null)); }
    @Test void preparoNaoAceitaItemVazio() { assertThrows(IllegalArgumentException.class, () -> new PreparoHamburguer().executar(" ")); }
    @Test void preparoRetornaListaImutavel() { assertThrows(UnsupportedOperationException.class, () -> new PreparoHamburguer().executar("X").add("extra")); }
    @Test void pedidoEntregaRetornaCodigo() { assertEquals("P1", new PedidoEntrega("P1", "Rua A", 2.5).getCodigo()); }
    @Test void pedidoEntregaRetornaEndereco() { assertEquals("Rua A", new PedidoEntrega("P1", "Rua A", 2.5).getEndereco()); }
    @Test void pedidoEntregaRetornaPeso() { assertEquals(2.5, new PedidoEntrega("P1", "Rua A", 2.5).getPesoKg(), 0.001); }
    @Test void entregaLegadaNaoAceitaPayloadNulo() { assertThrows(IllegalArgumentException.class, () -> new EntregaLegada().solicitarColeta(null)); }
    @Test void entregaLegadaNaoAceitaPayloadVazio() { assertThrows(IllegalArgumentException.class, () -> new EntregaLegada().solicitarColeta(" ")); }
    @Test void entregaLegadaRetornaColetaComTamanho() { assertEquals("COLETA:3", new EntregaLegada().solicitarColeta("abc")); }
    @Test void adapterNaoAceitaServicoNulo() { assertThrows(IllegalArgumentException.class, () -> new EntregaParceiroAdapter(null)); }
    @Test void adapterNaoAceitaPedidoNulo() { assertThrows(IllegalArgumentException.class, () -> new EntregaParceiroAdapter(new EntregaLegada()).enviar(null)); }
    @Test void adapterEnviaPedidoParaLegado() { assertTrue(new EntregaParceiroAdapter(new EntregaLegada()).enviar(new PedidoEntrega("P1", "Rua A", 2.0)).startsWith("COLETA:")); }
    @Test void adapterCalculaPayloadComCodigoEnderecoEPeso() { assertEquals("COLETA:10", new EntregaParceiroAdapter(new EntregaLegada()).enviar(new PedidoEntrega("P1", "Rua", 2.0))); }
}
