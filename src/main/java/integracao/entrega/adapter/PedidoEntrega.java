package hamburgueria.integracao.entrega.adapter;

public class PedidoEntrega {
    private final String codigo;
    private final String endereco;
    private final double pesoKg;

    public PedidoEntrega(String codigo, String endereco, double pesoKg) {
        this.codigo = codigo;
        this.endereco = endereco;
        this.pesoKg = pesoKg;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEndereco() {
        return endereco;
    }

    public double getPesoKg() {
        return pesoKg;
    }
}
