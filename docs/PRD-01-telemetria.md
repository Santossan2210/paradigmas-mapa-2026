# DOCUMENTO DE REQUISITOS DO PRODUTO (PRD) 01 — Sistema de Telemetria EcoFleet

**Projeto:** Telemetria de Frota Sustentável — EcoFleet Solutions S.A.
**Autor:** Antonio Santos
**Elaborado por:** Antonio Santos
**Data:** 08/11/2026
**Versão:** 1.3
**Documento de origem:** PROJETO (= "MAPA - Paradigmas de Linguagens de Programação - 54_2026_Instrução")
**Índice geral:** [PRD.md](PRD.md) · **PRD complementar:** [PRD-02-relatorio-funcional.md](PRD-02-relatorio-funcional.md)

> Este PRD cobre **somente** o que o documento PROJETO exige. Nada aqui foi criado pelo autor além da escolha de valores de exemplo, que o próprio enunciado pede que sejam "coerentes".

---

## 1. Visão Geral do Produto

A EcoFleet Solutions S.A. opera uma frota urbana formada por **vans elétricas de carga** e **bicicletas elétricas de entrega**. O autor vai reorganizar o sistema de telemetria no paradigma orientado a objetos. A nova solução representa os veículos por tipo, reúne as características comuns em um modelo geral, protege o nível de bateria e calcula o consumo de forma diferente para cada categoria.

O resultado é entregue em um arquivo identificado com as **Tarefas 1, 2 e 3**.

| Origem | Conteúdo coberto neste PRD |
|---|---|
| PROJETO — Tarefa 1 | Modelagem conceitual textual e justificativas |
| PROJETO — Tarefa 2 | Implementação das quatro partes: `Veiculo`, `VanEletrica`, `BicicletaEletrica` e `Main` |
| PROJETO — Tarefa 3 | Justificativa da lógica, de 10 a 20 linhas |
| PROJETO — Saída do projeto | Identificação, ordem do arquivo e legibilidade do código |

## 2. Problema que o Sistema Resolve

- O protótipo anterior aceitava níveis de bateria **abaixo de zero ou acima do máximo**, o que comprometia o acompanhamento da frota.
- Não havia um lugar central para as características comuns dos veículos.
- O cálculo de consumo não diferenciava as categorias de veículo.

## 3. Objetivos do Sistema

**Objetivo principal**
Modelar e implementar uma solução orientada a objetos que garanta níveis de bateria válidos e calcule o consumo correto de cada categoria de veículo.

**Objetivos específicos**
1. Representar os veículos por meio de um modelo geral e de dois tipos concretos.
2. Centralizar as características comuns no modelo geral.
3. Restringir o acesso ao nível de bateria.
4. Permitir um comportamento de consumo diferente para cada tipo de veículo.
5. Demonstrar na prática os conceitos de abstração, encapsulamento, construtores, herança e polimorfismo.

## 4. Personas e Perfis de Usuário

| Perfil | Descrição | Principais ações | Permissões básicas |
|---|---|---|---|
| Avaliador (professor) | Corrige a entrega no ambiente virtual | Ler o arquivo, conferir nomes, regras e valores | Somente leitura |
| Equipe de operação da EcoFleet (fictícia) | Acompanha a frota | Consultar a bateria e registrar viagens | Consulta e registro de viagem |
| Autor (Antonio Santos) | Desenvolve e entrega | Modelar, implementar, testar e redigir | Acesso total |

## 5. Escopo da Primeira Versão

Todo o escopo deste PRD é **obrigatório**.

### 5.1 Modelagem conceitual (Tarefa 1)
- Representação textual da estrutura de classes.
- Modelo geral abstrato `Veiculo`, com os tipos concretos `VanEletrica` e `BicicletaEletrica`.
- Atributos e visibilidades exatamente como no enunciado (ver seção 8).
- Justificativa de `Veiculo` ser abstrato: é um modelo geral que concentra o que é comum e não deve ser instanciado diretamente.
- Explicação da escolha de `protected` e `private`: o que é compartilhado por herança e o que permanece encapsulado.

### 5.2 Cadastro de veículos (Tarefa 2)
- Todas as partes pertencem ao pacote `telemetria`.
- `Veiculo` tem um construtor sem parâmetros que inicia os atributos com os valores da tabela abaixo.

**Valores iniciais do construtor sem parâmetros de `Veiculo`**

| Atributo | Valor inicial |
|---|---|
| `idVeiculo` | `0` |
| `modelo` | `""` (texto vazio) |
| `capacidadeCarga` | `0.0` |
| `autonomiaBateria` | `0.0` |

- Van e bicicleta têm construtores que recebem os valores necessários, iniciam os atributos herdados e definem a bateria inicial pela regra de validação.

### 5.3 Controle de bateria (Tarefa 2)
- Consulta pública do nível de bateria.
- Alteração pública do nível de bateria, sempre com validação (RN01).

