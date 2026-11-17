package entities;

import entities.enums.ConfigAlgoritmo;

import java.util.ArrayList;

public class Escalonador {
    private ArrayList<Processo> filaProcessos = new ArrayList<>();

    private ConfigAlgoritmo algoritmo;
    private int quantum;

    public Escalonador(ConfigAlgoritmo algoritmo, int quantum) {
        if (algoritmo == ConfigAlgoritmo.ROUND_ROBIN && quantum <= 0){
            throw new IllegalArgumentException("Quantum invalido");
        }
        this.algoritmo = algoritmo;
        this.quantum = quantum;
    }

    //getters
    public ConfigAlgoritmo getAlgoritmo() {
        return algoritmo;
    }

    public int getQuantum(){
        return quantum;
    }

    // principal
    public Processo escalonaProximoProcesso(){
        return switch (getAlgoritmo()){
            case FCFS -> removePrimeiroDaFila();
            case SJF -> removeSJF();
            case PRIORIDADE -> removePrioridade();
            case ROUND_ROBIN -> removePrimeiroDaFila();
        };
    }

    // auxiliar
    public void adicionarProcesso(Processo p){
        filaProcessos.add(p);
    }

    public boolean temProcessosAguardando(){ return !filaProcessos.isEmpty(); };

    public void getProcessos(){
        System.out.println("\n=== PROCESSOS AGUARDANDO ===");

        for(Processo p : filaProcessos){
            System.out.println(p);
        }
    }

    // algoritmos de decisão
    public Processo removePrimeiroDaFila(){
        if(filaProcessos.isEmpty()) {
            return null;
        } else {
            return filaProcessos.remove(0);
        }
    }

    public Processo removeSJF(){
        if(filaProcessos.isEmpty()){
            return null;
        } else {
            int iMenor = 0;

            for(int i = 1; i < filaProcessos.size(); i++){
                if(filaProcessos.get(i).getQuantidadeInstrucoes() < filaProcessos.get(iMenor).getQuantidadeInstrucoes()) {
                    iMenor = i;
                }
            }
            return filaProcessos.remove(iMenor);
        }
    }

    public Processo removePrioridade(){
        if(filaProcessos.isEmpty()){
            return null;
        } else {
            int iMaior = 0;

            for(int i = 1; i < filaProcessos.size(); i++){
                if(filaProcessos.get(i).getPrioridade() > filaProcessos.get(iMaior).getPrioridade()) {
                    iMaior = i;
                }
            }
            return filaProcessos.remove(iMaior);
        }
    }
}
