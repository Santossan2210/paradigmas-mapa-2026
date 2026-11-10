# DOCUMENTO DE REQUISITOS DO PRODUTO (PRD) 02 — Relatório Técnico: Paradigma Funcional

**Projeto:** Paradigma Funcional aplicado ao Processamento de Dados de Telemetria — EcoFleet Solutions S.A.
**Autor:** Antonio Santos
**Elaborado por:** Antonio Santos
**Data:** 08/11/2026
**Versão:** 1.3
**Documento de origem:** REQUISITOS PARA DESENVOLVIMENTO DO PROJETO (= "Orientação para a Atividade de Estudos MAPA", mais as instruções do autor)
**Índice geral:** [PRD.md](PRD.md) · **PRD base:** [PRD-01-telemetria.md](PRD-01-telemetria.md)

> Este PRD separa claramente o **escopo obrigatório**, que vem do documento REQUISITOS, do **escopo complementar**, que é proposta do autor. Os itens complementares são apoio e podem ser retirados sem descumprir o enunciado.

---

## 1. Visão Geral do Produto

Este produto é um **relatório técnico** que analisa o uso do paradigma funcional em um sistema que precisa fazer cálculos complexos e processar dados em paralelo. O relatório é organizado em **5 tópicos** e inclui um **exemplo prático de processamento de dados** com código, testado em ambiente remoto gratuito e guardado no GitHub.

O documento REQUISITOS não descreve um cenário concreto. Por isso, o autor usa como cenário a **telemetria da frota EcoFleet** definida no [PRD-01](PRD-01-telemetria.md): o processamento de um grande volume de viagens de vans e bicicletas elétricas. Essa é a premissa P00.

| Origem | Conteúdo |
|---|---|
| REQUISITOS — Tópico 1 | Explicação conceitual: paradigma funcional, imutabilidade, funções puras, avaliação preguiçosa e funções de ordem superior |
| REQUISITOS — Tópico 2 | Comparação entre funcional e imperativo, com vantagens e desvantagens no contexto do problema |
| REQUISITOS — Tópico 3 | Exemplo prático (transformar, selecionar, agregar), com linguagem informada, explicação e conceitos apontados |
| REQUISITOS — Tópico 3 (instruções do autor) | Autoria de Antonio Santos; teste em ambiente remoto gratuito; repositório no GitHub |
| REQUISITOS — Tópico 4 | Aplicabilidade ao cenário: paralelismo, volume, pipelines, efeitos colaterais, previsibilidade e confiabilidade |
| REQUISITOS — Tópico 5 | Conclusão crítica: desempenho, manutenção, experiência da equipe, curva de aprendizado e adequação |
| PRD-01 (PROJETO) | Cenário e regras de consumo reaproveitados (RN02, RN03, RN04) |
| Antonio Santos | Premissas P00 a P05 e escopo complementar |

## 2. Problema que o Sistema Resolve
O enunciado descreve um sistema que precisa **fazer cálculos complexos e processar dados em paralelo**, mantendo a consistência e reduzindo efeitos colaterais. O relatório precisa mostrar, com argumentos e com um exemplo funcional, se o paradigma funcional é viável para esse cenário e por quê.

## 3. Objetivos do Sistema

**Objetivo principal**
Demonstrar, de forma conceitual e prática, como o paradigma funcional se aplica ao processamento de dados em paralelo, e avaliar criticamente sua viabilidade.

**Objetivos específicos**
1. Explicar as quatro características pedidas e por que elas importam.
2. Comparar o funcional com o imperativo dentro do problema.
3. Apresentar um exemplo funcional de processamento de dados, explicado.
4. Relacionar os benefícios ao cenário de cálculo paralelo.
5. Concluir de forma crítica, apoiada nos argumentos anteriores.
6. Testar o código em ambiente remoto gratuito e guardá-lo no GitHub.

## 4. Personas e Perfis de Usuário

| Perfil | Descrição | Principais ações | Permissões básicas |
|---|---|---|---|
| Avaliador (professor) | Corrige a entrega pelo checklist | Ler o relatório, consultar o repositório e a evidência de teste | Somente leitura |
| Equipe técnica da EcoFleet (fictícia) | Decide se adota o paradigma funcional | Ler a análise e os resultados do exemplo | Somente leitura |
| Autor (Antonio Santos) | Redige, desenvolve e testa | Escrever, codificar, executar, revisar e assinar | Acesso total |

## 5. Escopo da Primeira Versão

### 5.A Escopo obrigatório (vem do REQUISITOS)

