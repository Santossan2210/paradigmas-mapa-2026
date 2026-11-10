# DECISÕES TÉCNICAS DO PROJETO

**Projeto:** EcoFleet Solutions S.A. — Paradigmas de Linguagens de Programação (MAPA 54/2026)
**Autor:** Antonio Santos
**Elaborado por:** Antonio Santos
**Data:** 08/11/2026
**Versão:** 1.0

---

## 1. Documentos recebidos

| Documento | Versão | Situação |
|---|---|---|
| `docs/PRD.md` (índice e rastreabilidade) | 1.3 | Aprovado |
| `docs/PRD-01-telemetria.md` (Tarefas 1 a 3, OO) | 1.3 | Aprovado |
| `docs/PRD-02-relatorio-funcional.md` (Tópicos 1 a 5, funcional) | 1.3 | Aprovado |
| `docs/DESIGN.md` | — | Não existe. Não se aplica, porque a saída é só em console |

## 2. Identificação do sistema

- **Nome:** Telemetria de Frota Sustentável — EcoFleet.
- **Tipo:** aplicação de console, sem interface gráfica, sem persistência e sem rede.
- **Partes:**
  - **Módulo OO** (PRD-01): pacote `telemetria`, com as classes `Veiculo`, `VanEletrica`, `BicicletaEletrica` e `Main`.
  - **Módulo funcional** (PRD-02): pipeline de transformar, selecionar e agregar sobre um lote simulado de viagens, mais a comparação entre sequencial e paralelo.
  - **Entregável acadêmico:** um único `.docx` em duas partes.
- **Autoria:** Antonio Santos (código, commits e relatório). A identificação formal (nome completo e RA do autor, definidos em `relatorio/identificacao.local.txt`) aparece só no cabeçalho do relatório.

## 3. Decisões técnicas confirmadas

### 3.1 Linguagem e paradigmas
- **Java 21 LTS** nos dois módulos.
- **Módulo OO:** classe abstrata, herança (`extends`), encapsulamento (`private` e `protected`), construtores e polimorfismo (`@Override`), exatamente como no enunciado.
- **Módulo funcional:** API de Streams (`map`, `filter`, `reduce` e `collect`), expressões lambda, referências a método, `record` para dados imutáveis e `parallelStream()` para o modo paralelo.

### 3.2 Build
- **Sem ferramenta de build:** só `javac` e `java`. Não haverá Maven, Gradle nem dependências externas.
- Os comandos de compilar e executar são os mesmos na máquina local e no ambiente remoto.

### 3.3 Repositório
- **GitHub:** `https://github.com/Santossan2210/paradigmas-mapa-2026`, público.
- **Autoria dos commits:** nome "Antonio Santos", configurado **só neste repositório**. A configuração global atual da máquina é "Santos" e não será alterada.
- Antes de cada envio ao GitHub, o conteúdo é mostrado ao autor e enviado só com a confirmação dele.

### 3.4 Teste remoto
- **GitHub Actions**, disparado a cada envio ao repositório: compila e executa os dois módulos com JDK 21 (Temurin).
- O log público da execução e uma imagem da execução aprovada servem de evidência no relatório.

### 3.5 Entrega
- **Um único arquivo `.docx`**, organizado assim:
  - Identificação (nome completo, RA e links do repositório e do Actions);
  - **Parte I — PROJETO:** Tarefa 1, Tarefa 2 (código das 4 classes e a saída) e Tarefa 3;
  - **Parte II — Relatório Funcional:** Tópicos 1 a 5, com o código, a saída e a evidência remota no Tópico 3;
  - Referências.

### 3.6 Ordem de construção: 5 etapas testáveis

| Etapa | Conteúdo | Critério para avançar |
|---|---|---|
| 1 | PRD-01: `Veiculo`, `VanEletrica`, `BicicletaEletrica`, `Main` | Compila sem erro; a saída mostra **75.0** (van) e **90.0** (bicicleta); -5 e 120 viram 0.0 |
| 2 | PRD-02, núcleo: pipeline sobre o lote de 10 viagens e avaliação preguiçosa visível | Viagens críticas (abaixo de 20%) corretas; entrada não alterada; saída mostra a avaliação sob demanda |
| 3 | PRD-02, complementar: lote de 100.000 viagens, sequencial × paralelo e tempos | Totais idênticos nos dois modos; tempos exibidos em ms |
| 4 | Repositório e GitHub Actions | Repositório criado, código enviado, execução aprovada e evidência capturada |
| 5 | Relatório `.docx` com as Partes I e II | Checklists do PRD-01 §11 e do PRD-02 §11.A atendidos; saídas reais coladas |

