# PLANO DE CONSTRUÇÃO DO PROJETO

**Projeto:** Telemetria de Frota Sustentável — EcoFleet Solutions S.A.
**Elaborado por:** Antonio Santos
**Data:** 08/11/2026
**Base:** `docs/FSD.md` v1.1. As referências "§" apontam para seções do FSD.

---

## Visão Geral das Fases

| Fase | Nome | Depende de | Natureza |
|---|---|---|---|
| 0 | Esqueleto e controle de versão | — | Preparação |
| 1 | Módulo OO (`telemetria`) e verificação | 0 | Obrigatório |
| 2 | Módulo funcional: núcleo | 0 | Obrigatório |
| 3 | Módulo funcional: complementar (lote grande, sequencial × paralelo) | 2 | Complementar |
| 4 | Repositório GitHub e CI (Actions) | 1, 2, 3 | Obrigatório |
| 5 | Relatório `.docx` (Partes I e II) | 4 | Obrigatório |

As fases 1 e 2 não dependem uma da outra, porque o pacote `processamento` não importa `telemetria`. Mesmo assim, seguem em ordem, para cada commit ter um escopo claro.

---

## Fase 0 — Esqueleto e controle de versão

### Objetivo
Deixar o projeto pronto para receber código: pastas, configuração mínima e Git local com a autoria correta.

### Entregas
- [x] Pastas da §8: `.github/workflows/`, `src/telemetria/`, `src/processamento/`, `src/verificacao/`, `relatorio/conteudo/`, `relatorio/saidas/`, `relatorio/evidencias/` (com `.gitkeep`).
- [x] `.gitignore`, com o mínimo da §8 mais os materiais da disciplina (`/Livro.pdf`, `/Aula*.pdf`, `/Dashboard.webp`).
- [x] `.gitattributes` (LF para texto; binários marcados).
- [x] `README.md` mínimo, `relatorio/requirements.txt` (`python-docx==1.1.2`) e `relatorio/identificacao.exemplo.txt`.
- [x] `CLAUDE.md`, `docs/PLANO.md`, `docs/STATUS.md` e `docs/ERROS.md`.
- [x] `git init -b main`, `git config user.name "Antonio Santos"` e `git config user.email` com o endereço `noreply` do GitHub do autor, **só no repositório local**.
- [x] Primeiro commit com a documentação e o esqueleto (`4755dac`).

### Critérios de Conclusão
- `git status` **não** lista `Livro.pdf`, `Aula*.pdf` nem `Dashboard.webp`.
- `git log` mostra o autor "Antonio Santos".
- Nenhum caminho absoluto em arquivos versionados.

### Status: Concluída (08/11/2026)

---

## Fase 1 — Módulo OO (`telemetria`) e verificação

### Objetivo
Implementar exatamente as 4 classes do enunciado e o programa de verificação das regras.

### Entregas
- `src/telemetria/Veiculo.java`, com todos os membros da §12.1:
  - `abstract`;
  - os 4 atributos com as visibilidades corretas;
  - construtor sem parâmetros com `0`, `""`, `0.0` e `0.0`;
  - `getAutonomiaBateria()`;
  - `setAutonomiaBateria(double bateria)`, com a RN01 em `if/else`;
  - `public abstract void registrarViagem(double distancia);`.
- `src/telemetria/VanEletrica.java`:
  - `extends Veiculo` e o atributo `private String placaIdentificacao`;
  - construtor `(int, String, double, double, String)` que atribui os campos `protected` e chama `setAutonomiaBateria`;
  - `@Override registrarViagem` com o literal `0.5`, a mensagem da §15.1 e `String.format(Locale.ROOT, …)`.
- `src/telemetria/BicicletaEletrica.java`: idem, com `private double limiteVelocidadeAssistida` e o literal `0.2`.
- `src/telemetria/Main.java`:
  - cria `VanEletrica(1, "EcoCargo E-Van 1500", 1500.0, 100.0, "ECO1A26")` e `BicicletaEletrica(2, "EcoBike Urban 25", 30.0, 100.0, 25.0)`;
  - simula 50.0 km em cada um;
  - imprime exatamente a saída da §15.1.
