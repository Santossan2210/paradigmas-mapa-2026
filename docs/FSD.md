# FSD — ESPECIFICAÇÃO FUNCIONAL DO SISTEMA

**Sistema:** Telemetria de Frota Sustentável — EcoFleet Solutions S.A.
**Fornecedor:** SAN SOLUÇÕES
**Elaborado por:** Antonio Santos
**Data:** 08/11/2026
**Versão:** 1.1 (validado; incorpora os achados I1–I4 e M1–M4 da validação)

> Este documento é a **única fonte da verdade** para a implementação. Tudo o que é preciso para construir o sistema está transcrito aqui.

---

## 1. Visão Geral do Sistema

A EcoFleet Solutions S.A. (empresa fictícia de um trabalho acadêmico) opera uma frota urbana de **vans elétricas de carga** e **bicicletas elétricas de entrega**. O protótipo anterior de telemetria aceitava níveis de bateria inválidos, abaixo de 0 ou acima de 100, o que comprometia o acompanhamento da frota.

A SAN SOLUÇÕES entrega três produtos:

1. **Módulo OO (pacote `telemetria`):** reorganiza a telemetria no paradigma orientado a objetos, com classe abstrata, herança, encapsulamento, construtores e polimorfismo. Corresponde às Tarefas 1, 2 e 3 de um enunciado acadêmico (o "PROJETO").
2. **Módulo funcional (pacote `processamento`):** processa um lote simulado de viagens com um pipeline funcional (transformar, selecionar e agregar), em modo sequencial e paralelo, provando que os dois modos dão o mesmo resultado. Serve de exemplo prático para um relatório sobre o paradigma funcional (Tópicos 1 a 5 de um segundo documento, o "REQUISITOS").
3. **Relatório acadêmico `.docx`:** um único arquivo em duas partes, gerado por script a partir de textos editáveis, do código-fonte e das saídas reais das execuções.

O código é versionado num repositório público no GitHub e executado automaticamente no GitHub Actions, que funciona como ambiente remoto gratuito de teste e como evidência de execução.

**Natureza das exigências:**
- **Obrigatório:** vem dos documentos acadêmicos.
- **Premissa:** decisão declarada da SAN SOLUÇÕES (códigos P00 a P05).
- **Complementar:** opcional, criado para dar evidência concreta à análise.

Cada item deste FSD indica a sua natureza quando ela não for óbvia.

## 2. Objetivo do Sistema

**Principal:** garantir que o nível de bateria de cada veículo seja sempre válido e calculado corretamente após as viagens, e demonstrar que o processamento funcional de grandes volumes de viagens produz sempre o mesmo resultado, em sequência ou em paralelo.

**Específicos:**
1. Representar os veículos em uma classe abstrata `Veiculo` com duas subclasses concretas.
2. Impedir que níveis de bateria fora de 0.0 a 100.0 sejam armazenados normalmente.
3. Calcular o consumo de forma polimórfica: van 0.5 e bicicleta 0.2 ponto percentual por km.
4. Processar viagens em um pipeline de transformar (`map`), selecionar (`filter`) e agregar (`reduce`).
5. Identificar as viagens que terminam com bateria crítica (abaixo de 20%).
6. Comparar os modos sequencial e paralelo em resultado e em tempo.
7. Executar tudo automaticamente em ambiente remoto gratuito, com log público.
8. Gerar o relatório `.docx` completo, com saídas reais.

## 3. Escopo da Primeira Versão

### 3.1 Obrigatório: Módulo OO (Tarefas 1 a 3)
- Classe abstrata `Veiculo` e subclasses `VanEletrica` e `BicicletaEletrica`, com os atributos, visibilidades e métodos exatos da §12.1.
- Validação da bateria (RN01) em `setAutonomiaBateria`.
- `registrarViagem(double distancia)` polimórfico (RN02 a RN04), com mensagem no console.
- `Main`: cria uma van e uma bicicleta com bateria 100.0, simula 50.0 km em cada uma e exibe os níveis finais (75.0 e 90.0).
- Execução e teste em ambiente compatível com Java. Pode ser local.

### 3.2 Obrigatório: Exemplo funcional (Tópico 3)
- Pipeline com dados de entrada → transformar → selecionar → agregar → resultado final, em Java com Streams, lambdas e records.
- Código com autoria "Antonio Santos".
- Teste em ambiente remoto gratuito (GitHub Actions), com evidência.
- Código armazenado no repositório GitHub do autor.

### 3.3 Complementar: Módulo funcional ampliado
- Lote pequeno (10 viagens) exibido viagem a viagem.
- Lote grande (100.000 viagens) exibido só em resumo.
- Demonstração visível da avaliação preguiçosa.
- Comparação sequencial × paralelo, com confirmação de igualdade e tempos em ms.

### 3.4 Apoio à verificação (aprovado pelo autor na validação do FSD)
- Programa `verificacao.VerificacaoRegras`, que confere automaticamente a RN01 e os valores 75.0 e 90.0 usando só a API pública das classes do pacote `telemetria`. Existe para comprovar os critérios "testado" e "sem exceções" sem framework de testes, que está fora de escopo. Não entra no relatório; aparece só no repositório e no CI.
- Workflow do GitHub Actions que compila, executa e confere as saídas esperadas.

### 3.5 Relatório
- Script de geração do `.docx`, arquivos de conteúdo editáveis e captura das saídas das execuções.

## 4. Fora de Escopo
- Interface gráfica, web ou API; persistência (arquivos de dados, banco); dados reais de sensores ou GPS.
- Recarga de bateria, rotas e previsão de autonomia.
- Frameworks de teste (JUnit etc.), Maven, Gradle e qualquer dependência Java externa.
- Classes, atributos ou métodos extras dentro do pacote `telemetria`, inclusive constantes `static final`.
- Exemplos em outras linguagens de programação no relatório.
- Autenticação, perfis de acesso, auditoria, exclusão lógica, uploads e integrações externas.
- README detalhado. É permitido só um README mínimo de identificação (§8).

## 5. Stack Técnica Definida

| Item | Definição |
|---|---|
| Linguagem | Java 21 LTS |
| JDK | Eclipse Temurin 21 (local e remoto) |
| Build | `javac` e `java` puros, sem ferramenta de build e sem dependências |
| Recursos OO | `abstract`, `extends`, `protected`/`private`, construtores, `@Override` |
| Recursos funcionais | `java.util.stream` (`map`, `filter`, `reduce`, `findFirst`, `limit`, `peek`), lambdas, referências a método, `record`, `enum`, `Function` (função de ordem superior), `parallelStream()` |
| Controle de versão | Git + GitHub (repositório público `paradigmas-mapa-2026`) |
| Teste remoto | GitHub Actions (`ubuntu-latest`, `actions/checkout@v4`, `actions/setup-java@v4` com Temurin 21) |
| Geração do relatório | Python 3 + biblioteca `python-docx` |
| Codificação dos fontes | UTF-8 |

Nenhuma outra tecnologia pode ser introduzida.

## 6. Ambientes (Desenvolvimento, Testes, Produção)

### 6.1 Desenvolvimento (local)
- Windows 11, com JDK Temurin 21 no `PATH` e `JAVA_HOME` configurado.
- Comandos executados na raiz `[Diretório do Projeto - Repositório]`, em PowerShell ou Git Bash:

```
javac -encoding UTF-8 -d out src/telemetria/*.java src/processamento/*.java src/verificacao/*.java
java -Dstdout.encoding=UTF-8 -cp out telemetria.Main
java -Dstdout.encoding=UTF-8 -cp out processamento.ProcessamentoMain
java -Dstdout.encoding=UTF-8 -cp out verificacao.VerificacaoRegras
```

