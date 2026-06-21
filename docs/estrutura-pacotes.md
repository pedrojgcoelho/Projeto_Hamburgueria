# Estrutura detalhada de pacotes

Este arquivo descreve a organização completa do projeto, separando o sistema por domínio de negócio e por padrão GoF aplicado. A árvore abaixo acompanha o código presente em `src/main/java/hamburgueria`.

```text
hamburgueria
├── atendimento
│   ├── chain
│   │   ├── ContextoPedido.java
│   │   ├── ResultadoValidacao.java
│   │   ├── ValidadorCarrinho.java
│   │   ├── ValidadorCliente.java
│   │   ├── ValidadorPagamento.java
│   │   ├── ValidadorPedido.java
│   │   └── package-info.java
│   ├── facade
│   │   ├── TotemFacade.java
│   │   └── package-info.java
│   ├── mediator
│   │   ├── Atendente.java
│   │   ├── Chapeiro.java
│   │   ├── Entregador.java
│   │   ├── Funcionario.java
│   │   ├── MediadorLoja.java
│   │   └── package-info.java
│   ├── observer
│   │   ├── MonitorCozinha.java
│   │   ├── ObservadorPedido.java
│   │   ├── PainelCliente.java
│   │   ├── PedidoObservavel.java
│   │   └── package-info.java
│   └── state
│       ├── EstadoEmEntrega.java
│       ├── EstadoEmPreparo.java
│       ├── EstadoFinalizado.java
│       ├── EstadoPedido.java
│       ├── EstadoPronto.java
│       ├── EstadoRecebido.java
│       ├── PedidoAtendimento.java
│       └── package-info.java
├── cozinha
│   ├── combos
│   │   └── abstractfactory
│   │       ├── Acompanhamento.java
│   │       ├── BatataRustica.java
│   │       ├── CarneArtesanal.java
│   │       ├── CarneSmash.java
│   │       ├── ComboArtesanalFactory.java
│   │       ├── ComboFactory.java
│   │       ├── ComboSmashFactory.java
│   │       ├── OnionRings.java
│   │       ├── Pao.java
│   │       ├── PaoAustraliano.java
│   │       ├── PaoBrioche.java
│   │       ├── Proteina.java
│   │       └── package-info.java
│   ├── equipamentos
│   │   └── factorymethod
│   │       ├── Chapa.java
│   │       ├── Equipamento.java
│   │       ├── EstacaoBebidas.java
│   │       ├── EstacaoFrituras.java
│   │       ├── EstacaoGrelhados.java
│   │       ├── EstacaoPreparo.java
│   │       ├── Fritadeira.java
│   │       ├── Liquidificador.java
│   │       └── package-info.java
│   ├── eventos
│   │   └── singleton
│   │       ├── CentralEventos.java
│   │       ├── Evento.java
│   │       ├── OuvinteEvento.java
│   │       └── package-info.java
│   └── preparo
│       └── templatemethod
│           ├── PreparoBatata.java
│           ├── PreparoBebida.java
│           ├── PreparoHamburguer.java
│           ├── PreparoTemplate.java
│           └── package-info.java
├── dominio
│   ├── carrinho
│   │   ├── command
│   │   │   ├── AdicionarProdutoCommand.java
│   │   │   ├── ComandoCarrinho.java
│   │   │   ├── LimparCarrinhoCommand.java
│   │   │   ├── RemoverUltimoProdutoCommand.java
│   │   │   └── package-info.java
│   │   ├── memento
│   │   │   ├── CarrinhoMemento.java
│   │   │   ├── HistoricoCarrinho.java
│   │   │   └── package-info.java
│   │   └── modelo
│   │       ├── Carrinho.java
│   │       └── package-info.java
│   ├── catalogo
│   │   ├── composite
│   │   │   ├── ComponenteCardapio.java
│   │   │   ├── ItemCardapio.java
│   │   │   ├── SecaoCardapio.java
│   │   │   └── package-info.java
│   │   ├── flyweight
│   │   │   ├── BibliotecaImagens.java
│   │   │   ├── ImagemProduto.java
│   │   │   └── package-info.java
│   │   ├── iterator
│   │   │   ├── CardapioIterator.java
│   │   │   └── package-info.java
│   │   └── visitor
│   │       ├── ListadorCardapioVisitor.java
│   │       ├── TotalizadorCardapioVisitor.java
│   │       ├── VisitanteCardapio.java
│   │       └── package-info.java
│   └── montagem
│       ├── builder
│       │   ├── HamburguerBuilder.java
│       │   ├── HamburguerPersonalizado.java
│       │   └── package-info.java
│       ├── decorator
│       │   ├── AdicionalHamburguer.java
│       │   ├── BaconDecorator.java
│       │   ├── CebolaCaramelizadaDecorator.java
│       │   ├── Hamburguer.java
│       │   ├── HamburguerBase.java
│       │   ├── MolhoEspecialDecorator.java
│       │   ├── QueijoExtraDecorator.java
│       │   └── package-info.java
│       └── prototype
│           ├── ComboCliente.java
│           ├── ComboPrototype.java
│           └── package-info.java
├── financeiro
│   ├── adapter
│   │   ├── PagamentoJsonAdapter.java
│   │   ├── PedidoPagamento.java
│   │   └── package-info.java
│   ├── bridge
│   │   ├── Pagamento.java
│   │   ├── PagamentoCartao.java
│   │   ├── PagamentoPix.java
│   │   ├── ProcessadorLocal.java
│   │   ├── ProcessadorOnline.java
│   │   ├── ProcessadorPagamento.java
│   │   └── package-info.java
│   ├── interpreter
│   │   ├── DivisaoExpression.java
│   │   ├── Expressao.java
│   │   ├── InterpretadorRegra.java
│   │   ├── MultiplicacaoExpression.java
│   │   ├── NumeroExpression.java
│   │   ├── SomaExpression.java
│   │   ├── SubtracaoExpression.java
│   │   ├── VariavelExpression.java
│   │   └── package-info.java
│   ├── proxy
│   │   ├── GatewayPagamento.java
│   │   ├── GatewayReal.java
│   │   ├── GatewaySeguroProxy.java
│   │   └── package-info.java
│   └── strategy
│       ├── CalculadoraPreco.java
│       ├── PoliticaPreco.java
│       ├── PrecoCupomFixo.java
│       ├── PrecoHappyHour.java
│       ├── PrecoPadrao.java
│       └── package-info.java
└── integracao
    └── entrega
        └── adapter
            ├── EntregaLegada.java
            ├── EntregaParceiroAdapter.java
            ├── PedidoEntrega.java
            └── package-info.java
```

