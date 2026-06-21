# Hamburgueria - Padrões de Projeto

Projeto didático em Java/Maven para aplicar os padrões GoF em uma hamburgueria. A proposta é representar um ecossistema completo: catálogo, montagem do hambúrguer, carrinho, checkout, cozinha, atendimento, funcionários, pagamento, entrega e relatórios.

> Este projeto foi feito como uma solução própria. O repositório de referência da atividade e o exemplo de outro aluno foram usados apenas para entender o nível de organização esperado, sem copiar implementação.

## Organização por módulos

A versão 4 foi reorganizada com pacotes mais profundos, separando domínio, atendimento, cozinha, financeiro e integrações. A estrutura completa está em `docs/estrutura-pacotes.md`.

| Módulo | Subpacotes | Responsabilidade | Padrões principais |
|---|---|---|---|
| `dominio.catalogo` | `composite`, `iterator`, `flyweight`, `visitor` | Cardápio, seções, itens, imagens e relatórios | Composite, Iterator, Flyweight, Visitor |
| `dominio.montagem` | `builder`, `decorator`, `prototype` | Hambúrguer customizado, adicionais e combos clonáveis | Builder, Decorator, Prototype |
| `dominio.carrinho` | `modelo`, `command`, `memento` | Carrinho, ações reversíveis e restauração de estado | Command, Memento |
| `financeiro` | `strategy`, `interpreter`, `bridge`, `proxy`, `adapter` | Precificação, regras, gateway e adaptação financeira | Strategy, Interpreter, Bridge, Proxy, Adapter |
| `atendimento` | `state`, `observer`, `chain`, `mediator`, `facade` | Pedido, telas, validações, funcionários e totem | State, Observer, Chain, Mediator, Facade |
| `cozinha` | `eventos.singleton`, `equipamentos.factorymethod`, `combos.abstractfactory`, `preparo.templatemethod` | Eventos, estações, equipamentos, combos e receitas | Singleton, Factory Method, Abstract Factory, Template Method |
| `integracao.entrega` | `adapter` | Integração com parceiro externo de entrega | Adapter |

## Padrões aplicados

1. **Composite**: o cardápio é uma árvore com seções e itens.
2. **Iterator**: percorre o cardápio sem expor a estrutura interna.
3. **Flyweight**: imagens reutilizáveis dos produtos ficam centralizadas.
4. **Visitor**: relatórios percorrem o cardápio sem alterar suas classes.
5. **Builder**: monta hambúrgueres personalizados passo a passo.
6. **Decorator**: adicionais alteram dinamicamente descrição e preço do hambúrguer.
7. **Prototype**: combos podem ser clonados para repetir pedidos.
8. **Memento**: carrinho pode salvar e restaurar seu estado.
9. **Command**: ações do carrinho podem ser executadas e desfeitas.
10. **Strategy**: políticas de preço são trocadas sem alterar a calculadora.
11. **Interpreter**: regras matemáticas simples de preço são interpretadas a partir de texto.
12. **Bridge**: método de pagamento é separado do processador externo.
13. **Proxy**: gateway seguro controla acesso ao gateway real.
14. **Adapter**: objetos internos são convertidos para formatos externos.
15. **State**: pedido muda de comportamento conforme sua etapa.
16. **Observer**: telas são notificadas quando o pedido muda.
17. **Chain of Responsibility**: validações passam por uma cadeia.
18. **Mediator**: funcionário não fala diretamente com todos; o mediador coordena a equipe.
19. **Facade**: totem simplifica abertura, montagem e checkout.
20. **Singleton**: central de eventos única para integração entre pagamento e cozinha.
21. **Factory Method**: cada estação cria o equipamento correto.
22. **Abstract Factory**: famílias de combos consistentes, como Smash e Artesanal.
23. **Template Method**: o fluxo de preparo é fixo, mas cada tipo personaliza etapas.

## Testes

A suíte possui **448 casos de teste** em JUnit 5.

Regras seguidas:

- cada teste valida apenas uma coisa;
- há testes de sucesso e de erro;
- não há `if`, `for`, `while` ou `switch` nos testes;
- não há `System.out.print`, `System.err` ou `printStackTrace` no projeto;
- os testes cobrem classes e métodos dos módulos principais.

## Como executar

Pré-requisitos:

- Java JDK 11 ou superior;
- Apache Maven.

Comando:

```bash
mvn clean test
```

## Diagrama

O arquivo `Diagrama-Hamburgueria.png` mostra a visão geral dos módulos e padrões. O fonte do diagrama está em `docs/diagrama.dot`. A árvore detalhada de pacotes está em `docs/estrutura-pacotes.md`.