- **Curinga `*.java` no PowerShell:** o launcher do Java no Windows expande o curinga. Confirme isso na Fase 1. Se não funcionar, rode os mesmos comandos no Git Bash, que é o shell de referência local.
- Enquanto um pacote ainda não tiver arquivos (por exemplo, `processamento` na Fase 1), retire o padrão correspondente do comando `javac`. Um curinga sem arquivos faz o `javac` falhar com "file not found".
- No Windows, se os acentos aparecerem trocados no console, execute `chcp 65001` antes. A opção `-Dstdout.encoding=UTF-8` garante a saída em UTF-8.
- A pasta `out/` contém só arquivos compilados e não é versionada.

### 6.2 Testes (remoto)
- GitHub Actions, workflow `.github/workflows/executar.yml` (detalhado na §20.1).
- É disparado a cada `push` em qualquer branch e manualmente (`workflow_dispatch`).
- Os mesmos comandos da §6.1 rodam em `ubuntu-latest`.

### 6.3 Produção
- Não se aplica. O sistema é acadêmico e roda só em console. A "entrega" é o `.docx` enviado ao ambiente virtual de aprendizagem.

## 7. Arquitetura Geral do Sistema

Três programas Java independentes, que compartilham apenas o pacote `telemetria` (usado pela verificação), e um script Python separado:

```
┌──────────────────────────┐   usa API pública   ┌──────────────────────────┐
│ telemetria (OO)          │◄────────────────────│ verificacao              │
│ Veiculo (abstract)       │                     │ VerificacaoRegras        │
│  ├─ VanEletrica          │                     └──────────────────────────┘
│  └─ BicicletaEletrica    │
│ Main                     │
└──────────────────────────┘

┌──────────────────────────────────────────────────────────────┐
│ processamento (funcional) — NÃO depende de telemetria        │
│ Categoria (enum) · Viagem / ResultadoViagem / ResumoCategoria │
│ (records) · CalculoViagem (funções puras) · GeradorLote      │
│ PipelineTelemetria · ProcessamentoMain                       │
└──────────────────────────────────────────────────────────────┘

relatorio/gerar_relatorio.py ──lê──► src/**/*.java + relatorio/conteudo/* + relatorio/saidas/*
                             ──gera──► relatorio/gerado/*.docx  (não versionado)
```

**Princípios:**
- **Independência do módulo funcional:** `processamento` **não** importa `telemetria`. As regras de consumo (RN02 a RN04) são reescritas como funções puras sobre dados imutáveis. Isso é intencional: as classes OO alteram estado via `setAutonomiaBateria`, o que contraria a imutabilidade. A duplicação das taxas 0.5 e 0.2 é consciente e deve ser comentada no código e discutida nos Tópicos 2 e 5 do relatório.
- **Pacote `telemetria` intocável:** contém exatamente 4 classes e só os membros listados na §12.1.
- **Sem estado compartilhado mutável no pipeline:** nenhuma lambda do pipeline escreve em variável, coleção ou objeto externo. A única exceção é o `peek` de instrumentação didática da §15.2.3, que só imprime.

## 8. Estrutura de Pastas do Projeto

```
[Diretório do Projeto - Repositório]/
├── .github/
│   └── workflows/
│       └── executar.yml              # CI: compila, executa e confere
├── docs/                             # documentação do projeto (PRDs, decisões, FSD validado)
├── src/
│   ├── telemetria/                   # Módulo OO — exatamente 4 classes
│   │   ├── Veiculo.java
│   │   ├── VanEletrica.java
│   │   ├── BicicletaEletrica.java
│   │   └── Main.java
│   ├── processamento/                # Módulo funcional
│   │   ├── Categoria.java
│   │   ├── Viagem.java
│   │   ├── ResultadoViagem.java
│   │   ├── ResumoCategoria.java
│   │   ├── CalculoViagem.java
│   │   ├── GeradorLote.java
│   │   ├── PipelineTelemetria.java
│   │   └── ProcessamentoMain.java
│   └── verificacao/
│       └── VerificacaoRegras.java
├── relatorio/
│   ├── gerar_relatorio.py            # gera o .docx
│   ├── requirements.txt              # python-docx==1.1.2 (versão fixada)
│   ├── identificacao.exemplo.txt     # modelo versionado (sem dados reais)
│   ├── identificacao.local.txt       # NÃO versionado: nome completo, RA, links
│   ├── conteudo/                     # textos editáveis pelo autor (ver §15.4)
│   ├── saidas/                       # saídas capturadas (.txt) — versionadas
│   ├── evidencias/                   # imagem da execução aprovada no Actions — versionada
│   └── gerado/                       # NÃO versionado: .docx final
├── out/                              # NÃO versionado: .class compilados
├── .gitignore
├── .gitattributes                    # normaliza fins de linha (LF) para .java/.yml/.py/.txt
└── README.md                         # mínimo: nome do projeto, autor, como compilar/executar
```

**`.gitignore` mínimo:** `out/`, `relatorio/gerado/`, `relatorio/identificacao.local.txt`, `__pycache__/`, `*.class`.

## 9. Convenção de Configuração

- **Não há segredos.** O sistema não usa senhas, tokens nem conexões. O workflow do Actions não usa `secrets`.
- **Constantes de domínio:**
  - no pacote `telemetria`, as taxas 0.5 e 0.2 são **literais** dentro de `registrarViagem`, com comentário, porque o enunciado proíbe atributos extras;
  - no pacote `processamento`, as constantes ficam exatamente onde a tabela da §24 indica, que é a fonte única: taxas em `Categoria`, limite crítico em `CalculoViagem`, semente em `GeradorLote`, tamanhos de lote e rodadas de medição em `ProcessamentoMain`.
- **Dados pessoais:** nome completo, RA e links ficam em `relatorio/identificacao.local.txt`, arquivo local **não versionado**. O repositório guarda só `relatorio/identificacao.exemplo.txt`, com campos vazios e o mesmo formato:

```
nome_completo=
ra=
autor_assinatura=Antonio Santos
empresa=SAN SOLUÇÕES
repositorio_url=
actions_url=
```

- **Autoria Git:** `user.name = Antonio Santos` é configurado só no repositório local (`git config user.name`), nunca globalmente. O e-mail é escolhido pelo autor (§29).
- **Caminhos:** todo caminho no código, no script e no workflow é relativo à raiz do repositório.

## 10. Perfis de Usuário e Permissões Detalhadas

O sistema não tem login nem controle de acesso. Os perfis abaixo são do contexto de uso.

| Perfil | Pode fazer | Não pode fazer | Observações |
|---|---|---|---|
| Avaliador (professor) | Ler o `.docx`; abrir o repositório público e os logs do Actions | Alterar código ou relatório | Não precisa executar nada |
| Equipe de operação EcoFleet (fictícia) | No cenário: consultar bateria, registrar viagens, ver totais e alertas | Gravar bateria sem passar pela RN01 | Representada pelas saídas de console |
| Autor (Antonio Santos) | Alterar código e textos, executar, gerar o `.docx`, enviar ao GitHub | Alterar nomes definidos no enunciado; versionar dados pessoais | Faz a revisão final do texto |

**Permissão em nível de código (obrigatória):** `autonomiaBateria` é `private`. As subclasses e as classes externas só o leem por `getAutonomiaBateria()` e só o alteram por `setAutonomiaBateria(double)`. `idVeiculo`, `modelo` e `capacidadeCarga` são `protected`, acessíveis diretamente pelas subclasses.

## 11. Autenticação e Sessão

Não se aplica. Não há usuários, login, sessão nem dados persistidos.

## 12. Modelo de Dados Completo

Todos os dados existem apenas em memória, durante a execução.

### 12.1 Pacote `telemetria` (obrigatório, nomes exatos)

**`public abstract class Veiculo`**

