# STATUS DO PROJETO

## Última atualização: 08/11/2026

## Fase atual: Fase 3 concluída. Próxima: Fase 4 — Repositório GitHub e CI

## Progresso por fase

| Fase | Nome | Status |
|---|---|---|
| 0 | Esqueleto e controle de versão | **Concluída**: esqueleto, docs, Git local na branch `main`, autoria "Antonio Santos" e 1º commit `4755dac` |
| 1 | Módulo OO (`telemetria`) e verificação | **Concluída**: 4 classes em `telemetria` e `VerificacaoRegras`. Compila sem avisos (comando oficial); `Main` imprime 75.0% e 90.0% no formato da FSD §15.1; verificação 8/8 OK; testado no Git Bash e no PowerShell |
| 2 | Módulo funcional: núcleo | **Concluída**: `Categoria`, 3 records, `CalculoViagem`, `GeradorLote` (semente 376), `PipelineTelemetria` e as seções §15.2.1 a §15.2.4. Saída determinística; 10 linhas e totais conferidos de forma independente; 3 críticas; a avaliação preguiçosa para em 3 de 10 |
| 3 | Módulo funcional: complementar | **Concluída**: seção §15.2.5 com 100.000 viagens, 1 aquecimento e 5 rodadas medidas por modo, `Resultados idênticos: SIM` e `System.exit(1)` se diferirem. Programa inteiro roda em cerca de 1,3 s; totais iguais em execuções seguidas |
| 4 | Repositório GitHub e CI | Não iniciada (remoto ainda não conectado, de propósito) |
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

## Próxima ação recomendada
Iniciar a Fase 4 (`docs/PLANO.md`): criar `.github/workflows/executar.yml`, criar o repositório `Santossan2210/paradigmas-mapa-2026` (pela web ou com o GitHub CLI, com autorização) e fazer o push **só depois da confirmação do autor**.
