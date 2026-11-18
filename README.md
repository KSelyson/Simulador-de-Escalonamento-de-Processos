# Simulador de Escalonamento de Processos

Simulador em **Java** que demonstra o funcionamento de quatro algoritmos clássicos de escalonamento de processos em uma CPU: **FCFS**, **SJF**, **Round Robin** e **Prioridade**. Projeto desenvolvido para a disciplina de Sistemas Operacionais.

## Sumário

- [Visão geral](#visão-geral)
- [Algoritmos implementados](#algoritmos-implementados)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Entidades](#entidades)
- [Como funciona](#como-funciona)
- [Como executar](#como-executar)
- [Configuração](#configuração)

## Visão geral

O simulador cria processos com características aleatórias, entrega-os a um escalonador configurado com um dos algoritmos e usa uma CPU que executa **uma instrução por iteração** até que o processo termine. A execução é acompanhada pelo console, mostrando qual processo está rodando, quais estão aguardando e quais já foram finalizados.

## Algoritmos implementados

| Algoritmo | Critério de escolha | Preemptivo? |
|---|---|---|
| **FCFS** (First Come, First Served) | Ordem de chegada | Não |
| **SJF** (Shortest Job First) | Menor quantidade de instruções restantes | Não |
| **Round Robin** | Fila circular com *quantum* configurável; processos não finalizados voltam ao final da fila | Sim (por quantum) |
| **Prioridade** | Maior prioridade primeiro | Não |

## Estrutura do projeto

```
Simulador-de-Escalonamento-de-Processos/
└── src/
    ├── Main.java
    └── entities/
        ├── CPU.java
        ├── Escalonador.java
        ├── GeradorDeProcessos.java
        ├── Processo.java
        └── enums/
            └── ConfigAlgoritmo.java
```

## Entidades

### `Processo`
Representa um processo a ser executado.

- **ID**: identificador único.
- **Quantidade de instruções**: instruções restantes; cada execução na CPU decrementa uma. O processo termina quando chega a zero.
- **Prioridade**: valor entre 1 e 5.

### `GeradorDeProcessos`
Gera novos processos com:

- ID incremental;
- quantidade de instruções aleatória entre **10 e 50**;
- prioridade aleatória entre **1 e 5**.

### `Escalonador`
Mantém os processos aguardando e seleciona o próximo conforme o algoritmo configurado (`ConfigAlgoritmo`). No Round Robin, utiliza também o *quantum* informado.

### `CPU`
Executa os processos continuamente:

1. Solicita o próximo processo ao escalonador.
2. Executa uma instrução por iteração. Nos algoritmos não preemptivos, roda até o processo terminar; no Round Robin, roda até terminar ou atingir o quantum.
3. Se o processo terminou, vai para a lista de finalizados; caso contrário, é devolvido ao escalonador.
4. Aguarda (`Thread.sleep`) entre as rodadas para facilitar o acompanhamento da execução.
5. Ao fim, exibe a lista de processos finalizados.

### `ConfigAlgoritmo` (enum)
Define o algoritmo em uso: `FCFS`, `SJF`, `ROUND_ROBIN` e `PRIORIDADE`.

## Como funciona

O fluxo da `Main` é:

```java
ConfigAlgoritmo algoritmoEscolhido = SJF;
int quantum = 15; // usado apenas no Round Robin

GeradorDeProcessos gerador = new GeradorDeProcessos();
Escalonador escalonador = new Escalonador(algoritmoEscolhido, quantum);

for (int i = 1; i <= 5; i++) {
    escalonador.adicionarProcesso(gerador.gerarProcesso());
}

CPU cpu = new CPU(escalonador);
cpu.executarSimulacao();
```

A CPU recebe o escalonador e, a cada rodada, pede dele o próximo processo a executar.

## Como executar

### Requisitos

- **JDK 21 ou superior.** A `Main` usa o método `main` simplificado (sem declaração de classe), que no JDK 21 e 22 exige `--enable-preview` e passou a ser padrão a partir do JDK 25.

### Pela IDE (IntelliJ IDEA)

1. Abra o projeto.
2. Verifique se o *Language level* do projeto suporta o `main` simplificado.
3. Execute a `Main`.

### Pelo terminal

A partir da raiz do repositório:

```bash
# Compilar (JDK 21/22: acrescente --enable-preview --release <versão>)
javac -d out src/entities/enums/*.java src/entities/*.java src/Main.java

# Executar
java -cp out Main
```

## Configuração

A configuração é feita diretamente na `Main`:

| Parâmetro | Descrição |
|---|---|
| `algoritmoEscolhido` | `FCFS`, `SJF`, `ROUND_ROBIN` ou `PRIORIDADE` |
| `quantum` | Número de instruções por turno. Só é usado no Round Robin (valores pedidos nos testes: 2, 5 e 10) |
| Laço `for` | Define quantos processos são criados no início da simulação |
| `Thread.sleep(...)` na `CPU` | Pausa entre as rodadas, em milissegundos |


## Autor

**KSelyson**