| Membro | Tipo | Visibilidade | Regra |
|---|---|---|---|
| `idVeiculo` | `int` | `protected` | — |
| `modelo` | `String` | `protected` | — |
| `capacidadeCarga` | `double` | `protected` | Unidade: kg (indicada em comentário) |
| `autonomiaBateria` | `double` | `private` | Faixa válida 0.0 a 100.0 (RN01) |
| `Veiculo()` | construtor | `public` | Inicia `idVeiculo = 0`, `modelo = ""`, `capacidadeCarga = 0.0`, `autonomiaBateria = 0.0` |
| `getAutonomiaBateria()` | `double` | `public` | Retorna `autonomiaBateria` |
| `setAutonomiaBateria(double bateria)` | `void` | `public` | Aplica a RN01 com `if/else` |
| `registrarViagem(double distancia)` | `void` | `public abstract` | Assinatura exata: `public abstract void registrarViagem(double distancia);` |

**`public class VanEletrica extends Veiculo`**

| Membro | Tipo | Visibilidade | Regra |
|---|---|---|---|
| `placaIdentificacao` | `String` | `private` | — |
| `VanEletrica(int idVeiculo, String modelo, double capacidadeCarga, double autonomiaBateria, String placaIdentificacao)` | construtor | `public` | Atribui `this.idVeiculo`, `this.modelo` e `this.capacidadeCarga` (herdados `protected`); chama `setAutonomiaBateria(autonomiaBateria)`; atribui `this.placaIdentificacao` |
| `registrarViagem(double distancia)` | `void` | `public`, `@Override` | RN02 + RN04 |

**`public class BicicletaEletrica extends Veiculo`**

| Membro | Tipo | Visibilidade | Regra |
|---|---|---|---|
| `limiteVelocidadeAssistida` | `double` | `private` | Unidade: km/h (comentário) |
| `BicicletaEletrica(int idVeiculo, String modelo, double capacidadeCarga, double autonomiaBateria, double limiteVelocidadeAssistida)` | construtor | `public` | Mesmo padrão da van |
| `registrarViagem(double distancia)` | `void` | `public`, `@Override` | RN03 + RN04 |

**`public class Main`:** só `public static void main(String[] args)`.

Nenhum outro membro existe nessas classes: nada de getters extras, `toString`, constantes ou métodos auxiliares. Os construtores das subclasses chamam implicitamente `super()`, que inicia os padrões antes da atribuição.

### 12.2 Pacote `processamento`

**`public enum Categoria`**

| Constante | `taxaPorKm` (double) | `rotulo` (String) |
|---|---|---|
| `VAN` | 0.5 | "Van elétrica" |
| `BICICLETA` | 0.2 | "Bicicleta elétrica" |

Campos `private final`, com métodos de acesso `taxaPorKm()` e `rotulo()`.

**`public record Viagem(int id, Categoria categoria, int distanciaKm, int bateriaInicial)`**
- Imutável. `distanciaKm` está entre 5 e 120; `bateriaInicial` entre 40 e 100 (inteiros, por P02).
- O construtor compacto valida: `categoria` não nula, `distanciaKm >= 0`, `bateriaInicial` entre 0 e 100. Se algo for inválido, lança `IllegalArgumentException`, o que protege contra erro de programação no gerador.

**`public record ResultadoViagem(Viagem viagem, double consumo, double bateriaFinal, boolean critica)`**
- Imutável. Produzido só por `CalculoViagem.calcular`.

**`public record ResumoCategoria(Categoria categoria, long viagens, long kmTotal, long consumoCentesimos, long alertas)`**
- Imutável. Totais acumulados em **inteiros** (`long`), para que a soma seja exata e independente da ordem (§16, RN-F07).
- `static ResumoCategoria vazio(Categoria c)`: devolve um resumo com tudo zerado (identidade do `reduce`).
- `static ResumoCategoria de(ResultadoViagem r)`: resumo de uma viagem só, com `viagens = 1`, `kmTotal = distanciaKm`, `consumoCentesimos = Math.round(consumo * 100)` e `alertas = critica ? 1 : 0`.
- `ResumoCategoria combinar(ResumoCategoria outro)`: devolve um **novo** resumo com a soma campo a campo. É associativo e não altera nenhum dos dois. Exige a mesma categoria; se forem diferentes, lança `IllegalArgumentException`.
- `double consumoTotal()`: devolve `consumoCentesimos / 100.0`, só para exibição.

**`public final class CalculoViagem`** (construtor privado, só métodos `static`, funções puras)
- `static ResultadoViagem calcular(Viagem v)`: aplica a RN-F01 a RN-F03.
- `static boolean ehCritica(double bateriaFinal)`: devolve `bateriaFinal < LIMITE_CRITICO`.
- `static final double LIMITE_CRITICO = 20.0`.

**`public final class GeradorLote`** (construtor privado)
- `static final long SEMENTE = 2026L`.
- `static List<Viagem> gerar(int quantidade)`: gera sequencialmente, com `new java.util.Random(SEMENTE)`, e devolve uma lista **imutável** (`List.copyOf` ou `Stream.toList()`). Algoritmo da §16, RN-F05.

**`public final class PipelineTelemetria`** (construtor privado)
- `static List<ResultadoViagem> transformar(List<Viagem> viagens, Function<List<Viagem>, Stream<Viagem>> fonte)`: `fonte.apply(viagens).map(CalculoViagem::calcular).toList()`.
- `static List<ResultadoViagem> selecionarCriticas(List<ResultadoViagem> resultados)`: `resultados.stream().filter(ResultadoViagem::critica).toList()`.
- `static Map<Categoria, ResumoCategoria> agregar(List<Viagem> viagens, Function<List<Viagem>, Stream<Viagem>> fonte)`: para cada `Categoria` em ordem de declaração, `fonte.apply(viagens).filter(v -> v.categoria() == cat).map(CalculoViagem::calcular).map(ResumoCategoria::de).reduce(ResumoCategoria.vazio(cat), ResumoCategoria::combinar)`. Devolve um mapa imutável com ordem estável (`EnumMap` envolto em `Collections.unmodifiableMap`).
- `static final Function<List<Viagem>, Stream<Viagem>> SEQUENCIAL = List::stream;`
- `static final Function<List<Viagem>, Stream<Viagem>> PARALELO = List::parallelStream;`
- O parâmetro `fonte` é a **função de ordem superior** que o relatório deve apontar: o mesmo pipeline recebe como argumento a função que decide se o fluxo é sequencial ou paralelo.

**`public final class ProcessamentoMain`:** contém `main`, as constantes `private static final` da §24 (tamanhos de lote e rodadas de medição) e métodos `private static` de impressão e medição.

**Por que `agregar` recalcula a partir das viagens.** `agregar` recebe as **viagens**, e não a saída de `transformar` nem de `selecionarCriticas`, por dois motivos:
- a agregação vale para **todas** as viagens, não só para as críticas;
- cada execução sequencial ou paralela precisa ser um pipeline completo e autocontido (map → filter → map → reduce) para a comparação e a medição de tempo serem justas.

Recalcular é seguro porque `calcular` é uma função pura: sempre dá o mesmo resultado. Esse motivo deve aparecer no Tópico 3 do relatório, para o leitor não achar que as etapas estão desconectadas.

### 12.3 Pacote `verificacao`
**`public final class VerificacaoRegras`:** só `main` e métodos `private static`. Usa apenas construtores e métodos públicos de `telemetria`.

### 12.4 Relacionamentos
- `VanEletrica` e `BicicletaEletrica` **são** `Veiculo` (herança).
- `ResultadoViagem` **contém** uma `Viagem` (composição imutável).
- `Viagem` e `ResumoCategoria` **referenciam** uma `Categoria`.
- Não há chaves, identidade persistente nem relação entre `telemetria` e `processamento`.

## 13. Estratégia de Migrations

