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
Não usar `-Xlint:all` como critério de aceite deste projeto. Se o autor quiser zerar o aviso, a opção mais limpa é declarar `public final void setAutonomiaBateria(double bateria)` em `Veiculo`, o que também impede subclasses de burlar a validação. Isso exige aprovação, porque altera o FSD.
