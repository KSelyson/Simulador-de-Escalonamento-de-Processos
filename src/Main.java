//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    GeradorDeProcessos gerador = new GeradorDeProcessos();

    Processo p1 = gerador.gerarProcesso();
    Processo p2 = gerador.gerarProcesso();
    Processo p3 = gerador.gerarProcesso();

    System.out.println(p1);
    System.out.println(p2);
    System.out.println(p3);

}