Não se aplica: não há banco de dados nem persistência. Se a estrutura das classes mudar, a mudança é feita no próprio código, versionada em commits e revalidada pelo workflow (§25).

## 14. Módulos do Sistema

| Módulo | Natureza | Responsabilidade | Ponto de entrada |
|---|---|---|---|
| `telemetria` | Obrigatório | Modelo OO, validação da bateria, consumo polimórfico, demonstração de 50 km | `telemetria.Main` |
| `processamento` | Obrigatório (núcleo) + Complementar (lote grande, comparação, tempos) | Pipeline funcional sobre o lote simulado | `processamento.ProcessamentoMain` |
| `verificacao` | Apoio | Confere a RN01 e os valores de 50 km sem framework | `verificacao.VerificacaoRegras` |
| CI (`executar.yml`) | Obrigatório (teste remoto) | Compila, executa os 3 programas e falha se as saídas esperadas não aparecerem | Push ou disparo manual |
| `relatorio` | Obrigatório (entrega) | Gera o `.docx` com as Partes I e II | `python relatorio/gerar_relatorio.py` |

## 15. Telas e Fluxos — Detalhamento por Módulo

Não há telas gráficas. Cada "tela" é uma saída de console ou um documento. Os textos abaixo são o **formato exato** esperado. Números usam `Locale.ROOT` (ponto decimal) e casas fixas.

### 15.1 Módulo `telemetria` — saída de `telemetria.Main`

**Objetivo:** demonstrar o Fluxo de 50 km do enunciado.
**Origem:** linha de comando. **Destino:** fim do programa.
**Valores de criação.** O enunciado pede apenas "valores coerentes"; os valores abaixo foram escolhidos pela SAN SOLUÇÕES:
- `new VanEletrica(1, "EcoCargo E-Van 1500", 1500.0, 100.0, "ECO1A26")`
- `new BicicletaEletrica(2, "EcoBike Urban 25", 30.0, 100.0, 25.0)`

**Mensagem de `registrarViagem` (formato obrigatório):**

```
[Van elétrica] Distância percorrida: 50.0 km | Bateria restante: 75.0%
[Bicicleta elétrica] Distância percorrida: 50.0 km | Bateria restante: 90.0%
```

Gerada com `String.format(Locale.ROOT, "[Van elétrica] Distância percorrida: %.1f km | Bateria restante: %.1f%%%n", distancia, getAutonomiaBateria())`, e o equivalente para a bicicleta.

**Saída completa esperada:**

```
=== EcoFleet Solutions S.A. - Telemetria da Frota ===
[Van elétrica] Distância percorrida: 50.0 km | Bateria restante: 75.0%
[Bicicleta elétrica] Distância percorrida: 50.0 km | Bateria restante: 90.0%
--- Nível final de bateria ---
Van elétrica: 75.0%
Bicicleta elétrica: 90.0%
```

As duas últimas linhas usam `getAutonomiaBateria()` formatado com `%.1f` e `Locale.ROOT`.

**Estados:**
- Sucesso: a saída acima, com código de saída 0.
- Erro de compilação: o `javac` aponta o erro e o programa não roda.
- Exceção: não deve ocorrer. Se ocorrer, é defeito.

**Lógica de `registrarViagem` (van):**
1. `double novaBateria = getAutonomiaBateria() - (distancia * 0.5);`, com o comentário "cada km consome 0.5 ponto percentual".
2. `setAutonomiaBateria(novaBateria);` (a RN01 converte valores negativos em 0.0).
3. Imprime a mensagem.

A bicicleta é idêntica, com `0.2`.

**Lógica de `setAutonomiaBateria(double bateria)`:**

```
se (bateria < 0.0 || bateria > 100.0) → autonomiaBateria = 0.0
senão                                  → autonomiaBateria = bateria
```

### 15.2 Módulo `processamento` — saída de `processamento.ProcessamentoMain`

Seções impressas em ordem. Cabeçalho de cada seção: `=== <título> ===`.

#### 15.2.1 Seção "Lote pequeno — 10 viagens" (obrigatório)
- **Dados de entrada:** `GeradorLote.gerar(10)`.
- **Transformar:** `PipelineTelemetria.transformar(lote, SEQUENCIAL)`.
- Imprime uma linha por viagem, neste formato:

```
#01 | Van elétrica       |  87 km | bateria 92% -> 48.5% | consumo 43.5 | CRÍTICA: não
```

- Campos:
  - id com 2 dígitos;
  - rótulo alinhado à esquerda em 18 colunas;
  - distância com 3 dígitos;
  - bateria inicial inteira;
  - bateria final com `%.1f`;
  - consumo com `%.1f`;
  - "sim" ou "não".
- **Estado vazio:** não ocorre, porque o lote é fixo. Se a lista vier vazia, imprime `Nenhuma viagem no lote.`.

#### 15.2.2 Seção "Seleção — viagens críticas (< 20%)" (obrigatório)
- `PipelineTelemetria.selecionarCriticas(resultados)`.
- Imprime as mesmas linhas, só das críticas, e depois `Total de viagens críticas: N`.
- **Estado vazio:** se N = 0, imprime `Nenhuma viagem crítica neste lote.`.

#### 15.2.3 Seção "Avaliação preguiçosa" (complementar; o conceito é obrigatório no texto)
- Monta um stream sequencial sobre o lote pequeno com `.peek(v -> System.out.printf(Locale.ROOT, "  calculando viagem #%02d%n", v.id()))` antes de `.map(CalculoViagem::calcular)`, e `.filter(ResultadoViagem::critica)`. **Não** chama operação terminal ainda.
- Imprime `Pipeline montado. Nenhuma viagem foi calculada até aqui.`. Como o stream é preguiçoso, nenhuma linha "calculando" deve aparecer antes desta.
- Chama `.findFirst()` e imprime `Primeira viagem crítica encontrada: #NN` (ou `Nenhuma viagem crítica encontrada.`), e depois `Viagens calculadas: K de 10`.
  - K é contado **sem estado mutável compartilhado**: é o id da viagem encontrada, porque o lote é sequencial e os ids vão de 1 a 10 em ordem. Se nenhuma for encontrada, K = 10.
- O `peek` é **só instrumentação didática**: imprime, não altera dados, e o relatório deve dizer isso.
- Saída ilustrativa, com a viagem #03 como primeira crítica:

```
Pipeline montado. Nenhuma viagem foi calculada até aqui.
  calculando viagem #01
  calculando viagem #02
  calculando viagem #03
Primeira viagem crítica encontrada: #03
Viagens calculadas: 3 de 10
```

#### 15.2.4 Seção "Agregação por categoria — lote pequeno" (obrigatório)
- `PipelineTelemetria.agregar(lote, SEQUENCIAL)`.
- Uma linha por categoria:

```
Van elétrica       | viagens:   3 | km:    214 | consumo: 107.0 | alertas: 2
Bicicleta elétrica | viagens:   7 | km:    401 | consumo:  80.2 | alertas: 0
```

#### 15.2.5 Seção "Lote grande — 100000 viagens: sequencial × paralelo" (complementar)
- **Dados:** `GeradorLote.gerar(100_000)`.
- **Medição:**
  1. Uma rodada de aquecimento de cada modo, descartada.
  2. 5 rodadas medidas de cada modo, com `System.nanoTime()`.
  3. Exibe a **média** em ms com `%.1f`.
- **Resultado:** o resultado de `agregar(lote, SEQUENCIAL)` e o de `agregar(lote, PARALELO)` vêm da última rodada de cada modo.
- **Comparação:** `resultadoSequencial.equals(resultadoParalelo)`. A igualdade é exata, porque os records só têm `long` e `Categoria`.
- **Saída:**

