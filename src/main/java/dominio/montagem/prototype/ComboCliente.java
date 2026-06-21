package hamburgueria.dominio.montagem.prototype;

import hamburgueria.dominio.montagem.decorator.Hamburguer;

public class ComboCliente implements ComboPrototype {
    private final String nome;
    private final Hamburguer hamburguer;
    private final String bebida;
    private final String acompanhamento;

    public ComboCliente(String nome, Hamburguer hamburguer, String bebida, String acompanhamento) {
        if (hamburguer == null) {
            throw new IllegalArgumentException("Hamburguer obrigatorio");
        }
        this.nome = nome;
        this.hamburguer = hamburguer;
        this.bebida = bebida;
        this.acompanhamento = acompanhamento;
    }

    public String getNome() {
        return nome;
    }

    public Hamburguer getHamburguer() {
        return hamburguer;
    }

    public String getBebida() {
        return bebida;
    }

    public String getAcompanhamento() {
        return acompanhamento;
    }

    public double getPreco() {
        return hamburguer.getPreco() + 12.0;
    }

    public ComboCliente copiar() {
        return new ComboCliente(nome, hamburguer, bebida, acompanhamento);
    }
}