**5.A.1 Tópico 1 — Explicação conceitual**
- O que é o paradigma funcional.
- Imutabilidade: dados que não mudam depois de criados.
- Funções puras: mesma entrada, mesmo resultado, sem efeitos colaterais.
- Avaliação preguiçosa: calcular uma expressão só quando o resultado é necessário.
- Funções de ordem superior: funções que recebem ou devolvem outras funções.
- Para cada característica, explicar por que ela importa, sem apenas defini-la.

**5.A.2 Tópico 2 — Funcional × imperativo**
- Comparação por aspecto: organização, estado, concorrência e aprendizado.
- Vantagens e desvantagens de cada um, sempre ligadas ao problema.

**5.A.3 Tópico 3 — Exemplo prático**
- Linguagem adequada, informada no texto:
  - **Linguagem prioritária: Java**, com recursos funcionais (Streams, expressões lambda e records imutáveis), pela coerência com o PRD-01.
  - **Alternativa:** Python, somente mediante decisão registrada em `vibe-decisoes-tecnicas`.
- Estrutura sugerida pelo REQUISITOS e adotada aqui: dados de entrada → transformar (MAP) → selecionar (FILTER) → agregar (REDUCE) → resultado final.
- Explicação do funcionamento e indicação de onde aparece cada conceito funcional.
- Código e texto com a **autoria e o estilo pessoal de Antonio Santos**, revisados e assumidos pelo autor.
- **Teste em ambiente remoto gratuito**, com evidência de execução do exemplo funcional incluída no relatório.
- **Código armazenado em repositório GitHub do autor.** A conta e o nome do repositório serão registrados em `vibe-decisoes-tecnicas`.

**5.A.4 Tópico 4 — Aplicabilidade**
- Análise ligada ao cenário (cálculos complexos em paralelo, com consistência e menos efeitos colaterais), considerando processamento paralelo, grandes volumes, pipelines de transformação, redução de efeitos colaterais, previsibilidade e confiabilidade.
- Explicar explicitamente **como a imutabilidade e as funções puras favorecem o processamento paralelo**: sem estado compartilhado mutável, não há disputa entre tarefas nem necessidade de bloqueios, e o resultado não depende da ordem de execução.

**5.A.5 Tópico 5 — Conclusão crítica**
- Viabilidade considerando desempenho, manutenção, experiência da equipe, curva de aprendizado e adequação ao cenário.
- Usar os argumentos dos tópicos anteriores, sem repeti-los.
- Indicar **quando o paradigma funcional não é a melhor escolha**. Exemplos: volumes pequenos, em que o custo do paralelismo supera o ganho; regras fortemente dependentes de estado, como o registro sequencial de viagens do PRD-01; equipe sem familiaridade com o paradigma.

**5.A.6 Relatório**
- Os cinco tópicos identificados.
- **Extensão suficiente para atender integralmente os tópicos exigidos.**
- Referência a TOKUMOTO (2022), sem cópia literal de textos.

### 5.B Escopo complementar (proposta do autor)
São itens opcionais. Servem para dar evidência concreta aos Tópicos 3, 4 e 5.

| Item | Para que serve | Tópico apoiado |
|---|---|---|
| Lote simulado: 10 viagens exibidas e 100.000 viagens só resumidas | Dar volume real para discutir paralelismo | 3 e 4 |
| Seleção de viagens com bateria crítica (P01) | Dar sentido de negócio à etapa de seleção | 3 |
| Execução sequencial e paralela com resultado idêntico (P03) | Comprovar previsibilidade sem estado compartilhado | 4 |
| Medição de tempo sequencial × paralelo (ms) | Fundamentar a discussão de desempenho | 5 |
| Demonstração visível da avaliação preguiçosa na saída | Mostrar o conceito na prática, não só na teoria | 1 e 3 |
| Teste remoto feito com GitHub Actions | Forma adotada na arquitetura recomendada (§15) para cumprir o teste remoto obrigatório | 3 |
| Saída de execução e imagem do teste remoto no relatório | Prova de execução sem o avaliador precisar rodar nada | 3 |

## 6. Funcionalidades Fora de Escopo
- Dados reais de sensores ou GPS, interface gráfica e armazenamento permanente.
- Testes automatizados de unidade.
- Exemplos em várias linguagens. Python fica apenas como alternativa à linguagem prioritária (§5.A.3).
- Recarga, rotas e previsão de autonomia.

## 7. Regras de Negócio

As regras de cálculo reaproveitadas vêm do PRD-01 e do documento PROJETO:

| Código | Regra | Origem |
|---|---|---|
| RN02 | A van consome 0.5 ponto percentual por km. | PROJETO (via PRD-01) |
| RN03 | A bicicleta consome 0.2 ponto percentual por km. | PROJETO (via PRD-01) |
| RN04 | Bateria final = bateria inicial − (distância × taxa); se der negativo, vira 0.0. | PROJETO (via PRD-01) |

