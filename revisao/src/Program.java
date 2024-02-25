import java.util.Scanner;
import java.io.*;

public class Program {

    public static String carregarDados() throws IOException {
        String filename = "imdb.txt";
        InputStream is = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        StringBuilder sb = new StringBuilder();
        while(true) {
            String line = br.readLine();
            if(line == null) {
                break;
            }

            sb.append(line).append("\n");
        }

        is.close();

        return sb.toString();
    }

    public static void exibirDados() {

    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        int opcao;
        String dados = null;

        do {
            System.out.println("1. Carregar dados");
            System.out.println("2. Exibir dados");
            System.out.println("3. Sair");

            opcao = scan.nextInt();

            if(opcao == 1) {
                dados = carregarDados();
            } if(opcao == 2) {
                if(dados == null) {
                    System.out.println("Por favor, carregue os dados primeiro");
                } else {
                    exibirDados();
                }
            }

        } while(opcao != 3);

        scan.close();
    }
}