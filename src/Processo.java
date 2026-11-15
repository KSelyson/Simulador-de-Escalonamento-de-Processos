public class Processo {

    // atributos
    private final int id;
    private int quantidadeInstrucoes;
    private final int prioridade;

    // construtor
    public Processo(int id, int quantidadeInstrucoes, int prioridade) {
        this.id = id;
        this.quantidadeInstrucoes = quantidadeInstrucoes;
        this.prioridade = prioridade;
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public int getQuantidadeInstrucoes() {
        return quantidadeInstrucoes;
    }

    public void executarInstrucao() {
        if(isFinalizado()){
            throw new IllegalStateException("Processo já finalizado.");
        }

        this.quantidadeInstrucoes--;
    }

    public int getPrioridade() {
        return prioridade;
    }

    //saber o estado do processo
    public boolean isFinalizado(){
        return this.quantidadeInstrucoes == 0;
    }

    @Override
    public String toString() {
        return "Processo id: "+ this.id +" | Qtnd de Instruções: "+ this.quantidadeInstrucoes
                +" | Prioridade (1 a 5): "+ this.prioridade;
    }
}
