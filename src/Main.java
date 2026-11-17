import entities.CPU;
import entities.Escalonador;
import entities.GeradorDeProcessos;
import entities.enums.ConfigAlgoritmo;

import static entities.enums.ConfigAlgoritmo.FCFS;
import static entities.enums.ConfigAlgoritmo.PRIORIDADE;
import static entities.enums.ConfigAlgoritmo.ROUND_ROBIN;
import static entities.enums.ConfigAlgoritmo.SJF;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    ConfigAlgoritmo algoritmoEscolhido = SJF;
    int quantum = 15; // só será utilizado e validado se o algoritmo escolhido for round robin

    GeradorDeProcessos gerador = new GeradorDeProcessos();

    Escalonador escalonador = new Escalonador(algoritmoEscolhido, quantum);

    for(int i = 1; i <= 5; i++){
        escalonador.adicionarProcesso(gerador.gerarProcesso()); //gera o processo e mandar pro escalonador
    }

    CPU cpu = new CPU(escalonador); // entities.CPU gerado pae
    // criei um gerador que o escalonador utiliza e mandei o escalonador pra cpu entrar em contato

    cpu.executarSimulacao(); //inicia o programa
}
