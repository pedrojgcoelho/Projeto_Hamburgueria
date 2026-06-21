package hamburgueria.dominio.montagem.builder;

import hamburgueria.cozinha.combos.abstractfactory.Pao;
import java.util.ArrayList;
import java.util.List;

public class HamburguerBuilder {
    private String nome = "Burger personalizado";
    private String pao;
    private String proteina;
    private String queijo = "sem queijo";
    private final List<String> saladas = new ArrayList<>();
    private final List<String> molhos = new ArrayList<>();
    private double preco = 10.0;

    public HamburguerBuilder chamado(String nome) {
        this.nome = nome;
        return this;
    }

    public HamburguerBuilder comPao(String pao) {
        this.pao = pao;
        return this;
    }

    public HamburguerBuilder comProteina(String proteina) {
        this.proteina = proteina;
        return this;
    }

    public HamburguerBuilder comQueijo(String queijo) {
        this.queijo = queijo;
        this.preco += 3.0;
        return this;
    }

    public HamburguerBuilder comSalada(String salada) {
        saladas.add(salada);
        this.preco += 1.0;
        return this;
    }

    public HamburguerBuilder comMolho(String molho) {
        molhos.add(molho);
        this.preco += 0.5;
        return this;
    }

    public HamburguerPersonalizado montar() {
        if (pao == null || proteina == null) {
            throw new IllegalStateException("Pao e proteina sao obrigatorios");
        }
        return new HamburguerPersonalizado(nome, pao, proteina, queijo, saladas, molhos, preco);
    }
}
