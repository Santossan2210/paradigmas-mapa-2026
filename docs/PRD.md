# DOCUMENTO DE REQUISITOS DO PRODUTO (PRD) — Índice

**Projeto:** EcoFleet Solutions S.A. — Paradigmas de Linguagens de Programação (MAPA 54/2026)
**Autor:** Antonio Santos
**Elaborado por:** Antonio Santos
**Data:** 08/11/2026
**Versão:** 1.3. A 1.0 era um PRD único; a 1.1 separou em dois; a 1.2 e a 1.3 incorporam a segunda e a terceira revisões.

---

## 1. Por que dois PRDs

Os documentos de origem descrevem **dois trabalhos diferentes**:

- **PROJETO:** um exercício de Programação Orientada a Objetos em Java (abstração, herança, encapsulamento, construtores e polimorfismo), entregue como Tarefas 1, 2 e 3. Não menciona paradigma funcional, processamento paralelo, transformar/selecionar/agregar, GitHub nem execução remota.
- **REQUISITOS:** um relatório técnico sobre o paradigma funcional aplicado a um sistema com cálculos complexos e processamento paralelo, entregue em 5 tópicos, com teste em ambiente remoto gratuito e repositório no GitHub.

A versão 1.0 juntava os dois em um só documento e perdia o foco. Por isso eles foram separados:

| PRD | Escopo | Origem |
|---|---|---|
| [PRD-01 — Sistema de Telemetria EcoFleet](PRD-01-telemetria.md) | Tarefas 1, 2 e 3 (OO em Java) | Somente o PROJETO |
| [PRD-02 — Relatório Técnico: Paradigma Funcional](PRD-02-relatorio-funcional.md) | Tópicos 1 a 5, com escopo obrigatório e complementar separados | Somente o REQUISITOS, mais as premissas do autor |

## 2. Quadro de rastreabilidade

| Origem | Conteúdo | Onde está tratado | Tipo |
|---|---|---|---|
| PROJETO | Tarefa 1 — Modelagem conceitual | PRD-01 §5.1 / §11 | Obrigatório |
| PROJETO | Tarefa 2 — Implementação Java (`Veiculo`, `VanEletrica`, `BicicletaEletrica`, `Main`) | PRD-01 §5.2–5.5 / §11 | Obrigatório |
| PROJETO | Tarefa 3 — Justificativa (10 a 20 linhas) | PRD-01 §5.6 / §11 | Obrigatório |
| PROJETO | Regras RN01, RN02, RN03, RN04, RN06 | PRD-01 §7 | Obrigatório |
| PROJETO | Identificação (nome completo e RA do autor) e ordem do arquivo | PRD-01 §11 | Obrigatório |
| REQUISITOS | Tópico 1 — Explicação conceitual | PRD-02 §5.A.1 / §11.A | Obrigatório |
| REQUISITOS | Tópico 2 — Funcional × imperativo | PRD-02 §5.A.2 / §11.A | Obrigatório |
| REQUISITOS | Tópico 3 — Exemplo prático (transformar, selecionar, agregar) | PRD-02 §5.A.3 / §11.A | Obrigatório |
| REQUISITOS | Autoria de Antonio Santos; teste remoto gratuito; GitHub | PRD-02 §5.A.3 / §11.A | Obrigatório |
| REQUISITOS | Tópico 4 — Aplicabilidade | PRD-02 §5.A.4 / §11.A | Obrigatório |
| REQUISITOS | Tópico 5 — Conclusão crítica | PRD-02 §5.A.5 / §11.A | Obrigatório |
| Antonio Santos | P00 a P05 (cenário EcoFleet, bateria crítica abaixo de 20%, lote 30/70, sequencial = paralelo, imutabilidade, funções puras) | PRD-02 §7.1 | Premissa |
| Antonio Santos | Lote de 10 e de 100.000 viagens, medição de tempo, avaliação preguiçosa visível, GitHub Actions, evidências | PRD-02 §5.B / §11.B | Complementar |

## 3. Correções aplicadas na versão 1.1

| Apontamento da revisão | Correção |
|---|---|
| Mistura indevida de dois projetos | Separação em PRD-01 e PRD-02 |
| Requisitos inventados como regras de negócio (RN05, RN07–RN10) | Movidos para "Premissas da Solução Funcional" (P01–P05) no PRD-02 |
| Execução automática no GitHub tratada como obrigatória | O teste em ambiente remoto gratuito é obrigatório; o GitHub Actions passa a ser uma das formas possíveis (complementar) |
| Relatório limitado a 8 páginas | Trocado por "extensão suficiente para atender integralmente os tópicos exigidos" |
| Falta de rastreabilidade | Quadro de origem incluído aqui e em cada PRD |
| Escopo e critérios sem separação | Escopo e critérios divididos em obrigatório e complementar no PRD-02 |
| Arquitetura pouco clara | Pendência registrada no PRD-02 §15, item 1 (resolvida na 1.2) |

## 3.1 Correções aplicadas na versão 1.2

| Apontamento da revisão | Correção |
|---|---|
| PRD-01 §5.2: valores do construtor implícitos | Tabela com `idVeiculo = 0`, `modelo = ""`, `capacidadeCarga = 0.0`, `autonomiaBateria = 0.0` |
| PRD-01 §11: qualidade implícita | Critérios incluídos: sem erros de compilação e sem exceções em tempo de execução |
| PRD-01 Fluxo 3: cálculo pouco explícito | Fórmula: consumo = distância × taxa; bateria = atual − consumo |
| PRD-02 §5.A.3: linguagem em aberto | Linguagem prioritária: Java (Streams); alternativa: Python, mediante decisão registrada |
| PRD-02 §15: indecisão de arquitetura | Arquitetura recomendada: Java + Streams + relatório acadêmico + GitHub Actions |
| PRD-02 Tópico 4 | Critério incluído: como a imutabilidade e as funções puras favorecem o paralelismo |
| PRD-02 Tópico 5 | Critério incluído: quando o paradigma funcional não é a melhor escolha |
| Ambiente de teste entre PRD-01 e PRD-02 | PRD-01: ambiente Java, pode ser local. PRD-02: o exemplo funcional exige evidência de execução remota gratuita |

## 3.2 Correções aplicadas na versão 1.3

| Apontamento da revisão | Correção |
|---|---|
| PRD-01 §5.2: tabela pouco legível | A tabela de valores iniciais saiu de dentro da lista e virou bloco próprio, com título |
| PRD-02 §15: P00 tratada como pendência | Movida para observação da P00 na §7.1 |
| PRD-02 Tópico 3: documento preso a uma conta GitHub | Agora diz "repositório GitHub do autor"; a conta será registrada em `docs/DECISOES_TECNICAS.md` |

## 4. Decisões de entrega pendentes
1. **Formato da entrega:** saber se as Tarefas 1 a 3 (PRD-01) e os Tópicos 1 a 5 (PRD-02) vão em **um único arquivo** ou em **dois arquivos**. Depende de onde cada atividade é enviada no ambiente virtual.
2. ~~Arquitetura~~ **Resolvido:** Java + Streams + relatório acadêmico + teste remoto pelo GitHub Actions (PRD-02 §15).
3. **Revisão final:** verificação e revisão final pelo autor.
4. **Conta e nome do repositório GitHub** do autor, a registrar em `docs/DECISOES_TECNICAS.md`. O REQUISITOS indica a conta Santossan2210.

## 5. Próximo passo
Com os PRDs aprovados, as decisões técnicas serão registradas em `docs/DECISOES_TECNICAS.md`.
