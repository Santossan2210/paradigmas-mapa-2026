# STATUS DO PROJETO

## Última atualização: 08/11/2026

## Fase atual: Fase 0 concluída. Próxima: Fase 1 — Módulo OO (`telemetria`) e verificação

## Progresso por fase

| Fase | Nome | Status |
|---|---|---|
| 0 | Esqueleto e controle de versão | **Concluída**: esqueleto, docs, Git local na branch `main`, autoria "Antonio Santos" e 1º commit `4755dac` |
| 1 | Módulo OO (`telemetria`) e verificação | Não iniciada |
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

## Próxima ação recomendada
Iniciar a Fase 1 (`docs/PLANO.md`): as 4 classes do pacote `telemetria` e `verificacao.VerificacaoRegras`.
