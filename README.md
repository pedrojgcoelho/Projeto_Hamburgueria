# Hamburgueria - Padrões de Projeto GoF

Projeto didático em **Java/Maven** que aplica os padrões de projeto GoF em um domínio de **hamburgueria**. A proposta é organizar um sistema com fluxo de negócio completo: cardápio, montagem de hambúrguer, carrinho, atendimento, checkout, cozinha, financeiro, entrega e integrações.

---

## Visão geral

O sistema simula uma hamburgueria com os seguintes fluxos principais:

1. O cliente acessa o cardápio.
2. O cardápio é organizado em seções e produtos.
3. O cliente monta um hambúrguer personalizado.
4. Adicionais alteram dinamicamente o produto.
5. O carrinho registra ações executáveis e reversíveis.
6. O pedido passa por validações de checkout.
7. O atendimento acompanha o estado do pedido.
8. A cozinha recebe eventos e prepara os itens.
9. O financeiro calcula preço, aplica regras e processa pagamento.
10. A entrega adapta o pedido para um parceiro externo.

---

## Diagrama da arquitetura

O diagrama foi gerado em alta resolução para permitir zoom.

Arquivos disponíveis:

| Arquivo | Uso recomendado |
|---|---|
| `Diagrama-Hamburgueria.svg` | Melhor opção para zoom, pois é vetorial. |
| `Diagrama-Hamburgueria.png` | Versão principal renderizada em alta resolução. |
| `docs/estrutura-pacotes.md` | Árvore detalhada dos pacotes e classes. |


---

## Estrutura de pacotes

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

A árvore completa, com todos os arquivos Java, está em:

```text
docs/estrutura-pacotes.md
```

---

## Padrões implementados

| Nº | Padrão | Onde foi aplicado | Ideia no domínio da hamburgueria |
|---:|---|---|---|
| 1 | Composite | `dominio.catalogo.composite` | Cardápio como árvore de seções e itens. |
| 2 | Iterator | `dominio.catalogo.iterator` | Percorrer cardápio sem expor a estrutura interna. |
| 3 | Flyweight | `dominio.catalogo.flyweight` | Reaproveitar imagens/metadados de produtos. |
| 4 | Visitor | `dominio.catalogo.visitor` | Gerar relatórios do cardápio sem alterar suas classes. |
| 5 | Builder | `dominio.montagem.builder` | Montar hambúrguer personalizado passo a passo. |
| 6 | Decorator | `dominio.montagem.decorator` | Adicionar bacon, queijo, molho e outros extras ao hambúrguer. |
| 7 | Prototype | `dominio.montagem.prototype` | Clonar combos e pedidos frequentes. |
| 8 | Command | `dominio.carrinho.command` | Executar e desfazer ações do carrinho. |
| 9 | Memento | `dominio.carrinho.memento` | Salvar e restaurar o estado do carrinho. |
| 10 | Strategy | `financeiro.strategy` | Trocar políticas de preço. |
| 11 | Interpreter | `financeiro.interpreter` | Interpretar regras matemáticas simples de preço. |
| 12 | Bridge | `financeiro.bridge` | Separar formas de pagamento dos processadores. |
| 13 | Proxy | `financeiro.proxy` | Proteger o gateway real de pagamento. |
| 14 | Adapter | `financeiro.adapter` | Converter dados financeiros para formato externo. |
| 15 | State | `atendimento.state` | Alterar comportamento do pedido conforme seu status. |
| 16 | Observer | `atendimento.observer` | Notificar painel do cliente e cozinha quando o pedido muda. |
| 17 | Chain of Responsibility | `atendimento.chain` | Validar cliente, carrinho e pagamento em cadeia. |
| 18 | Mediator | `atendimento.mediator` | Coordenar funcionários da loja sem acoplamento direto. |
| 19 | Facade | `atendimento.facade` | Simplificar o uso do sistema por meio de um totem. |
| 20 | Singleton | `cozinha.eventos.singleton` | Central única de eventos da cozinha. |
| 21 | Factory Method | `cozinha.equipamentos.factorymethod` | Criar estações/equipamentos de preparo. |
| 22 | Abstract Factory | `cozinha.combos.abstractfactory` | Criar famílias consistentes de combos. |
| 23 | Template Method | `cozinha.preparo.templatemethod` | Definir o fluxo fixo de preparo com etapas especializadas. |

---

## Módulos do sistema

### Atendimento

Responsável pelo relacionamento com o cliente e pelo fluxo operacional do pedido.

Pacotes principais:

- `atendimento.facade`: entrada simplificada pelo totem.
- `atendimento.state`: ciclo de vida do pedido.
- `atendimento.observer`: atualização de painéis e monitores.
- `atendimento.chain`: validações do checkout.
- `atendimento.mediator`: comunicação entre atendente, chapeiro e entregador.

### Domínio

Representa as regras centrais de negócio.

Pacotes principais:

- `dominio.catalogo`: cardápio, itens, seções, imagens e visitantes.
- `dominio.montagem`: montagem do hambúrguer, adicionais e combos.
- `dominio.carrinho`: itens selecionados, comandos e histórico.

### Financeiro

Responsável por precificação, regras de desconto e pagamento.

Pacotes principais:

- `financeiro.strategy`: políticas de preço.
- `financeiro.interpreter`: expressões matemáticas.
- `financeiro.bridge`: separação entre forma de pagamento e processador.
- `financeiro.proxy`: controle de acesso ao gateway.
- `financeiro.adapter`: conversão para formato externo.

### Cozinha

Representa o preparo e a operação interna.

Pacotes principais:

- `cozinha.eventos.singleton`: central de eventos.
- `cozinha.equipamentos.factorymethod`: criação de estações.
- `cozinha.combos.abstractfactory`: criação de famílias de combos.
- `cozinha.preparo.templatemethod`: fluxo de preparo.

### Integração

Responsável pela comunicação com serviços externos.

Pacote principal:

- `integracao.entrega.adapter`: adaptação do pedido para parceiros de entrega.

---

## Testes

A suíte possui **448 testes unitários** em JUnit 5.

Regras seguidas:

- cada teste valida apenas uma coisa;
- existem testes de sucesso e testes de erro;
- não há lógica condicional nos testes;
- não há `if`, `for`, `while` ou `switch` nos testes;
- não há `System.out.print`, `System.err` ou `printStackTrace` no código;
- cada módulo principal possui testes específicos;
- os testes cobrem classes e métodos usados na aplicação dos padrões.

---

## Como executar

Pré-requisitos:

- Java JDK 11 ou superior;
- Apache Maven.

Executar a suíte de testes:

```bash
mvn clean test
```

Compilar sem rodar testes:

```bash
mvn clean package -DskipTests
```

---

## Checklist de qualidade

O checklist do projeto está em:

```text
docs/checklist-qualidade.md
```

Resumo:

- 23 padrões GoF implementados;
- 448 testes unitários;
- organização por domínio e subpacote;
- documentação de arquitetura;
- diagrama em SVG e PNG;
- testes simples, sem lógica interna.

---
