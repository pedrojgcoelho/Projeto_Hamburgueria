package hamburgueria.atendimento.state;

public class PedidoAtendimento {
    private EstadoPedido estado = new EstadoRecebido();
    private final String codigo;

    public PedidoAtendimento(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Codigo obrigatorio");
        }
        this.codigo = codigo;
    }

    public void avancar() {
        estado.proximo(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return estado.nome();
    }

    public String getCodigo() {
        return codigo;
    }
}
