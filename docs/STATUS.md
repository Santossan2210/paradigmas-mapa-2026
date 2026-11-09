# STATUS DO PROJETO

## Última atualização: 08/11/2026

## Fase atual: Fase 4 concluída. Próxima: Fase 5 — Relatório `.docx`

## Progresso por fase

| Fase | Nome | Status |
|---|---|---|
| 0 | Esqueleto e controle de versão | **Concluída**: esqueleto, docs, Git local na branch `main`, autoria "Antonio Santos" e 1º commit `4755dac` |
| 1 | Módulo OO (`telemetria`) e verificação | **Concluída**: 4 classes em `telemetria` e `VerificacaoRegras`. Compila sem avisos (comando oficial); `Main` imprime 75.0% e 90.0% no formato da FSD §15.1; verificação 8/8 OK; testado no Git Bash e no PowerShell |
| 2 | Módulo funcional: núcleo | **Concluída**: `Categoria`, 3 records, `CalculoViagem`, `GeradorLote` (semente 376), `PipelineTelemetria` e as seções §15.2.1 a §15.2.4. Saída determinística; 10 linhas e totais conferidos de forma independente; 3 críticas; a avaliação preguiçosa para em 3 de 10 |
| 3 | Módulo funcional: complementar | **Concluída**: seção §15.2.5 com 100.000 viagens, 1 aquecimento e 5 rodadas medidas por modo, `Resultados idênticos: SIM` e `System.exit(1)` se diferirem. Programa inteiro roda em cerca de 1,3 s; totais iguais em execuções seguidas |
| 4 | Repositório GitHub e CI | **Concluída**: repositório público `Santossan2210/paradigmas-mapa-2026`, `origin` configurado, workflow `executar.yml` aprovado (execução nº 2, commit `917eac4`); saídas remotas em `relatorio/saidas/` (iguais às locais, exceto tempos) e captura em `relatorio/evidencias/actions_aprovado.png` |
| 5 | Relatório `.docx` | Não iniciada |

## Ambiente
- JDK Temurin 21.0.13 instalado; `JAVA_HOME` configurado.
- Git 2.54. Configuração local do repositório: `user.name = Antonio Santos`, `user.email` = `noreply` do GitHub, `core.autocrlf = false`. A configuração global não foi alterada.
- GitHub CLI (`gh`): não instalado.
- `python-docx`: não instalado (Fase 5).

## Decisões registradas nesta etapa
- Branch principal: `main`.
- E-mail dos commits: endereço `noreply` do GitHub, para não expor o e-mail pessoal no repositório público.
- Nome completo e RA foram retirados dos documentos versionados (DECISOES_TECNICAS, PRD-01, PRD) e substituídos por referência a `relatorio/identificacao.local.txt`.

## Pendências do autor
- Verificar a política da UniCesumar sobre uso de IA (antes da entrega).
- Criar o repositório `Santossan2210/paradigmas-mapa-2026` (Fase 4).

## Observações da Fase 1
- `docs/ERROS.md`:
  - no PowerShell, a opção `-Dstdout.encoding=UTF-8` precisa de aspas;
  - o aviso `this-escape` só aparece com `-Xlint:all`. Ficou sem correção, porque corrigir mudaria a declaração do FSD. Opcional: tornar `setAutonomiaBateria` `final`, se o autor aprovar.

## Observações da Fase 2
- Semente trocada de 2026 para **376**, com aprovação do autor (ver `docs/ERROS.md`). O FSD e o PLANO foram atualizados.
- O formato das linhas de agregação usa colunas mais largas que o exemplo antigo do FSD, para servir também ao lote de 100.000. A FSD §15.2.4 foi atualizada com o formato e os valores reais.
- `LOTE_GRANDE` não foi criado nesta fase (seria uma constante sem uso); entra na Fase 3.

## Observações da Fase 3
- A medição fica no método `medir`, que devolve um record privado `Medicao` (resultado da última rodada + média em ms). Assim o mesmo código serve aos dois modos, recebendo `SEQUENCIAL` ou `PARALELO` como função.
- Totais do lote grande (semente 376): Van 29.922 viagens / 6.882 alertas; Bicicleta 70.078 / 479. A FSD §15.2.5 foi atualizada com esses valores reais.
- Tempos locais: cerca de 11,8 ms sequencial e 7,1 ms paralelo. Variam a cada execução e não são critério de aceite.

## Observações da Fase 4
- O primeiro push (`edd5592`) falhou por YAML inválido (`: ` em valor sem aspas). Corrigido em `917eac4` e registrado em `docs/ERROS.md`. Por decisão do autor, o commit com falha permanece no histórico (sem reescrita).
- Tempos no servidor do GitHub: 5,2 ms sequencial e 2,0 ms paralelo (localmente: 11,8 e 7,1). Útil para o Tópico 4.
- Avisos do Actions (não bloqueiam): Node.js 20 está obsoleto nas actions `@v4`, e o `setup-java@v4` sugere migrar para `@v5`. Mudar exige atualizar a FSD §20.1, que fixa `@v4`; fica a critério do autor.
- A captura mostra o nome de usuário do GitHub (já público na URL) e a foto de perfil em miniatura.

## Pendências do autor (Fase 4)
- Preencher `repositorio_url` e `actions_url` em `relatorio/identificacao.local.txt` (não versionado).

## Próxima ação recomendada
Iniciar a Fase 5 (`docs/PLANO.md`): `gerar_relatorio.py`, os 10 arquivos de `relatorio/conteudo/`, instalação do `python-docx` e geração do `.docx`. Antes, o autor preenche `relatorio/identificacao.local.txt`.
