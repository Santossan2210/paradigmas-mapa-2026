# LOG DE ERROS DO PROJETO

Cada entrada registrada aqui deve seguir esta estrutura:

## [Data] — [Título curto do erro]
### Sintoma
### Causa
### Solução aplicada
### Como evitar no futuro

---

## 08/11/2026 — PowerShell quebra a opção `-Dstdout.encoding=UTF-8`

### Sintoma
No PowerShell, `java -Dstdout.encoding=UTF-8 -cp out telemetria.Main` falha com `Error: Could not find or load main class .encoding=UTF-8`.

### Causa
O PowerShell divide o argumento sem aspas no primeiro ponto. O `java` recebe `-Dstdout` e `.encoding=UTF-8` como dois argumentos separados e trata o segundo como o nome da classe.

### Solução aplicada
No PowerShell, colocar a opção entre aspas: `java "-Dstdout.encoding=UTF-8" -cp out telemetria.Main`. No Git Bash e no Linux do GitHub Actions, o comando sem aspas funciona normalmente. O `README.md` e o `CLAUDE.md` passaram a registrar isso.

### Como evitar no futuro
Em comandos de PowerShell, sempre colocar entre aspas as opções `-D<propriedade>=<valor>`. O shell de referência local continua sendo o Git Bash. O curinga `src/telemetria/*.java` do `javac` funciona no PowerShell (verificado: 5 classes compiladas).

---

## 08/11/2026 — Aviso `this-escape` com `javac -Xlint:all` (informativo, não corrigido)

### Sintoma
Compilando com `-Xlint:all`, o `javac` 21 mostra `warning: [this-escape] possible 'this' escape before subclass is fully initialized` na chamada `setAutonomiaBateria(...)` dentro dos construtores de `VanEletrica` e `BicicletaEletrica`.

### Causa
O construtor chama um método que poderia ser sobrescrito por uma eventual subclasse da van ou da bicicleta antes que ela termine de ser inicializada. O enunciado **exige** essa chamada no construtor, para que a bateria inicial passe pela validação.

### Solução aplicada
Nenhuma alteração no código. O comando oficial de compilação (sem `-Xlint`) não mostra avisos. Tornar o método `final` ou a classe `final` eliminaria o aviso, mas mudaria a declaração especificada no FSD §12.1, então ficou como está.

### Como evitar no futuro
Não usar `-Xlint:all` como critério de aceite deste projeto.

---

## 08/11/2026 — Semente 2026 gerava um lote pequeno sem valor didático

### Sintoma
Com `GeradorLote.SEMENTE = 2026L`, as 10 primeiras viagens tinham 0 viagens críticas e só 1 van. A seção de seleção (FILTER) ficava vazia, e a demonstração da avaliação preguiçosa calculava as 10 viagens, sem mostrar que o stream para cedo.

### Causa
A semente tinha sido escolhida pelo ano, sem conferir o lote que ela gera. Com 70% de bicicletas e bateria inicial de no mínimo 40%, é comum não haver nenhuma crítica em só 10 viagens.

### Solução aplicada
Busquei sementes cujo lote de 10 tivesse de 3 a 4 vans, 2 a 3 críticas e a primeira crítica entre a #03 e a #05. Com aprovação do autor, a semente passou a ser **376**: 4 vans, 3 críticas (#03, #04, #08) e a avaliação preguiçosa parando em 3 de 10. O FSD (P02, RN-F05, §12.2, §24, §30) e o PLANO foram atualizados.

### Como evitar no futuro
Quando um dado simulado sustenta uma demonstração, conferir a saída gerada antes de fixar o parâmetro na especificação. Se o autor quiser zerar o aviso, a opção mais limpa é declarar `public final void setAutonomiaBateria(double bateria)` em `Veiculo`, o que também impede subclasses de burlar a validação. Isso exige aprovação, porque altera o FSD.

---

## 08/11/2026 — Workflow inválido: `: ` dentro de valor YAML sem aspas

### Sintoma
No primeiro push (`edd5592`), o GitHub Actions marcou a execução como `failure` sem rodar nenhum job. O nome exibido foi o caminho do arquivo (`.github/workflows/executar.yml`) em vez de "Executar e verificar".

### Causa
Os passos de conferência usavam `run: grep -q "Van elétrica: 75.0%" ...` e `run: grep -q "Resultados idênticos: SIM" ...`. Em YAML, um valor sem aspas (escalar simples) não pode conter `: `, que é interpretado como início de um novo par chave/valor. O arquivo inteiro ficou inválido. A simulação local rodou os comandos no shell, mas não leu o YAML, então não pegou o problema.

### Solução aplicada
Os dois `run` passaram a usar bloco literal (`run: |`), com o comando na linha seguinte. O arquivo foi validado com PyYAML (instalado só na pasta temporária) antes do novo push.

### Como evitar no futuro
Sempre validar o workflow com um parser YAML antes do push, além de rodar os comandos. Em `run:` com `: `, `#` ou aspas no texto, usar `run: |`.