### 5.4 Registro de viagem (Tarefa 2)
- Operação abstrata de registro de viagem no modelo geral, com assinatura `public abstract void registrarViagem(double distancia)`.
- Cada tipo de veículo implementa a operação com sua própria taxa de consumo, marcada como sobrescrita (`@Override`).
- O novo nível de bateria é calculado a partir da consulta e gravado por meio da alteração validada.
- A operação exibe a distância percorrida e a bateria resultante.

### 5.5 Demonstração (Tarefa 2 — `Main`)
- Cria uma van e uma bicicleta com bateria em 100.0 e demais dados coerentes.
- Simula uma viagem de 50.0 km para cada veículo.
- Exibe o nível final de bateria dos dois.

### 5.6 Justificativa (Tarefa 3)
Texto de 10 a 20 linhas explicando:
- como a validação impede valores fora de 0.0 a 100.0;
- como a organização dos atributos permite reutilizar o que é comum e manter o que é específico;
- como o registro de viagem usa a distância para calcular a bateria;
- qual é o papel do construtor nos valores iniciais.

### 5.7 Teste (Orientações gerais)
- O código deve ser executado e testado em ambiente compatível com Java antes do envio. **Esse ambiente pode ser local**; o PROJETO não exige execução remota. A evidência remota é exigência do PRD-02, para o exemplo funcional.
- Os cálculos e os níveis finais devem ser conferidos contra as regras.

## 6. Funcionalidades Fora de Escopo
- Processamento em lote, transformação, seleção e agregação de dados, processamento paralelo e alertas de bateria crítica. Esses itens são tratados no [PRD-02](PRD-02-relatorio-funcional.md).
- Recarga de bateria, rotas e previsão de autonomia.
- Interface gráfica, armazenamento permanente e dados reais de sensores.
- Classes, atributos ou métodos além dos pedidos.

## 7. Regras de Negócio

| Código | Regra | Origem |
|---|---|---|
| RN01 | Bateria abaixo de 0.0 ou acima de 100.0 é registrada como **0.0**. Valores de 0.0 a 100.0 são registrados como informados. Exemplo: 120 vira 0.0, e não 100. | PROJETO — Classe Veiculo |
| RN02 | A van consome **0.5** ponto percentual de bateria por km. | PROJETO — Classe VanEletrica |
| RN03 | A bicicleta consome **0.2** ponto percentual de bateria por km. | PROJETO — Classe BicicletaEletrica |
| RN04 | Bateria final = bateria atual − (distância × taxa), calculada a partir da consulta e gravada pela alteração validada (RN01). Se o resultado for negativo, a bateria vira 0.0. | PROJETO — Classes Van e Bicicleta |
| RN06 | O modelo geral `Veiculo` não pode ser instanciado diretamente. | PROJETO — Tarefa 1 |

A numeração RN05 não é usada neste documento. A regra que tinha esse número virou a premissa P01 no PRD-02. A numeração das demais foi mantida para facilitar a rastreabilidade.

## 8. Informações que o Sistema Precisa Controlar

| Informação | Para que serve | Observações |
|---|---|---|
| `idVeiculo` (int, protected) | Identificar o veículo | Compartilhado por herança |
| `modelo` (String, protected) | Descrever o veículo | Compartilhado por herança |
| `capacidadeCarga` (double, protected) | Indicar a carga suportada | Compartilhado por herança |
| `autonomiaBateria` (double, private) | Nível de bateria, de 0 a 100 | Encapsulado; acessado só pela consulta e pela alteração validada |
| `placaIdentificacao` (String, private) | Identificar a van | Exclusivo de `VanEletrica` |
| `limiteVelocidadeAssistida` (double, private) | Caracterizar a bicicleta | Exclusivo de `BicicletaEletrica` |

Os dados existem apenas durante a execução.

## 9. Fluxos Principais de Uso

### Fluxo 1 — Criação de veículo
1. Um veículo é criado com seus dados.
2. Os atributos herdados recebem os valores informados.
3. A bateria inicial é definida pela alteração validada (RN01).

### Fluxo 2 — Proteção da bateria
1. Um valor de bateria é informado.
2. O sistema verifica se está entre 0.0 e 100.0.
3. Se estiver, o valor é registrado. Se não estiver, o sistema registra 0.0.

### Fluxo 3 — Registro de viagem
1. Uma distância é informada ao veículo.
2. O veículo consulta a bateria atual.
3. Calcula o consumo: **consumo = distância × taxa** (0.5 para a van, 0.2 para a bicicleta).
4. Calcula o novo nível: **bateria atual − consumo**, e grava pela alteração validada.
5. Exibe a distância e a bateria resultante.

### Fluxo 4 — Demonstração
1. Cria uma van e uma bicicleta com bateria em 100.0.
2. Registra 50.0 km para cada uma.
3. Exibe os níveis finais: **van 75.0** e **bicicleta 90.0**.

