import java.util.Scanner;

public class ConsultarCeps {
    public static void main(String[] args) {
        String cep;
        Scanner scanner = new Scanner(System.in);
        int opcao = 1;

        while (opcao != 2) {
            System.out.println("Escolha a Opção");
            System.out.println("1 - Procurar CEP");
            System.out.println("2 - Consultar CEPS GRAVADOS");
            System.out.println("3 - Exibir Ceps e excluir");
            System.out.println("4 - Sair");
            opcao = scanner.nextInt();
            if (opcao == 1){
                System.out.println("1 - Digite o Cep para procurar o endereço");
                cep = scanner.next();
                APICeps.consultarCEP(cep);
            } else if (opcao == 4) {
                return;
            } else if (opcao == 2){
                APICeps.exibirEnderecosGravados();
            } else if (opcao == 3){
                APICeps.exibirEnderecosEDeletar();
            }
            else {
                System.out.println("Opcão Inválida");
            }
        }
    }
}
