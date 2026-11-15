import enums.ConfigAlgoritmo;

import static enums.ConfigAlgoritmo.FCFS;
import static enums.ConfigAlgoritmo.PRIORIDADE;
import static enums.ConfigAlgoritmo.ROUND_ROBIN;
import static enums.ConfigAlgoritmo.SJF;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    ConfigAlgoritmo algoritmoEscolhido = PRIORIDADE;
    int quantum = 3; // só será utilizado e validado se o algoritmo escolhido for round robin

    GeradorDeProcessos gerador = new GeradorDeProcessos();

    Escalonador escalonador = new Escalonador(algoritmoEscolhido, quantum);

    for(int i = 1; i <= 10; i++){
        escalonador.adicionarProcesso(gerador.gerarProcesso()); //gera o processo e mandar pro escalonador
    }

    CPU cpu = new CPU(escalonador); // CPU gerado pae
    // criei um gerador que o escalonador utiliza e mandei o escalonador pra cpu entrar em contato

    cpu.executarSimulacao(); //inicia o programa
}
