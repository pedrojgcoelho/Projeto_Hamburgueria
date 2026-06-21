# Estrutura de pacotes

A versão 4 foi reorganizada para ficar mais próxima de um projeto real. Em vez de deixar todos os padrões dentro de poucos pacotes grandes, cada domínio foi dividido por responsabilidade e por padrão aplicado.

```text
hamburgueria
├── atendimento
│   ├── chain
│   ├── facade
│   ├── mediator
│   ├── observer
│   └── state
├── cozinha
│   ├── combos/abstractfactory
│   ├── equipamentos/factorymethod
│   ├── eventos/singleton
│   └── preparo/templatemethod
├── dominio
│   ├── carrinho
│   │   ├── command
│   │   ├── memento
│   │   └── modelo
│   ├── catalogo
│   │   ├── composite
│   │   ├── flyweight
│   │   ├── iterator
│   │   └── visitor
│   └── montagem
│       ├── builder
│       ├── decorator
│       └── prototype
├── financeiro
│   ├── adapter
│   ├── bridge
│   ├── interpreter
│   ├── proxy
│   └── strategy
└── integracao
    └── entrega/adapter
```

## Critério de separação

- `dominio`: regra principal da hamburgueria, como cardápio, montagem do produto e carrinho.
- `atendimento`: fluxo operacional do pedido e comunicação entre funcionários.
- `cozinha`: fabricação, equipamentos, estações, receitas e combos.
- `financeiro`: preço, pagamento, gateway, regras e integrações financeiras.
- `integracao`: adaptação para serviços externos, como entrega.

Essa divisão evita pacotes genéricos demais e deixa evidente onde cada padrão foi aplicado.