Cada etapa vira um ou mais commits próprios.

## 4. Decisões adotadas por padrão

| Decisão | Padrão adotado | Motivo |
|---|---|---|
| Distribuição do JDK | Eclipse Temurin 21 | Gratuita, LTS, a mesma no local e no remoto |
| Lote simulado determinístico | Gerador pseudoaleatório com semente fixa | Atende a P02: mesmo lote em toda execução |
| Medição de tempo | `System.nanoTime()`, exibido em ms | Padrão do Java, sem dependências |
| Geração do `.docx` | Script Python com a biblioteca `python-docx` | O Python 3.14 já está na máquina; permite gerar o documento de forma repetível com as saídas reais |
| Separação dos módulos | O módulo funcional fica em pacote próprio, separado de `telemetria` | Mantém o pacote `telemetria` exatamente como o enunciado pede, com 4 classes |
| Versão do relatório | Arquivo gerado fora de `src/`, sem dados além de nome e RA | Evita expor dados pessoais no repositório público |

## 5. Stack e ambientes

| Item | Definição |
|---|---|
| Linguagem | Java 21 LTS |
| JDK local | Eclipse Temurin 21.0.13, já instalado (`JAVA_HOME` configurado) |
| Build | `javac` e `java` puros |
| Controle de versão | Git 2.54 (local) e GitHub (remoto) |
| Ambiente local de desenvolvimento | Windows 11; compilação e execução pelo terminal |
| Ambiente de teste remoto | GitHub Actions (`ubuntu-latest` + Temurin 21) |
| Ambiente de produção | Não se aplica: projeto acadêmico, executado só em console |
| Geração do relatório | Python 3.14 + `python-docx` (a instalar na etapa 5) |
| Banco de dados, interface e frameworks | Nenhum |

## 6. Arquitetura obrigatória

- **Pacote `telemetria`**, só com as 4 classes do enunciado, sem classes extras:
  - `Veiculo`: `abstract`; atributos `protected` `idVeiculo`, `modelo` e `capacidadeCarga`; atributo `private` `autonomiaBateria`; construtor sem parâmetros; `getAutonomiaBateria()` e `setAutonomiaBateria(double bateria)` públicos; `public abstract void registrarViagem(double distancia)`.
  - `VanEletrica` e `BicicletaEletrica`: `extends Veiculo`, cada uma com seu atributo `private`, construtor completo que usa `setAutonomiaBateria()` e `@Override public void registrarViagem(double distancia)`.
  - `Main`: `public static void main(String[] args)`.
- **Módulo funcional** em pacote separado:
  - os dados de viagem e de resultado são `record`, portanto imutáveis;
  - o cálculo é feito por funções puras, que repetem as regras RN02 a RN04 sem alterar nenhum objeto;
  - o mesmo pipeline roda em `stream()` e em `parallelStream()`.
- **Regra de ouro:** nenhum nome de classe, atributo ou método do enunciado pode ser alterado, traduzido ou complementado.

## 7. Recursos estruturais definidos

| Recurso | Situação |
|---|---|
| Autenticação | Não se aplica |
| Perfis de acesso (RBAC) | Não se aplica |
| Auditoria | Não se aplica |
| Exclusão lógica (soft delete) | Não se aplica: não há exclusão nem persistência |
| Log de erros | Não se aplica; erros de compilação ou execução aparecem no console e no log do Actions |
| Log de segurança | Não se aplica |
| Configurações globais | Só as constantes do domínio: taxas 0.5 e 0.2, limite de 20%, semente e tamanhos dos lotes |
| Uploads | Não se aplica |
| Exportações | Não se aplica; a única saída é o console |
| APIs e integrações externas | Nenhuma, além do GitHub e do Actions como infraestrutura de entrega |

## 8. Perfis e permissões em nível alto

Os perfis seguem os PRDs: Avaliador (só leitura), Equipe EcoFleet (fictícia) e Autor (acesso total). No sistema não existe controle de acesso por usuário. A única "permissão" implementada é de código: `autonomiaBateria` é `private` e só muda via `setAutonomiaBateria()`.

## 9. Entidades prováveis em nível alto

- **Veículo** (abstrato), **Van Elétrica** e **Bicicleta Elétrica**, conforme o PRD-01.
- **Viagem** (imutável): veículo, categoria, distância e bateria inicial.
- **Resultado da viagem** (imutável): consumo, bateria final e indicador de viagem crítica.
- **Resumo por categoria**: viagens, km, consumo e alertas.
- **Categoria de veículo**: van ou bicicleta, cada uma com sua taxa de consumo.