### 7.1 Premissas da Solução Funcional
São premissas criadas pelo autor, **não** exigidas pelos documentos oficiais. Elas aparecem declaradas como premissas no relatório.

| Código | Premissa | Antes era |
|---|---|---|
| P00 | O cenário do relatório é a telemetria da frota EcoFleet. | — (ex-pendência §15) |
| P01 | Uma viagem é **crítica** quando a bateria final fica **abaixo de 20%**. | RN05 |
| P02 | O lote simulado é sempre igual a cada execução: 30% vans e 70% bicicletas, distância de 5 a 120 km e bateria inicial de 40% a 100%. | RN07 |
| P03 | O processamento sequencial e o paralelo devem gerar totais idênticos. | RN08 |
| P04 | O processamento não altera os dados de entrada; cada etapa gera dados novos (imutabilidade). | RN09 |
| P05 | O cálculo de uma viagem depende só dos dados dela, sem efeitos sobre dados compartilhados (funções puras). | RN10 |

**Observação sobre a P00:** o documento REQUISITOS não fornece cenário concreto e só descreve "um sistema que precisa realizar cálculos complexos e processar dados de forma paralela". O autor adota a frota EcoFleet do PROJETO como cenário, pela continuidade entre os dois trabalhos. Isso é declarado no relatório como premissa. Se for removido ou trocado por outro cenário, o atendimento aos 5 tópicos não fica comprometido, por isso este ponto não é tratado como pendência.

## 8. Informações que o Sistema Precisa Controlar

| Informação | Para que serve | Observações |
|---|---|---|
| Viagem (veículo, categoria, distância, bateria inicial) | Entrada do processamento | Imutável (P04) |
| Resultado da viagem (consumo, bateria final, indicador crítico) | Saída da etapa de transformação | Imutável (P04) |
| Resumo por categoria (viagens, km, consumo, alertas) | Saída da etapa de agregação | — |
| Tempo sequencial e tempo paralelo | Discussão de desempenho | Complementar; varia com a máquina |
| Indicador de resultados idênticos | Comprovar a P03 | Complementar |

Os dados existem apenas durante a execução.

## 9. Fluxos Principais de Uso

### Fluxo 1 — Pipeline funcional (obrigatório)
1. O sistema recebe uma coleção de viagens.
2. **Transformar:** calcula o consumo e a bateria final de cada viagem.
3. **Selecionar:** mantém só as viagens que atendem ao critério (P01).
4. **Agregar:** combina os resultados em totais por categoria.
5. Exibe o resultado final.

### Fluxo 2 — Sequencial × paralelo (complementar)
1. O sistema gera 100.000 viagens (P02).
2. Executa o pipeline em sequência e mede o tempo.
3. Executa o mesmo pipeline em paralelo e mede o tempo.
4. Compara os totais (P03) e exibe os totais, os dois tempos e se os resultados são iguais.

### Fluxo 3 — Teste remoto (obrigatório; a forma é complementar)
1. O autor envia o código ao repositório no GitHub.
2. O código é executado em ambiente remoto gratuito, por exemplo o GitHub Actions.
3. A saída e a evidência da execução são levadas ao relatório.

### Fluxo 4 — Produção do relatório (obrigatório)
1. O autor redige os Tópicos 1 e 2.
2. Insere o código, a explicação e a saída no Tópico 3.
3. Redige os Tópicos 4 e 5 com base nos resultados.
4. Revisa o texto com suas próprias palavras e assina.

## 10. Histórias de Usuário
1. Como **avaliador**, eu quero cada característica funcional explicada com o porquê de sua importância, para verificar a compreensão e não a memorização.
2. Como **avaliador**, eu quero a comparação funcional × imperativo ligada ao problema, para ver vantagens e desvantagens concretas.
3. Como **avaliador**, eu quero o código com explicação e conceitos indicados, para entender a solução sem precisar executá-la.
4. Como **equipe técnica da EcoFleet**, eu quero saber em quais situações o paradigma funcional traz benefício real, para decidir sua adoção.
5. Como **equipe técnica da EcoFleet**, eu quero ver que o modo paralelo dá o mesmo resultado do sequencial, para confiar no processamento. (complementar)
6. Como **autor**, eu quero o código testado em ambiente remoto e guardado no GitHub, para comprovar que ele funciona.

## 11. Critérios de Aceitação

### 11.A Critérios obrigatórios (checklist do REQUISITOS)

**Tópico 1**
- [ ] Explica o paradigma funcional.
- [ ] Explica a imutabilidade.
- [ ] Explica as funções puras.
- [ ] Explica a avaliação preguiçosa.
- [ ] Explica as funções de ordem superior.

