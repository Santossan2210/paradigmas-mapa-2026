# INVENTÁRIO DE INSUMOS DO PROJETO

**Projeto:** Telemetria de Frota Sustentável — EcoFleet Solutions S.A.
**Elaborado por:** Antonio Santos
**Data:** 08/11/2026
**Base:** `docs/FSD.md` v1.1 (validado)

---

## 1. Documentos de Análise (docs/)

| Arquivo | Finalidade | Usado em tempo de execução? | Observações |
|---|---|---|---|
| `docs/PRD.md` | Índice dos PRDs e quadro de rastreabilidade (origem → requisito) | Não | v1.3 |
| `docs/PRD-01-telemetria.md` | Requisitos do Módulo OO (Tarefas 1 a 3) | Não | v1.3 |
| `docs/PRD-02-relatorio-funcional.md` | Requisitos do relatório funcional (Tópicos 1 a 5), premissas P00 a P05 | Não | v1.3 |
| `docs/DECISOES_TECNICAS.md` | Stack, ambientes, repositório e ordem das fases | Não | v1.0 |
| `docs/FSD.md` | **Fonte única da verdade** para a implementação | Não | v1.1, validado |
| `docs/INSUMOS.md` | Este inventário | Não | — |
| `docs/DESIGN.md` | — | — | **Não existe e não é necessário**: o sistema roda só em console |

## 2. Arquivos de Referência Visual/Dados

### 2.1 Na raiz do projeto

| Arquivo | Finalidade | Usado em tempo de execução? | Observações |
|---|---|---|---|
| `Livro.pdf` (16,5 MB) | Livro-texto da disciplina: TOKUMOTO, R. C. *Paradigmas de Linguagem de Programação*. Unicesumar, 2022. Tem capítulo sobre o paradigma funcional (funções puras, imutabilidade, lambda, Clojure/Lisp, paralelismo em CPUs multinúcleo) e sobre OO | **Não** | **Referência principal** para redigir a Tarefa 1, a Tarefa 3 e os Tópicos 1 a 5, sempre em paráfrase e com citação. Material protegido por direitos autorais: **não pode ir para o repositório público** |
| `Aula 01.pdf` | Slides: fundamentos das linguagens e paradigmas | Não | Apoio geral |
| `Aula 02.pdf` | Slides: elementos básicos de um código | Não | Pouco relevante |
| `Aula_03.pdf` | Slides: estruturas de controle e sub-rotinas | Não | Apoio para explicar a condicional de `setAutonomiaBateria` |
| `Aula_04.pdf` | Slides: paradigma imperativo e linguagem C (estado e mudança de estado) | Não | **Relevante** para o Tópico 2 (funcional × imperativo) |
| `Aula_05.pdf` | Slides: programação estruturada em C | Não | Apoio ao Tópico 2 |
| `Aula_06.pdf` | Slides: fundamentos de OO (classes, encapsulamento, visibilidade) | Não | **Relevante** para as Tarefas 1 e 3 |
| `Aula_07.pdf` | Slides: herança, polimorfismo e classes abstratas em Java | Não | **Relevante** para as Tarefas 1, 2 e 3 |
| `Dashboard.webp` | Cronograma da disciplina (Semanas 1 a 10; Semana 8 = Programação Funcional) | Não | Só contexto. Mostra que a aula de Programação Funcional (Semana 8) ainda não está na pasta |

Todos os slides e o cronograma são materiais da UniCesumar, protegidos por direitos autorais, e **não podem ir para o repositório público**.

### 2.2 Fora do projeto (pasta da disciplina no OneDrive)

| Arquivo | Finalidade | Usado em tempo de execução? | Observações |
|---|---|---|---|
| `PROJETO.docx` | Enunciado oficial (Tarefas 1 a 3, OO) | Não | Já transcrito no FSD. Tem nome completo e RA, por isso não deve ser copiado ao repositório |
| `REQUISTOS PARA DESENVOLVIMENTO DO PROJETO.docx` | Orientação dos Tópicos 1 a 5 e instruções do autor | Não | Já transcrito no FSD |
| `MAPA - ... _Instrução.docx` | Igual ao PROJETO | Não | Duplicado |
| `ORIENTAÇÃO PARA A ATIVIDADE DE ESTUDOS MAPA.docx` | Igual ao REQUISITOS, sem as instruções do autor | Não | Duplicado |

### 2.3 Insumos usados em tempo de execução
**Nenhum.** O sistema não carrega imagens, planilhas nem dados externos: o lote é gerado em código, com semente fixa. O script do relatório usa em tempo de geração só arquivos que o próprio projeto vai produzir (§5).

## 3. Inconsistências Encontradas entre FSD e DESIGN
Não se aplica, porque não existe `DESIGN.md` e o FSD não tem interface gráfica.

Fiz também uma conferência cruzada FSD × PRDs × DECISOES_TECNICAS (feita na validação do FSD) e não encontrei inconsistências pendentes.

## 4. Arquivos Não Referenciados em Nenhum Documento

| Arquivo | Situação | Decisão |
|---|---|---|
| `Livro.pdf`, `Aula*.pdf`, `Dashboard.webp` | Estão na raiz do repositório local, mas nenhum documento diz o que fazer com eles. O FSD cita TOKUMOTO (2022) só como referência bibliográfica | **Decidido:** ficam na pasta como consulta local e são **ignorados no Git** (regras na §6) |

**Risco:** sem tratamento, um `git add .` publicaria cerca de 21 MB de material protegido por direitos autorais num repositório público.

## 5. Insumos Ainda Faltando (mencionados no FSD mas ausentes no projeto)

| Insumo | Quem gera | Quando | Bloqueia? |
|---|---|---|---|
| `src/`, `.github/`, `relatorio/` e demais pastas da §8 do FSD | Implementação | Fase 1 em diante | Não (é o trabalho a fazer) |
| `relatorio/identificacao.local.txt` (nome completo, RA, links) | Autor, a partir do modelo | Antes da Fase 5 | Não |
| `relatorio/saidas/*.txt` | Artefato do GitHub Actions | Fase 4 | Não |
| `relatorio/evidencias/actions_aprovado.png` | Captura da execução aprovada | Fase 4 | Não |
| E-mail dos commits (nome ou `noreply` do GitHub) | Autor | **Antes do 1º commit (Fase 1)** | **Bloqueia só o commit**, não o código |
| Repositório `Santossan2210/paradigmas-mapa-2026` | Autor (web) ou CLI autorizado | Fase 4 | Não |
| `python-docx==1.1.2` | `pip install -r relatorio/requirements.txt` | Fase 5 | Não |
| Slides da Semana 8 (Programação Funcional) | Disciplina | Opcional | Não. O `Livro.pdf` cobre o conteúdo |

## 6. Conclusão

**Pronto para preparar o projeto: SIM.**

Não há insumo essencial faltando nem inconsistência entre os documentos de análise. Antes do primeiro commit, duas providências são necessárias:

1. **Proteger os materiais da disciplina — DECIDIDO pelo autor em 08/11/2026: manter na pasta e ignorar no Git.** O `.gitignore` deve conter, além do mínimo do FSD §8:

   ```
   # Materiais da disciplina (direitos autorais) — consulta local, nunca versionar
   /Livro.pdf
   /Aula*.pdf
   /Dashboard.webp
   ```

   Antes do primeiro commit, confira com `git status` que nenhum desses arquivos aparece como pendente.
2. **Informar o e-mail dos commits.**