## Leitura da arquitetura

| Domínio | Pacotes | Padrões aplicados | Papel no sistema |
|---|---|---|---|
| Atendimento | `atendimento.facade`, `atendimento.state`, `atendimento.observer`, `atendimento.chain`, `atendimento.mediator` | Facade, State, Observer, Chain of Responsibility, Mediator | Controla o fluxo do pedido, validações, atendimento no totem e comunicação entre funcionários. |
| Catálogo | `dominio.catalogo.composite`, `iterator`, `flyweight`, `visitor` | Composite, Iterator, Flyweight, Visitor | Organiza o cardápio em seções e produtos, reaproveita imagens e permite relatórios sem alterar as classes do cardápio. |
| Montagem | `dominio.montagem.builder`, `decorator`, `prototype` | Builder, Decorator, Prototype | Monta hambúrgueres personalizados, aplica adicionais e clona combos recorrentes. |
| Carrinho | `dominio.carrinho.modelo`, `command`, `memento` | Command, Memento | Controla itens do carrinho, ações reversíveis e restauração de estado. |
| Financeiro | `financeiro.strategy`, `interpreter`, `bridge`, `proxy`, `adapter` | Strategy, Interpreter, Bridge, Proxy, Adapter | Calcula preços, interpreta regras, processa pagamentos e adapta dados para integrações externas. |
| Cozinha | `cozinha.eventos.singleton`, `equipamentos.factorymethod`, `combos.abstractfactory`, `preparo.templatemethod` | Singleton, Factory Method, Abstract Factory, Template Method | Coordena eventos, estações de preparo, famílias de combos e receitas com etapas fixas. |
| Integração | `integracao.entrega.adapter` | Adapter | Adapta pedidos internos para um sistema legado/parceiro de entrega. |

## Relação com o diagrama

- O fonte editável do diagrama está em `docs/diagrama.dot`.
- A imagem renderizada está na raiz do projeto como `Diagrama-Hamburgueria.png`.
- A estrutura acima serve como checklist para explicar os pacotes durante a apresentação.