## 10. Módulos, telas e fluxos esperados em nível alto

Não há telas; toda a interação é pela saída do console.

| Módulo | Programa | Saída esperada |
|---|---|---|
| OO | `telemetria.Main` | Mensagens de cada viagem e nível final (75.0 / 90.0) |
| Funcional | Programa principal do módulo funcional | Lote de 10 viagens detalhado, viagens críticas, totais por categoria, avaliação preguiçosa visível; lote de 100.000 com totais, tempos e confirmação de resultados idênticos |
| CI | Workflow do GitHub Actions | Compila e executa os dois programas; o log fica público |

## 11. Alertas para relatórios, consultas, exportações e desempenho

- **Os tempos não são benchmark:** variam com a máquina do Actions e com o aquecimento da JVM. O relatório precisa dizer isso explicitamente.
- **O paralelo pode ser mais lento:** em lotes pequenos, ou se o custo de dividir e juntar superar o ganho. Isso deve ser usado como argumento no Tópico 5, não escondido.
- **A agregação paralela precisa ser associativa:** somas por categoria devem usar `collect` ou `reduce` com operações associativas, nunca acumulação em variáveis externas mutáveis. Senão a P03 (resultados idênticos) quebra.
- **Comparação de valores `double`:** somar em ordens diferentes pode gerar diferenças mínimas de arredondamento entre o modo sequencial e o paralelo. O FSD deve definir como comparar: com tolerância, com arredondamento fixo ou acumulando em unidades inteiras.
- **Formatação numérica:** a saída deve usar ponto decimal e uma quantidade fixa de casas, para ficar igual no Windows e no Linux do Actions, sem depender do idioma do sistema.

## 12. Alertas para uploads, anexos e arquivos

- Não há upload no sistema.
- O `.docx` é gerado localmente. Ele contém nome completo e RA; o FSD deve definir se ele vai para o repositório público ou fica fora dele.
- A imagem da execução aprovada no Actions é capturada manualmente ou pelo navegador, e entra só no `.docx`.

## 13. Alertas para logs, auditoria e segurança

- **Dados pessoais:** só nome e RA, apenas no `.docx`. Nada pessoal no código nem nos nomes do repositório.
- **Autoria do Git:** configurar `user.name = Antonio Santos` só no repositório. O e-mail de commit deve ser o que o autor usa no GitHub, ou o e-mail privado `noreply` do GitHub, se ele preferir não expor o próprio.
- **GitHub CLI:** o `gh` não está instalado. Criar o repositório exige login do autor, pela interface web ou instalando e autenticando o `gh`, sempre com a confirmação dele.
- **Actions:** o workflow não usa segredos nem tokens.

## 14. Itens que não devem ser inventados

- Classes, atributos ou métodos além dos do enunciado dentro do pacote `telemetria`.
- Mudanças na RN01: valor inválido vira **0.0**, não é limitado a 100.
- Outras taxas de consumo além de 0.5 e 0.2.
- Recarga, rotas, previsão de autonomia, persistência, interface gráfica ou API.
- Testes unitários com framework, que estão fora de escopo nos PRDs.
- Novas premissas além de P00 a P05 sem registro e aprovação.
- Citações literais longas do material didático; só paráfrase com referência a TOKUMOTO (2022).

## 15. Pendências não bloqueantes

1. **Política da UniCesumar sobre uso de IA:** verificação e revisão final do texto pelo autor.
2. **Criação do repositório no GitHub:** pela interface web ou instalando o `gh`. Decidir na etapa 4.
3. **E-mail dos commits:** o e-mail do GitHub do autor ou o `noreply`. Decidir na etapa 4.
4. **Instalação do `python-docx`:** na etapa 5.
5. **`.docx` no repositório ou fora dele:** decidir no FSD (ver §12).
6. **Manter ou retirar** cada item complementar do PRD-02 §5.B. Por padrão, todos são mantidos.

## 16. Pronto para o FSD

**Sim.** As decisões de linguagem, build, repositório, teste remoto, formato de entrega e ordem de construção estão confirmadas. Nenhuma pendência bloqueia o FSD. O FSD deve detalhar:
- as 5 etapas com critérios de teste;
- a estrutura de pastas e os nomes do módulo funcional;
- o formato exato das saídas do console;
- a forma de comparar resultados `double` (§11);
- o conteúdo do workflow do Actions;
- a estrutura do `.docx`.