```
Van elétrica       | viagens:  30012 | km: 1878634 | consumo: 939317.0 | alertas: 14820
Bicicleta elétrica | viagens:  69988 | km: 4378022 | consumo: 875604.4 | alertas:   812
Tempo médio sequencial: 42.3 ms
Tempo médio paralelo:   18.7 ms
Resultados idênticos: SIM
Observação: tempos variam conforme a máquina e não representam desempenho de produção.
```

Os números acima são **ilustrativos**. Os reais saem da execução.
- **Estado de falha:** se não forem iguais, imprime `Resultados idênticos: NÃO` e encerra com `System.exit(1)`, para que o workflow falhe.

### 15.3 Módulo `verificacao` — saída de `verificacao.VerificacaoRegras`
- **Objetivo:** conferir as regras obrigatórias sem framework.
- **Casos**, cada um sobre uma `VanEletrica` nova criada com a bateria indicada e conferido com `getAutonomiaBateria()`:

| Caso | Entrada no construtor | Esperado |
|---|---|---|
| RN01 negativo | -5.0 | 0.0 |
| RN01 acima de 100 | 120.0 | 0.0 |
| RN01 limite inferior | 0.0 | 0.0 |
| RN01 meio | 50.0 | 50.0 |
| RN01 limite superior | 100.0 | 100.0 |
| RN02 van 50 km | 100.0, depois `registrarViagem(50.0)` | 75.0 |
| RN03 bicicleta 50 km | `BicicletaEletrica` com 100.0, depois `registrarViagem(50.0)` | 90.0 |
| RN04 esgotamento | van com 10.0, depois `registrarViagem(100.0)` | 0.0 |

- A comparação usa `Math.abs(obtido - esperado) < 1e-9`.
- Imprime `[OK] <caso>` ou `[FALHA] <caso>: esperado X, obtido Y`.
- No fim, imprime `Verificação concluída: N/8 casos OK`.
- Se houver falha, encerra com `System.exit(1)`.
- As mensagens de `registrarViagem` também aparecem nessa saída. Isso é esperado.

### 15.4 Módulo `relatorio` — o documento `.docx`

**Geração:** `python relatorio/gerar_relatorio.py`, executado na raiz.
- Lê `relatorio/identificacao.local.txt`. Se ele não existir, aborta com a mensagem `Crie relatorio/identificacao.local.txt a partir de identificacao.exemplo.txt`.
- Lê os textos de `relatorio/conteudo/`, os fontes de `src/` e as saídas de `relatorio/saidas/`.
- Grava `relatorio/gerado/Relatorio_MAPA_Paradigmas_Antonio_Santos.docx`.

**Formato dos arquivos de conteúdo** (texto UTF-8, marcação mínima interpretada pelo script):

| Marcação | Resultado no .docx |
|---|---|
| `# Título` | Título nível 1 |
| `## Título` | Título nível 2 |
| `- item` | Item de lista |
| linha em branco | separa parágrafos |
| `[[CODIGO:src/telemetria/Veiculo.java]]` | insere o arquivo inteiro como bloco de código |
| `[[SAIDA:relatorio/saidas/telemetria_main.txt]]` | insere a saída como bloco monoespaçado |
| `[[IMAGEM:relatorio/evidencias/actions_aprovado.png]]` | insere a imagem com largura de 15 cm |
| `[[TABELA]]` … `[[/TABELA]]` | linhas com `|` viram uma tabela; a primeira linha é o cabeçalho |
| `{{chave}}` | é substituído pelo valor da chave em `identificacao.local.txt` (`nome_completo`, `ra`, `autor_assinatura`, `empresa`, `repositorio_url`, `actions_url`). Vale em qualquer arquivo de conteúdo. Se a chave não existir ou estiver vazia, o script aborta com `Campo de identificação vazio: <chave>` e código 1 |

**Leitura de arquivos pelo script:** todo arquivo de texto (conteúdo, fontes, saídas e identificação) é lido com `encoding="utf-8-sig"`, que aceita arquivos com ou sem BOM. Quebras de linha CRLF e LF são tratadas da mesma forma.

**Exemplo de `00_capa.txt`:**

```
# Paradigmas de Linguagens de Programação — MAPA
## Identificação da empresa
Nome completo: {{nome_completo}}
RA: {{ra}}
Empresa: {{empresa}}
Repositório: {{repositorio_url}}
Execução remota: {{actions_url}}
```

**Arquivos de conteúdo, em ordem de montagem:**

| Arquivo | Seção do .docx | Conteúdo mínimo |
|---|---|---|
| `00_capa.txt` | Identificação | Título do trabalho; "Identificação da empresa"; Nome completo e RA (vindos de `identificacao.local.txt`); empresa SAN SOLUÇÕES; links do repositório e do Actions |
| `10_tarefa1.txt` | Parte I — Tarefa 1: Modelagem conceitual | Representação textual das 3 classes com atributos, tipos e visibilidades (tabela); relação de herança; justificativa de `Veiculo` abstrata; explicação de `protected` × `private` |
| `20_tarefa2.txt` | Parte I — Tarefa 2: Implementação em Java | Código das 4 classes, cada uma com título próprio; saída de `telemetria.Main` |
| `30_tarefa3.txt` | Parte I — Tarefa 3: Justificativa | **10 a 20 linhas** no `.docx` (Arial 12, margens de 2,5 cm, o que equivale a cerca de **130 a 260 palavras**). O script conta as palavras desse arquivo e imprime um **aviso**, sem abortar, se ficar fora da faixa. Deve cobrir a lógica do `setAutonomiaBateria`, a reutilização via superclasse, o uso da distância em `registrarViagem` e o papel do construtor |
| `40_topico1.txt` | Parte II — Tópico 1 | Paradigma funcional; imutabilidade; funções puras; avaliação preguiçosa; funções de ordem superior; por que cada uma importa |
| `50_topico2.txt` | Parte II — Tópico 2 | Tabela funcional × imperativo (organização, estado, concorrência, aprendizado) + vantagens e desvantagens no cenário EcoFleet, incluindo a tensão entre `setAutonomiaBateria` (mutável) e os records (imutáveis) |
| `60_topico3.txt` | Parte II — Tópico 3 | Linguagem adotada (Java 21 com Streams); código de `Categoria`, `Viagem`, `ResultadoViagem`, `ResumoCategoria`, `CalculoViagem` e `PipelineTelemetria`; explicação; quadro "onde está cada conceito" (§18.2); saída de `ProcessamentoMain`; imagem do Actions; premissas P00 a P05 declaradas |
| `70_topico4.txt` | Parte II — Tópico 4 | Aplicabilidade ao cenário: paralelismo, volume, pipelines, efeitos colaterais, previsibilidade e confiabilidade; explicitar **como imutabilidade e funções puras favorecem o paralelismo** |
| `80_topico5.txt` | Parte II — Tópico 5 | Conclusão crítica: desempenho (com os tempos reais), manutenção, experiência da equipe, curva de aprendizado, adequação; **quando o funcional não é a melhor escolha** |
| `90_referencias.txt` | Referências | TOKUMOTO, Ronie Cesar. *Paradigmas de Linguagem de Programação*. Maringá: Unicesumar, 2022. Reimpresso em 2023. Outras fontes, só se forem usadas |

**Formatação do .docx:**
- Fonte do texto: Arial 12; títulos em negrito.
- Código e saídas: Consolas 9, com indentação preservada (espaços), em parágrafos sem recuo e com fundo cinza-claro, se possível.
- A Parte I e a Parte II começam em página nova.
- Margens de 2,5 cm.

**Estados:**
- Sucesso: o arquivo é gerado e o script imprime o caminho relativo.
- Erro: se faltar um arquivo referenciado, o script aborta com `Arquivo não encontrado: <caminho relativo>` e código de saída 1.

