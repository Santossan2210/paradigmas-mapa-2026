# Telemetria de Frota Sustentável — EcoFleet

Projeto acadêmico da disciplina Paradigmas de Linguagens de Programação (MAPA 54/2026).
Desenvolvido por **Antonio Santos**.

- `src/telemetria` — modelo orientado a objetos (Veiculo, VanEletrica, BicicletaEletrica, Main)
- `src/processamento` — processamento funcional de viagens com Java Streams
- `src/verificacao` — conferência das regras de bateria

## Compilar e executar (Java 21)

```
javac -encoding UTF-8 -d out src/telemetria/*.java src/processamento/*.java src/verificacao/*.java
java -Dstdout.encoding=UTF-8 -cp out telemetria.Main
java -Dstdout.encoding=UTF-8 -cp out processamento.ProcessamentoMain
java -Dstdout.encoding=UTF-8 -cp out verificacao.VerificacaoRegras
```

No PowerShell, coloque a opção entre aspas: `java "-Dstdout.encoding=UTF-8" -cp out telemetria.Main`.

## Gerar o relatório (.docx)

1. Copie `relatorio/identificacao.exemplo.txt` para `relatorio/identificacao.local.txt` e preencha os campos. Esse arquivo não é versionado.
2. Na raiz do repositório:

```
pip install -r relatorio/requirements.txt
python relatorio/gerar_relatorio.py
```

São gravados em `relatorio/gerado/` (pasta não versionada):

- `Relatorio_MAPA_Paradigmas_Antonio_Santos.docx`: a entrega, na sequência pedida pela disciplina (Identificação, Tarefa 1, Tarefa 2 com as quatro classes e Tarefa 3).
- `Complemento_Paradigma_Funcional_Antonio_Santos.docx`: os Tópicos 1 a 5 sobre o paradigma funcional.

Para usar o Formulário Padrão (Template) do STUDEO, salve-o como `relatorio/template.local.docx` antes de gerar. Ele não é versionado.
