package hamburgueria.dominio.montagem.builder;

import hamburgueria.dominio.montagem.decorator.Hamburguer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HamburguerPersonalizado implements Hamburguer {
    private final String nome;
    private final String pao;
    private final String proteina;
    private final String queijo;
    private final List<String> saladas;
    private final List<String> molhos;
    private final double preco;

    public HamburguerPersonalizado(String nome, String pao, String proteina, String queijo,
                                   List<String> saladas, List<String> molhos, double preco) {
        this.nome = nome;
        this.pao = pao;
        this.proteina = proteina;
        this.queijo = queijo;
        this.saladas = new ArrayList<>(saladas);
        this.molhos = new ArrayList<>(molhos);
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getPao() {
        return pao;
    }

    public String getProteina() {
        return proteina;
    }

    public String getQueijo() {
        return queijo;
    }

    public List<String> getSaladas() {
        return Collections.unmodifiableList(saladas);
    }

    public List<String> getMolhos() {
        return Collections.unmodifiableList(molhos);
    }

    public String getDescricao() {
        return nome + " com " + pao + ", " + proteina + " e " + queijo;
    }

    public double getPreco() {
        return preco;
    }
}