- `src/verificacao/VerificacaoRegras.java`, com os 8 casos da §15.3, `[OK]`/`[FALHA]`, o resumo `N/8` e `System.exit(1)` em caso de falha.
- Remover `src/telemetria/.gitkeep` e `src/verificacao/.gitkeep`.
- Javadoc com `@author Antonio Santos` em cada classe; 4 espaços de indentação; UTF-8.

### Critérios de Conclusão
- `javac -encoding UTF-8 -d out src/telemetria/*.java src/verificacao/*.java` sem erros nem avisos. Confirmar se o curinga funciona no PowerShell; se não, usar o Git Bash (§6.1).
- `java -Dstdout.encoding=UTF-8 -cp out telemetria.Main` imprime **exatamente** as 6 linhas da §15.1, com **75.0%** e **90.0%**.
- `java -Dstdout.encoding=UTF-8 -cp out verificacao.VerificacaoRegras` imprime `Verificação concluída: 8/8 casos OK` e sai com código 0.
- `src/telemetria/` contém exatamente 4 arquivos `.java` e nenhum membro além dos da §12.1.
- Commit: `Implementa módulo OO de telemetria e verificação das regras`.

### Status: Concluída (08/11/2026)

---

## Fase 2 — Módulo funcional: núcleo

### Objetivo
Implementar os tipos imutáveis, as funções puras, o gerador determinístico e o pipeline, com as seções obrigatórias da saída.

### Entregas
- `Categoria.java`: enum com `VAN(0.5, "Van elétrica")` e `BICICLETA(0.2, "Bicicleta elétrica")`, `taxaPorKm()` e `rotulo()`.
- `Viagem.java`: record `(int id, Categoria categoria, int distanciaKm, int bateriaInicial)`, com construtor compacto que valida os dados (§17).
- `ResultadoViagem.java`: record `(Viagem viagem, double consumo, double bateriaFinal, boolean critica)`.
- `ResumoCategoria.java`: record `(Categoria, long viagens, long kmTotal, long consumoCentesimos, long alertas)`, com `vazio`, `de`, `combinar` (associativo, valida a categoria) e `consumoTotal()`.
- `CalculoViagem.java`: `calcular` (RN-F01 a RN-F03), `ehCritica` e `LIMITE_CRITICO = 20.0`.
- `GeradorLote.java`: `SEMENTE = 376L` e `gerar(int)` com o algoritmo RN-F05, devolvendo uma lista imutável e validando `quantidade >= 0`.
- `PipelineTelemetria.java`: `transformar`, `selecionarCriticas` e `agregar`, com o parâmetro `Function<List<Viagem>, Stream<Viagem>> fonte`, as constantes `SEQUENCIAL`/`PARALELO` e o comentário explicando por que `agregar` recalcula a partir das viagens (§12.2).
- `ProcessamentoMain.java`: constante `LOTE_PEQUENO = 10` e as seções §15.2.1 a §15.2.4. `LOTE_GRANDE` fica para a Fase 3, onde é usado.
- Remover `src/processamento/.gitkeep`.

### Critérios de Conclusão
- Compila junto com os demais pacotes, sem erros.
- Duas execuções seguidas produzem saída **idêntica** (lote determinístico).
- Conferência manual das 10 viagens:
  - `bateriaFinal = bateriaInicial − distância × taxa` (ou 0.0 se negativo);
  - `critica` ⇔ `bateriaFinal < 20.0`.
- Na seção de avaliação preguiçosa, "Pipeline montado…" aparece **antes** de qualquer "calculando viagem #NN", e "Viagens calculadas: K de 10" bate com o id encontrado.
- Os totais da agregação do lote pequeno batem com a soma manual das 10 linhas.
- Nenhuma lambda do pipeline escreve em estado externo (exceto o `peek` didático, que só imprime).
- Commit: `Implementa pipeline funcional de telemetria`.

### Status: Concluída (08/11/2026)

---

## Fase 3 — Módulo funcional: complementar

### Objetivo
Comparar os modos sequencial e paralelo sobre 100.000 viagens, provando que o resultado é idêntico e medindo os tempos.