**Captura das saídas:** o arquivo `relatorio/saidas/<programa>.txt` guarda a saída de cada programa.
- **Fonte oficial:** o artefato `saidas` da execução aprovada no Actions (§20.1). Ele é baixado e os arquivos são renomeados conforme a lista abaixo.
- **Alternativa local:** só pelo **Git Bash**, com `java -Dstdout.encoding=UTF-8 -cp out telemetria.Main > relatorio/saidas/telemetria_main.txt`. **Não** use o redirecionamento `>` do Windows PowerShell 5.1, que grava em UTF-16 ou com BOM.

Nomes:
- `telemetria_main.txt`
- `processamento_main.txt`
- `verificacao_regras.txt`

**Autoria do texto:** os textos de `conteudo/` são redigidos em primeira pessoa do plural institucional ("a SAN SOLUÇÕES adotou…") ou em impessoal, num tom direto. O autor revisa e reescreve com as próprias palavras antes da entrega.

## 16. Regras de Negócio Detalhadas

### 16.1 Obrigatórias (vêm do enunciado)

| Código | Regra | Onde |
|---|---|---|
| RN01 | Em `setAutonomiaBateria(double bateria)`: se `bateria < 0.0` **ou** `bateria > 100.0`, grava **0.0**; senão grava `bateria`. Exemplo: 120 vira 0.0, e não 100. Os limites 0.0 e 100.0 são válidos. | `Veiculo` |
| RN02 | Van: cada km reduz 0.5 ponto percentual. | `VanEletrica.registrarViagem` |
| RN03 | Bicicleta: cada km reduz 0.2 ponto percentual. | `BicicletaEletrica.registrarViagem` |
| RN04 | Nova bateria = `getAutonomiaBateria() − distancia × taxa`, gravada **por meio de** `setAutonomiaBateria()`. Se o resultado for negativo, vira 0.0 pela RN01. | As duas subclasses |
| RN06 | `Veiculo` é `abstract` e não pode ser instanciada. | `Veiculo` |
| RN-D1 | A demonstração usa bateria inicial 100.0 e viagem de 50.0 km, e exibe 75.0 (van) e 90.0 (bicicleta). | `Main` |

### 16.2 Premissas da Solução Funcional (SAN SOLUÇÕES, declaradas no relatório)

| Código | Premissa |
|---|---|
| P00 | O cenário do relatório funcional é a telemetria da frota EcoFleet. O documento REQUISITOS não define cenário, e a premissa pode ser removida sem invalidar os tópicos. |
| P01 | Viagem crítica: `bateriaFinal < 20.0`. Exatamente 20.0 **não** é crítica. |
| P02 | Lote determinístico: semente 2026; 30% vans e 70% bicicletas (em probabilidade); distância inteira de 5 a 120 km; bateria inicial inteira de 40 a 100%. |
| P03 | Os modos sequencial e paralelo produzem totais idênticos. |
| P04 | Os dados de entrada nunca são alterados; cada etapa cria dados novos (records). |
| P05 | O cálculo de uma viagem depende só dela, sem ler nem escrever estado compartilhado. |

### 16.3 Regras operacionais do módulo funcional

| Código | Regra |
|---|---|
| RN-F01 | `consumo = distanciaKm × categoria.taxaPorKm()`. É o consumo teórico, mesmo que a bateria se esgote. |
| RN-F02 | `bruto = bateriaInicial − consumo`; `bateriaFinal = (bruto < 0.0 \|\| bruto > 100.0) ? 0.0 : bruto`. Espelha a RN01 e a RN04 sem estado mutável. |
| RN-F03 | `critica = bateriaFinal < 20.0` (P01). |
| RN-F04 | Os ids das viagens são sequenciais, começando em 1, na ordem de geração. |
| RN-F05 | Algoritmo do gerador, chamado em ordem para cada viagem `i = 1..n` com um único `Random(2026)`: `categoria = random.nextInt(100) < 30 ? VAN : BICICLETA`; `distanciaKm = 5 + random.nextInt(116)`; `bateriaInicial = 40 + random.nextInt(61)`. A geração é **sempre sequencial**; só o processamento pode ser paralelo. |
| RN-F06 | Agregação: para cada categoria, `reduce` com identidade `ResumoCategoria.vazio(cat)` e operação `combinar`, que é associativa. É proibido acumular em variáveis externas. |
| RN-F07 | Totais acumulados em `long`: km inteiros e consumo em centésimos (`Math.round(consumo × 100)`). Isso torna a soma exata e independente da ordem, o que garante a P03 com `equals`. A exibição divide por 100.0. |
| RN-F08 | Como o gerador começa sempre com a mesma semente, o lote pequeno é igual às 10 primeiras viagens do lote grande. |

## 17. Validações

| Onde | Campo/entrada | Validação | Comportamento em caso de violação |
|---|---|---|---|
| `Veiculo.setAutonomiaBateria` | `bateria` | 0.0 ≤ x ≤ 100.0 | Grava 0.0 (RN01). Não lança exceção |
| Construtores de `VanEletrica`/`BicicletaEletrica` | `autonomiaBateria` | Via `setAutonomiaBateria` | Igual à RN01 |
| `registrarViagem` | `distancia` | Sem validação extra (o enunciado não pede) | Distância negativa aumentaria a bateria; se passar de 100, vira 0.0 pela RN01. Registrado como risco na §29 |
| `Viagem` (construtor compacto) | `categoria`, `distanciaKm`, `bateriaInicial` | Não nulo; ≥ 0; 0 a 100 | `IllegalArgumentException` |
| `ResumoCategoria.combinar` | `outro.categoria` | Igual a `this.categoria` | `IllegalArgumentException` |
| `GeradorLote.gerar` | `quantidade` | ≥ 0 | `IllegalArgumentException` |
| `gerar_relatorio.py` | Arquivos referenciados | Existência | Aborta com mensagem e código 1 |

## 18. Consultas, Relatórios e Indicadores

### 18.1 Indicadores exibidos

| Indicador | Programa/seção | Natureza |
|---|---|---|
| Distância e bateria resultante por viagem | `telemetria.Main` | Obrigatório |
| Nível final de cada veículo | `telemetria.Main` | Obrigatório |
| Lista de viagens com consumo e bateria final | §15.2.1 | Obrigatório |
| Viagens críticas e total | §15.2.2 | Premissa P01 |
| Viagens calculadas sob demanda | §15.2.3 | Complementar |
| Totais por categoria (lote pequeno) | §15.2.4 | Obrigatório |
| Totais, tempos e igualdade (lote grande) | §15.2.5 | Complementar |
| Resultado da verificação (N/8) | §15.3 | Apoio |

### 18.2 Quadro "onde está cada conceito" (vai no Tópico 3 do relatório)

| Conceito | Onde aparece no código |
|---|---|
| Imutabilidade | `record Viagem`, `ResultadoViagem`, `ResumoCategoria`; listas de `toList()`/`List.copyOf`; `combinar` devolve um novo objeto |
| Função pura | `CalculoViagem.calcular`, `CalculoViagem.ehCritica`, `ResumoCategoria.combinar` |
| Avaliação preguiçosa | Seção §15.2.3: `peek` + `filter` + `findFirst` calculam só o necessário |
| Função de ordem superior | `PipelineTelemetria.transformar`/`agregar` recebem `Function<List<Viagem>, Stream<Viagem>> fonte`; `map`, `filter` e `reduce` recebem funções |
| MAP | `.map(CalculoViagem::calcular)`, `.map(ResumoCategoria::de)` |
| FILTER | `.filter(ResultadoViagem::critica)`, `.filter(v -> v.categoria() == cat)` |
| REDUCE | `.reduce(ResumoCategoria.vazio(cat), ResumoCategoria::combinar)` |
| Paralelismo seguro | `PARALELO = List::parallelStream` com o mesmo pipeline; igualdade comprovada |