## 10. Histórias de Usuário
1. Como **equipe de operação**, eu quero que níveis de bateria inválidos não sejam aceitos para confiar nos dados da frota.
2. Como **equipe de operação**, eu quero registrar uma viagem informando só a distância para saber a bateria restante.
3. Como **equipe de operação**, eu quero que van e bicicleta tenham consumos próprios para que o cálculo reflita cada veículo.
4. Como **avaliador**, eu quero ver a modelagem, o código e a justificativa identificados como Tarefas 1, 2 e 3 para conferir a entrega com rapidez.

## 11. Critérios de Aceitação

Todos os critérios deste PRD são **obrigatórios**, porque vêm do documento PROJETO.

### Tarefa 1
- [ ] A modelagem textual apresenta `Veiculo` (abstrata), `VanEletrica` e `BicicletaEletrica`.
- [ ] Todos os atributos aparecem com tipo e visibilidade corretos.
- [ ] Há uma justificativa para `Veiculo` ser abstrata.
- [ ] Há uma explicação para o uso de `protected` e de `private`.

### Tarefa 2
- [ ] As quatro partes estão no pacote `telemetria` e identificadas no arquivo.
- [ ] `Veiculo` é declarada como abstrata, tem construtor sem parâmetros com os valores da tabela da §5.2 e tem `getAutonomiaBateria()` e `setAutonomiaBateria(double bateria)` públicos.
- [ ] Os valores -5 e 120 são registrados como 0.0; os valores 0.0, 50.0 e 100.0 são registrados sem alteração.
- [ ] `public abstract void registrarViagem(double distancia);` está declarado em `Veiculo`.
- [ ] Van e bicicleta usam `extends`, têm seus atributos privados e construtores que usam `setAutonomiaBateria()` para a bateria inicial.
- [ ] `registrarViagem` é sobrescrito com `@Override` e com a assinatura exata.
- [ ] O cálculo usa `getAutonomiaBateria()` e grava com `setAutonomiaBateria()`.
- [ ] Cada viagem exibe a distância e a bateria resultante.
- [ ] `Main` tem `public static void main(String[] args)`, cria os dois veículos com 100.0, simula 50.0 km e exibe **75.0** (van) e **90.0** (bicicleta).
- [ ] O código está organizado, legível e bem indentado.
- [ ] O código não tem erros de compilação.
- [ ] O código executa sem exceções em tempo de execução.
- [ ] O código foi executado e testado em ambiente Java (local ou remoto).

### Tarefa 3
- [ ] O texto tem entre 10 e 20 linhas.
- [ ] Explica a validação, a organização dos atributos, o registro de viagem e o papel do construtor.

### Arquivo
- [ ] A identificação traz o **nome completo** e o **RA** do autor (definidos em `relatorio/identificacao.local.txt`, não versionado).
- [ ] A ordem é: identificação, Tarefa 1, Tarefa 2, Tarefa 3.
- [ ] O arquivo abre corretamente.
- [ ] As referências estão citadas, sem cópia literal.

## 12. Consultas, Relatórios e Indicadores

| Indicador | Onde aparece |
|---|---|
| Distância e bateria resultante de cada viagem | Saída do registro de viagem |
| Nível final de bateria de cada veículo | Saída da demonstração (`Main`) |

## 13. Permissões e Segurança Funcional

| Perfil | Pode fazer | Não pode fazer | Observações |
|---|---|---|---|
| Avaliador | Ler o arquivo | Alterar a entrega | — |
| Equipe de operação | Consultar a bateria e registrar viagens | Gravar bateria sem passar pela RN01 | Perfil fictício do cenário |
| Autor | Alterar o código e o texto | Mudar nomes de classes, atributos ou métodos definidos no enunciado | Responsável pela revisão final |

**Segurança funcional:** `autonomiaBateria` é privado e só muda pela alteração validada. Os tipos de veículo acessam diretamente apenas os atributos protegidos.

## 14. Limitações da Primeira Versão
- A saída é apenas em modo texto, no console.
- A RN01 zera valores acima de 100, o que perde informação. Ela é mantida porque o enunciado exige.
- O consumo é linear e não considera carga, relevo ou velocidade.

## 15. Pontos Pendentes Antes do FSD
1. ~~Ambiente de teste~~ **Resolvido:** para o PRD-01, o ambiente compatível com Java pode ser local. A evidência de execução remota gratuita é exigida apenas para o exemplo funcional do PRD-02. Na prática, o mesmo teste remoto do GitHub Actions também executará este código.
2. **Formato da entrega:** saber se as Tarefas 1 a 3 vão em um arquivo próprio ou no mesmo arquivo do relatório funcional. Ver o [índice](PRD.md), seção "Decisões de entrega pendentes".

## 16. Resumo Final do PRD
Este PRD cobre exclusivamente o documento PROJETO:
- um modelo abstrato `Veiculo`, com `VanEletrica` e `BicicletaEletrica`;
- bateria encapsulada e validada (valores inválidos viram 0.0);
- consumo polimórfico de 0.5 por km para a van e 0.2 por km para a bicicleta;
- demonstração com 50 km resultando em **75.0** e **90.0**.

A entrega é um arquivo com identificação e as Tarefas 1, 2 e 3.
