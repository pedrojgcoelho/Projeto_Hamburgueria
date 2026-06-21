package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.atendimento.mediator.Atendente;
import hamburgueria.atendimento.mediator.Chapeiro;
import hamburgueria.atendimento.mediator.Entregador;
import hamburgueria.atendimento.mediator.MediadorLoja;
import static org.junit.jupiter.api.Assertions.*;

class MediatorTest {
    @Test void atendenteTemFuncao() { assertEquals("ATENDIMENTO", new Atendente("Ana", new MediadorLoja()).funcao()); }
    @Test void chapeiroTemFuncao() { assertEquals("CHAPA", new Chapeiro("Beto", new MediadorLoja()).funcao()); }
    @Test void entregadorTemFuncao() { assertEquals("ENTREGA", new Entregador("Caio", new MediadorLoja()).funcao()); }
    @Test void mediadorRecebeMensagem() { MediadorLoja m = new MediadorLoja(); new Atendente("Ana", m).enviar("pedido novo"); assertEquals(1, m.getMensagens().size()); }
    @Test void mensagemIncluiFuncionario() { MediadorLoja m = new MediadorLoja(); new Atendente("Ana", m).enviar("pedido novo"); assertEquals("Ana:pedido novo", m.getMensagens().get(0)); }
    @Test void funcionarioRetornaNome() { assertEquals("Ana", new Atendente("Ana", new MediadorLoja()).getNome()); }
    @Test void mediadorIniciaSemMensagens() { assertEquals(0, new MediadorLoja().getMensagens().size()); }
}