## 19. Uploads, Anexos e Arquivos

- Não há upload no sistema.
- **Arquivos gerados:**
  - `out/` (compilados): não versionado.
  - `relatorio/gerado/*.docx`: não versionado, porque contém nome completo e RA.
- **Arquivos versionados de apoio:**
  - `relatorio/saidas/*.txt`: sem dados pessoais.
  - `relatorio/evidencias/actions_aprovado.png`: imagem da página do workflow aprovado, recortada para não mostrar dados além do nome do repositório.
- A imagem é capturada depois da primeira execução aprovada, manualmente pelo autor ou pelo navegador, com a autorização dele.

## 20. APIs e Integrações Externas

O sistema não consome APIs. A única integração é a infraestrutura de CI.

### 20.1 Workflow `.github/workflows/executar.yml`
- **Nome:** `Executar e verificar`.
- **Gatilhos:** `push` (todas as branches) e `workflow_dispatch`.
- **Job** `executar`, em `runs-on: ubuntu-latest`. Passos, em ordem:
  1. `actions/checkout@v4`.
  2. `actions/setup-java@v4` com `distribution: temurin` e `java-version: '21'`.
  3. **Compilar:** `javac -encoding UTF-8 -d out src/telemetria/*.java src/processamento/*.java src/verificacao/*.java`.
  4. **Executar módulo OO:** `java -Dstdout.encoding=UTF-8 -cp out telemetria.Main | tee saida_telemetria.txt`.
  5. **Conferir módulo OO:** `grep -q "Van elétrica: 75.0%" saida_telemetria.txt && grep -q "Bicicleta elétrica: 90.0%" saida_telemetria.txt`.
  6. **Executar módulo funcional:** `java -Dstdout.encoding=UTF-8 -cp out processamento.ProcessamentoMain | tee saida_processamento.txt`.
  7. **Conferir módulo funcional:** `grep -q "Resultados idênticos: SIM" saida_processamento.txt`.
  8. **Verificar regras:** `java -Dstdout.encoding=UTF-8 -cp out verificacao.VerificacaoRegras | tee saida_verificacao.txt`.
  9. **Publicar saídas:** `actions/upload-artifact@v4` com os três `.txt` (nome `saidas`), executado com `if: always()`, para copiar ao relatório.
- **Configuração:** declarar `defaults: run: shell: bash` no job. Com `shell: bash` explícito, o Actions executa com `-eo pipefail`, então uma falha do `java` antes do `tee` não é mascarada.
- Não usa `secrets` nem permissões especiais.

## 21. Auditoria
Não se aplica. O histórico de commits do Git e os logs do Actions servem de rastro da evolução e das execuções.

## 22. Soft Delete
Não se aplica: não há exclusão nem persistência de registros.

## 23. Logs de Erro e Logs de Segurança
- **Erros:** aparecem na saída padrão ou de erro do console. Os programas `ProcessamentoMain` e `VerificacaoRegras` encerram com código 1 em caso de falha de verificação, o que faz o workflow falhar.
- **Exceções inesperadas** não são capturadas genericamente: devem aparecer com stack trace, para corrigir.
- **Logs de segurança:** não se aplica.
- **Exposição de dados:** nenhum log ou saída contém nome completo nem RA.

## 24. Configurações Globais do Sistema

| Constante | Valor | Local |
|---|---|---|
| Taxa van | 0.5 | Literal em `VanEletrica.registrarViagem` e `Categoria.VAN` |
| Taxa bicicleta | 0.2 | Literal em `BicicletaEletrica.registrarViagem` e `Categoria.BICICLETA` |
| Limite de bateria válido | 0.0 a 100.0 | Literais em `Veiculo.setAutonomiaBateria` e `CalculoViagem` |
| Limite crítico | 20.0 | `CalculoViagem.LIMITE_CRITICO` |
| Semente | 2026 | `GeradorLote.SEMENTE` |
| Lote pequeno | 10 | Constante `private static final` em `ProcessamentoMain` |
| Lote grande | 100000 | Constante `private static final` em `ProcessamentoMain` |
| Rodadas de medição | 1 de aquecimento + 5 medidas | Constantes em `ProcessamentoMain` |
| Locale de formatação | `Locale.ROOT` | Todos os `String.format` |

## 25. Estratégia de Testes

Não há framework de testes (fora de escopo). A verificação é feita em três camadas:

1. **Compilação sem erros:** `javac` com saída limpa, local e no Actions.
2. **Verificação de regras:** `verificacao.VerificacaoRegras` (§15.3), com 8 casos e código de saída 1 em caso de falha.
3. **Conferência de saída no CI:** `grep` das linhas obrigatórias (75.0, 90.0 e "Resultados idênticos: SIM").

**Checagens manuais por fase:**
- Conferir o formato da saída com a §15.
- Conferir que `telemetria` tem exatamente 4 arquivos e que não há membros extras (§12.1).
- Conferir que nenhuma lambda do pipeline escreve em estado externo (§7).
- No relatório: conferir os dois checklists da §27.

## 26. Plano de Implementação em Fases

| Fase | Entregas | Critério de conclusão |
|---|---|---|
| 1 — Base e Módulo OO | `git init` e configuração local de autoria; `.gitignore`, `.gitattributes`, README mínimo; as 4 classes de `telemetria`; `VerificacaoRegras` | Compila; `Main` mostra a saída exata da §15.1; verificação 8/8 OK |
| 2 — Funcional: núcleo | `Categoria`, os 3 records, `CalculoViagem`, `GeradorLote`, `PipelineTelemetria`; seções 15.2.1 a 15.2.4 de `ProcessamentoMain` | Lote determinístico (duas execuções dão saída idêntica); críticas corretas; a avaliação preguiçosa aparece como especificado |
| 3 — Funcional: complementar | Seção 15.2.5 (lote de 100.000, sequencial × paralelo, tempos) | "Resultados idênticos: SIM"; tempos exibidos |
| 4 — Repositório e CI | Workflow `executar.yml` (os três pacotes já existem, então o comando completo de compilação da §20.1 é válido); criação do repositório `paradigmas-mapa-2026` (público) com autorização do autor; push; captura das saídas e da imagem | Execução aprovada no Actions; `relatorio/saidas/*.txt` e `relatorio/evidencias/actions_aprovado.png` versionados |
| 5 — Relatório | `gerar_relatorio.py`, `requirements.txt`, `identificacao.exemplo.txt`, os 10 arquivos de `conteudo/`; geração do `.docx` | `.docx` abre corretamente; checklists da §27.3 e §27.4 atendidos; Tarefa 3 com 10 a 20 linhas |

Cada fase termina com um ou mais commits de autoria "Antonio Santos". Nenhum envio ao GitHub ocorre sem a confirmação do autor.

## 27. Critérios de Aceitação Técnicos

### 27.1 Módulo OO (obrigatório)
- [ ] O pacote `telemetria` contém exatamente `Veiculo`, `VanEletrica`, `BicicletaEletrica` e `Main`, todos com `package telemetria;`.
- [ ] Os membros são exatamente os da §12.1, com os tipos e visibilidades indicados.
- [ ] `Veiculo` é `abstract`; `new Veiculo()` não compila.
- [ ] O construtor sem parâmetros inicia `0`, `""`, `0.0` e `0.0`.
- [ ] `setAutonomiaBateria` usa uma estrutura condicional e cumpre a RN01 (verificação 5/5 casos de RN01).
- [ ] As subclasses usam `extends`, atribuem os atributos `protected` e chamam `setAutonomiaBateria()` no construtor.
- [ ] `registrarViagem` tem `@Override`, a assinatura exata, usa `getAutonomiaBateria()` e grava com `setAutonomiaBateria()`.
- [ ] A saída de `Main` é igual à da §15.1 (75.0 e 90.0).
- [ ] Não há erros de compilação nem exceções em tempo de execução.
- [ ] O código está indentado com 4 espaços, com comentários objetivos em português e `@author Antonio Santos` no Javadoc de cada classe.

