# STATUS DO PROJETO

## Última atualização: 08/11/2026

## Fase atual: Fase 1 concluída. Próxima: Fase 2 — Módulo funcional: núcleo

## Progresso por fase

| Fase | Nome | Status |
|---|---|---|
| 0 | Esqueleto e controle de versão | **Concluída**: esqueleto, docs, Git local na branch `main`, autoria "Antonio Santos" e 1º commit `4755dac` |
| 1 | Módulo OO (`telemetria`) e verificação | **Concluída**: 4 classes em `telemetria` e `VerificacaoRegras`. Compila sem avisos (comando oficial); `Main` imprime 75.0% e 90.0% no formato da FSD §15.1; verificação 8/8 OK; testado no Git Bash e no PowerShell |
| 2 | Módulo funcional: núcleo | Não iniciada |
| 3 | Módulo funcional: complementar | Não iniciada |
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

## Próxima ação recomendada
Iniciar a Fase 2 (`docs/PLANO.md`): `Categoria`, os records, `CalculoViagem`, `GeradorLote`, `PipelineTelemetria` e as seções §15.2.1 a §15.2.4 de `ProcessamentoMain`.
