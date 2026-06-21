package hamburgueria.integracao.entrega.adapter;

public class EntregaLegada {
    public String solicitarColeta(String payload) {
        if (payload == null || payload.isBlank()) {
            throw new IllegalArgumentException("Payload obrigatorio");
        }
        return "COLETA:" + payload.length();
    }
}