### 27.2 Módulo funcional
- [ ] (Obrigatório) Existem as etapas MAP, FILTER e REDUCE, identificáveis conforme a §18.2.
- [ ] (P04) Nenhum dado de entrada é alterado; todos os tipos de dados do pipeline são `record`.
- [ ] (P05) Nenhuma lambda do pipeline escreve em estado externo (exceto o `peek` didático, que só imprime).
- [ ] (P02) Duas execuções seguidas produzem saídas idênticas, exceto as linhas de tempo.
- [ ] (P01) A lista de críticas contém todas as viagens com `bateriaFinal < 20.0` e nenhuma outra.
- [ ] (Complementar) "Pipeline montado…" aparece antes de qualquer "calculando viagem", e K < 10 quando existe uma crítica antes da 10ª viagem.
- [ ] (P03) "Resultados idênticos: SIM" no lote de 100.000.
- [ ] (Complementar) Tempos médios exibidos em ms, com a observação de variabilidade.

### 27.3 Relatório — Parte I (PROJETO)
- [ ] Identificação com nome completo e RA (de `identificacao.local.txt`).
- [ ] Ordem: Identificação → Tarefa 1 → Tarefa 2 → Tarefa 3.
- [ ] A Tarefa 1 tem a modelagem textual, a justificativa de `Veiculo` abstrata e a explicação de `protected`/`private`.
- [ ] A Tarefa 2 tem o código das 4 classes, cada uma identificada, e a saída real.
- [ ] A Tarefa 3 tem entre 10 e 20 linhas e cobre os 4 pontos.

### 27.4 Relatório — Parte II (REQUISITOS)
- [ ] O Tópico 1 explica o paradigma funcional, a imutabilidade, as funções puras, a avaliação preguiçosa e as funções de ordem superior, e por que cada uma importa.
- [ ] O Tópico 2 compara o funcional e o imperativo, com vantagens e desvantagens ligadas ao cenário.
- [ ] O Tópico 3 informa a linguagem, traz o código, explica o funcionamento, mostra o quadro da §18.2, a saída real e a evidência remota, e declara P00 a P05.
- [ ] O Tópico 4 relaciona os benefícios ao cenário e explica como a imutabilidade e as funções puras favorecem o paralelismo.
- [ ] O Tópico 5 traz uma conclusão crítica com desempenho, manutenção, experiência da equipe, curva de aprendizado e quando o funcional não é a melhor escolha.
- [ ] Extensão suficiente para atender integralmente os tópicos.
- [ ] TOKUMOTO (2022) é referenciado, sem cópia literal.

### 27.5 CI e repositório
- [ ] O workflow é aprovado no push mais recente.
- [ ] O repositório é público e não contém `out/`, `.docx` nem `identificacao.local.txt`.
- [ ] Os commits têm autoria "Antonio Santos".

## 28. Glossário e Convenções de Nomenclatura

**Glossário**

| Termo | Significado |
|---|---|
| Autonomia da bateria | Nível de carga, em ponto percentual, de 0 a 100 |
| Ponto percentual | Unidade de redução da bateria por km |
| Viagem crítica | Viagem cuja bateria final fica abaixo de 20% |
| Lote | Coleção imutável de viagens simuladas |
| Pipeline | Sequência transformar → selecionar → agregar |
| Avaliação preguiçosa | Cálculo adiado até que o resultado seja pedido por uma operação terminal |
| Função de ordem superior | Função que recebe ou devolve outra função |
| Função pura | Mesmo resultado para a mesma entrada, sem efeitos colaterais |
| CI | Integração contínua: execução automática a cada envio (GitHub Actions) |

**Convenções**
- Classes, records e enum em `PascalCase`. Métodos e variáveis em `camelCase`. Constantes em `UPPER_SNAKE_CASE`. Pacotes em minúsculas.
- Nomes de domínio em português, sem acento em identificadores. Acentos só em strings e comentários.
- Fontes em UTF-8, fins de linha LF, indentação de 4 espaços, chaves no estilo K&R.
- Javadoc curto no topo de cada classe, com uma frase de propósito e `@author Antonio Santos`. Comentários de linha só onde explicam uma regra (RN01, taxas, imutabilidade, paralelismo), sem comentários óbvios.
- Mensagens de commit em português, no imperativo, curtas. Exemplo: `Implementa validação da bateria em Veiculo`.

## 29. Pendências e Riscos Conhecidos

**Pendências (não bloqueiam a Fase 1)**
1. **Política da UniCesumar sobre uso de IA:** o autor deve verificar e revisar o texto final com as próprias palavras.
2. **Criação do repositório (Fase 4):** pela interface web do GitHub, ou instalando e autenticando o GitHub CLI, sempre com a autorização do autor.
3. **E-mail dos commits (Fase 1):** o e-mail do GitHub do autor ou o endereço `noreply` do GitHub. Até a decisão, usar o `noreply` do GitHub do autor, informado por ele.
4. **Instalação do `python-docx` (Fase 5):** `pip install -r relatorio/requirements.txt`.
5. **Itens complementares:** todos mantidos por padrão. Podem ser retirados sem afetar o obrigatório.

**Riscos**

| Risco | Impacto | Mitigação |
|---|---|---|
| Avaliador esperar só OO ou só funcional | Médio | Relatório em duas partes independentes |
| Distância negativa em `registrarViagem` aumenta a bateria | Baixo (não exigido) | Não validar, para respeitar o enunciado; citar na Tarefa 3 se couber |
| A RN01 zera valores acima de 100 (perde informação) | Baixo | Seguir o enunciado; explicar na Tarefa 3 |
| Paralelo mais lento que sequencial | Baixo | Usar como argumento crítico no Tópico 5 |
| Acentos trocados no console do Windows | Baixo | `-Dstdout.encoding=UTF-8` e `chcp 65001`; as saídas do relatório vêm do Actions (Linux, UTF-8) |
| Diferença de arredondamento entre os modos | Eliminado | Totais em `long` (RN-F07) |
| Exposição de dados pessoais no repositório público | Médio | `identificacao.local.txt` e `.docx` no `.gitignore` |
| Texto com aparência genérica | Médio | Conteúdo em arquivos editáveis; revisão final do autor |

## 30. Resumo Final do FSD

O sistema é composto por três programas Java 21 de console, compilados com `javac` puro:

- **`telemetria` (OO, obrigatório):**
  - `Veiculo` abstrata, com a bateria `private` e validada (fora de 0 a 100 vira 0.0);
  - `VanEletrica` (0.5 por km) e `BicicletaEletrica` (0.2 por km), com `registrarViagem` polimórfico;
  - `Main` demonstra 50 km resultando em **75.0** e **90.0**.
- **`processamento` (funcional):**
  - records imutáveis e funções puras;
  - pipeline `map` → `filter` (crítica abaixo de 20%) → `reduce` (totais exatos em `long`);
  - a fonte do stream é passada como função de ordem superior, sequencial ou paralela;
  - avaliação preguiçosa visível;
  - lote determinístico (semente 2026) de 10 e 100.000 viagens, com prova de resultados idênticos e tempos.
- **`verificacao`:** 8 casos de regra, sem framework.

O GitHub Actions compila, executa e confere as saídas a cada push, no repositório público `paradigmas-mapa-2026`. Um script Python gera o `.docx` único, com a Parte I (Tarefas 1 a 3) e a Parte II (Tópicos 1 a 5), a partir de textos editáveis, do código e das saídas reais. Os dados pessoais ficam fora do repositório.

A implementação segue 5 fases testáveis (§26).