### Entregas
- Seção §15.2.5 em `ProcessamentoMain`:
  - 1 rodada de aquecimento e 5 rodadas medidas por modo, com `System.nanoTime()`;
  - média em ms com `%.1f`;
  - comparação `equals` entre os resultados;
  - `Resultados idênticos: SIM/NÃO`, a observação sobre a variabilidade dos tempos e `System.exit(1)` se forem diferentes.
- Constantes `RODADAS_AQUECIMENTO = 1` e `RODADAS_MEDIDAS = 5`.

### Critérios de Conclusão
- Imprime `Resultados idênticos: SIM`.
- Os tempos aparecem nas duas linhas, no formato da §15.2.5.
- Execução total do programa em poucos segundos no ambiente local.
- Commit: `Adiciona comparação sequencial x paralelo no lote grande`.

### Status: Não iniciada

---

## Fase 4 — Repositório GitHub e CI

### Objetivo
Publicar o código no repositório público e comprovar a execução em ambiente remoto gratuito.

### Entregas
- `.github/workflows/executar.yml` exatamente como a §20.1:
  - `push` + `workflow_dispatch`, `ubuntu-latest`, `checkout@v4`, `setup-java@v4` (Temurin 21);
  - `defaults.run.shell: bash`;
  - compila, executa os 3 programas com `tee`, roda os `grep` de conferência;
  - `upload-artifact@v4` (`saidas`, com `if: always()`).
- Remover `.github/workflows/.gitkeep`.
- Repositório `Santossan2210/paradigmas-mapa-2026` (público), criado pelo autor pela web, ou pelo GitHub CLI com autorização.
- Remoto `origin` configurado e push. **Só depois da confirmação do autor sobre o que será enviado.**
- Download do artefato `saidas`; cópia para `relatorio/saidas/` como `telemetria_main.txt`, `processamento_main.txt` e `verificacao_regras.txt`.
- `relatorio/evidencias/actions_aprovado.png`, com a captura da execução aprovada.
- Preencher `repositorio_url` e `actions_url` no `identificacao.local.txt`, que não é versionado.

### Critérios de Conclusão
- Workflow **aprovado** (verde) no último push.
- O repositório público não contém `out/`, `.docx`, `identificacao.local.txt` nem os materiais da disciplina.
- As saídas e a evidência estão versionadas.
- Commit: `Adiciona workflow de execução remota e evidências`.

### Status: Não iniciada

---

## Fase 5 — Relatório `.docx`

### Objetivo
Gerar o arquivo único de entrega, com a Parte I (Tarefas 1 a 3) e a Parte II (Tópicos 1 a 5).

### Entregas
- `relatorio/gerar_relatorio.py`, conforme a §15.4:
  - marcações `#`, `##`, `-`, `[[CODIGO:]]`, `[[SAIDA:]]`, `[[IMAGEM:]]`, `[[TABELA]]` e `{{chave}}`;
  - leitura em `utf-8-sig`;
  - abortar se faltar arquivo ou se um campo de identificação estiver vazio;
  - aviso de 130 a 260 palavras na Tarefa 3;
  - Arial 12, código em Consolas 9, margens de 2,5 cm, Partes I e II em página nova.
- Os 10 arquivos de `relatorio/conteudo/` da tabela da §15.4 (`00_capa` a `90_referencias`), redigidos em paráfrase do Livro-texto (TOKUMOTO, 2022), sem cópia literal.
- `relatorio/identificacao.local.txt` preenchido pelo autor (não versionado).
- `pip install -r relatorio/requirements.txt`.
- `relatorio/gerado/Relatorio_MAPA_Paradigmas_Antonio_Santos.docx`.

### Critérios de Conclusão
- O `.docx` abre corretamente no Word.
- Todos os itens das checklists §27.3 e §27.4 estão atendidos.
- A Tarefa 3 tem entre 10 e 20 linhas, sem aviso do script.
- O Tópico 3 traz o quadro "onde está cada conceito" (§18.2), a explicação de `agregar` e as premissas P00 a P05.
- O autor revisou e reescreveu o texto com as próprias palavras e verificou a política de IA da UniCesumar.
- Commit: `Adiciona gerador e conteúdo do relatório`. O `.docx` em si não é versionado.

### Status: Não iniciada
