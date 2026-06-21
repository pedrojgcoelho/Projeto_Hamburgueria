package hamburgueria.atendimento.observer;

public class PainelCliente implements ObservadorPedido {
    private String ultimaMensagem = "";

    public void atualizar(String codigoPedido, String status) {
        ultimaMensagem = codigoPedido + ":" + status;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }
}
