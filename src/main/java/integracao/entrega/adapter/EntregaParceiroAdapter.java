package hamburgueria.integracao.entrega.adapter;

public class EntregaParceiroAdapter {
    private final EntregaLegada entregaLegada;

    public EntregaParceiroAdapter(EntregaLegada entregaLegada) {
        if (entregaLegada == null) {
            throw new IllegalArgumentException("Servico legado obrigatorio");
        }
        this.entregaLegada = entregaLegada;
    }

    public String enviar(PedidoEntrega pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido obrigatorio");
        }
        String payload = pedido.getCodigo() + "|" + pedido.getEndereco() + "|" + pedido.getPesoKg();
        return entregaLegada.solicitarColeta(payload);
    }
}
