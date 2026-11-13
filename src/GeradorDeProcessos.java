

public class GeradorDeProcessos {
    private static int contadorId = 1;

    public Processo gerarProcesso(){

        int qtndInstrucoesRandom = (int) (Math.random() * 41) + 10;
        int prioridade = (int) (Math.random() * 5) + 1;
        return new Processo(contadorId++, qtndInstrucoesRandom, prioridade);
    }
}
