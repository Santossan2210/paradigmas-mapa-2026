# Telemetria de Frota Sustentável — EcoFleet

Projeto acadêmico da disciplina Paradigmas de Linguagens de Programação (MAPA 54/2026).
Desenvolvido por **Antonio Santos** — SAN SOLUÇÕES.

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