**Tópico 2**
- [ ] Compara o funcional e o imperativo.
- [ ] Apresenta vantagens e desvantagens.
- [ ] Relaciona a comparação ao cenário.

**Tópico 3**
- [ ] Apresenta um exemplo prático.
- [ ] Usa e informa uma linguagem adequada.
- [ ] Explica o funcionamento do código.
- [ ] Aponta onde aparecem os conceitos funcionais.
- [ ] O código tem a autoria de Antonio Santos.
- [ ] O código foi testado em ambiente remoto gratuito.
- [ ] O código está armazenado em repositório GitHub do autor.

**Tópico 4**
- [ ] Analisa a aplicabilidade do paradigma funcional.
- [ ] Relaciona os benefícios ao projeto apresentado.
- [ ] Explica explicitamente como a imutabilidade e as funções puras favorecem o processamento paralelo.

**Tópico 5**
- [ ] Apresenta uma conclusão crítica.
- [ ] Considera desempenho e manutenção.
- [ ] Considera a curva de aprendizado da equipe.
- [ ] Indica quando o paradigma funcional não é a melhor escolha.

**Geral**
- [ ] A extensão é suficiente para atender todos os tópicos.
- [ ] TOKUMOTO (2022) está referenciado, sem cópia literal.

### 11.B Critérios complementares 
- [ ] O lote simulado é igual em todas as execuções (P02).
- [ ] Toda viagem com bateria final abaixo de 20% aparece entre as críticas, e nenhuma outra (P01).
- [ ] Os totais sequencial e paralelo são idênticos e o sistema informa isso (P03).
- [ ] Os dados de entrada não são alterados (P04).
- [ ] Os tempos aparecem em ms, com a ressalva de que dependem da máquina.
- [ ] A avaliação preguiçosa é observável na saída.
- [ ] O relatório inclui a saída da execução e a evidência do teste remoto.

## 12. Consultas, Relatórios e Indicadores

| Indicador | Tipo |
|---|---|
| Resultado final do pipeline (totais por categoria) | Obrigatório |
| Lista de viagens críticas | Complementar |
| Tempo sequencial × paralelo (ms) | Complementar |
| Resultados idênticos (sim/não) | Complementar |

## 13. Permissões e Segurança Funcional

| Perfil | Pode fazer | Não pode fazer | Observações |
|---|---|---|---|
| Avaliador | Ler o relatório e consultar o repositório | Alterar a entrega | — |
| Equipe técnica (fictícia) | Ler a análise | — | Perfil do cenário |
| Autor | Escrever, codificar, publicar e testar | Publicar dados pessoais além de nome e RA | Revisão final de responsabilidade do autor |

## 14. Limitações da Primeira Versão
- Os dados são simulados.
- Os tempos dependem do ambiente remoto e não representam desempenho de produção. Em lotes pequenos, o modo paralelo pode ser mais lento.
- A análise se apoia em um único exemplo e em uma única linguagem.

## 15. Pontos Pendentes Antes do FSD
1. ~~Decisão de arquitetura~~ **Resolvido. Arquitetura recomendada, alinhada aos dois PRDs:**
   - código em Java;
   - recursos funcionais com Streams;
   - relatório em documento acadêmico;
   - teste remoto pelo GitHub Actions.

   Os detalhes (versão do Java, estrutura do repositório, workflow) serão registrados em `vibe-decisoes-tecnicas`.
2. **Conta e nome do repositório GitHub** do autor, e quem vai criá-lo. Será registrado em `vibe-decisoes-tecnicas`.
3. **Política da UniCesumar sobre IA** nesta atividade individual. O autor precisa verificar e fazer a revisão final com suas próprias palavras.
4. **Manter ou retirar** cada item do escopo complementar (5.B).

O uso do cenário EcoFleet deixou de ser pendência e virou observação da P00 (§7.1).

## 16. Resumo Final do PRD
O relatório responde aos 5 tópicos do REQUISITOS:
- explica as quatro características do paradigma funcional;
- compara o funcional com o imperativo dentro do problema;
- apresenta um exemplo de transformar, selecionar e agregar, com código explicado, de autoria de Antonio Santos, testado em ambiente remoto gratuito e guardado no GitHub;
- analisa a aplicabilidade ao cálculo paralelo;
- conclui de forma crítica.

O cenário é a frota EcoFleet (P00). Os demais itens criados pelo autor (bateria crítica abaixo de 20%, lote simulado, comparação sequencial × paralelo e medição de tempo) ficam separados como **premissas e escopo complementar**, que podem ser retirados sem descumprir o enunciado.
