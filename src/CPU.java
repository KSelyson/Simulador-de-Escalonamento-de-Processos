import enums.ConfigAlgoritmo;

import java.util.ArrayList;

public class CPU {

    private Escalonador escalonador;
    private Processo processoAtual;


    private ArrayList<Processo> processosFinalizados = new ArrayList<>();

    public CPU(Escalonador escalonador){
        this.escalonador = escalonador;
    }

    //primario
    public void executarSimulacao(){
        while(escalonador.temProcessosAguardando()){ //garante que o programa trabalhe e mostre os processsos atuais
            executarRodada();
            escalonador.getProcessos();
        }

        System.out.println("\n=== PROCESSOS FINALIZADO ==="); // quando terminar, mostrar a lista final
        for(Processo p : processosFinalizados){
            System.out.println(p);
        }
    }

    //secundario
    public void executarRodada(){
        processoAtual = escalonador.escalonaProximoProcesso();

        if(processoAtual == null){
            return;
        }

        System.out.println("\nExecutando: "+ processoAtual);

        int instrucoesExecutadas = 0;

        // enquanto o processo nn estiver finalizado, o while roda, mas se for round robin, roda até atingir o quantum
        while (!processoAtual.isFinalizado() &&
                (escalonador.getAlgoritmo() != ConfigAlgoritmo.ROUND_ROBIN ||
                        instrucoesExecutadas < escalonador.getQuantum())){

                processoAtual.executarInstrucao();
                instrucoesExecutadas++;

        }

        if(processoAtual.isFinalizado()){
            processosFinalizados.add(processoAtual); //se tiver finalizado já adiciona na lista com estado finalizado
            System.out.println("Processo finalizado com PID "+ processoAtual.getId());
        } else {
            escalonador.adicionarProcesso(processoAtual); //se não, joga de volta la no escalonador pra fzr a seleção
        }
    }
}
