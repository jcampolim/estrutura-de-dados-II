import java.util.Scanner;
import java.io.*;

public class Program {

    public static boolean carregarDados() {
        return true;
    }

    public static void exibirDados() {

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int opcao;
        boolean dados = false;

        do {
            System.out.println("1. Carregar dados");
            System.out.println("2. Exibir dados");
            System.out.println("3. Sair");

            opcao = scan.nextInt();

            if(opcao == 1) {
                dados = carregarDados();
            } if(opcao == 2) {
                if(!dados) {
                    System.out.println("Por favor, carregue os dados primeiro");
                } else {
                    exibirDados();
                }
            }

        } while(opcao != 3);

        scan.close();
    }
}