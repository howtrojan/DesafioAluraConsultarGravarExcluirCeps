import java.util.Scanner;

public class Desafio {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o seu nome");
        String nome = scanner.nextLine();
        System.out.println("Digite o tipo de conta");
        String tipoConta = scanner.nextLine();
        System.out.println("Digite o seu saldo");
        double saldo = scanner.nextDouble();


        String mensagensOpcoes = """
               
               1 - Consultar Saldo
               2 - Receber Valor
               3 - Transferir Valor
               4 - Sair
               
               """;

        System.out.println("Olá "+ nome + " foi identificado que você possui uma conta " + tipoConta + " " +  mensagensOpcoes);
        int opcao = scanner.nextInt();

        while (opcao != 4){
          if (opcao == 1){
              System.out.println("Seu saldo é " + saldo);
              System.out.println(mensagensOpcoes);
              opcao = scanner.nextInt();
          }else if (opcao == 2){
                System.out.println("Qual o valor que você quer receber ? ");
                double novoValor = scanner.nextDouble();
                saldo += novoValor;


              System.out.println("Valor recebido com sucesso");
              System.out.println("Seu saldo é " + saldo);

                System.out.println(mensagensOpcoes);
                opcao = scanner.nextInt();
            }else if (opcao == 3){
              System.out.println("Qual o valor que você quer transferir ? ");
              double valorTrasferido = scanner.nextDouble();
              saldo -= valorTrasferido;

              System.out.println("Valor trasferido com sucesso");
              System.out.println("Seu saldo é " + saldo);

              System.out.println(mensagensOpcoes);
              opcao = scanner.nextInt();
          }else{
              System.out.println("Opção inválida");

              System.out.println(mensagensOpcoes);
              opcao = scanner.nextInt();
          }

        }



    }
}
