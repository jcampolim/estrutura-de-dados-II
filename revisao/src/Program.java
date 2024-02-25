import java.util.Scanner;
import java.io.*;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Program {

    public static boolean carregarDados(String fileName, LinkedList movies) {
        try {
            InputStream is = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            while (true) {
                String title = br.readLine();

                if (title == null) {
                    break;
                }

                Movie movie = new Movie();

                movie.setTitle(title);
                movie.setYear(parseInt(br.readLine()));
                movie.setScore(parseFloat(br.readLine()));

                movies.insertMovie(movie);
            }

            is.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void exibirDados(LinkedList movies) {
        movies.print();
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        int opcao;
        boolean loadMovies = false;
        LinkedList movies = new LinkedList();

        do {
            System.out.println("------------------------------------");
            System.out.println("1. Carregar dados");
            System.out.println("2. Exibir dados");
            System.out.println("3. Sair");
            System.out.println("------------------------------------\n");

            System.out.print("Opção: ");
            opcao = scan.nextInt();

            System.out.println();

            if(opcao == 1) {
                loadMovies = carregarDados("imdb.txt", movies);
                if(loadMovies) {
                    System.out.println("Dados caregados com sucesso!\n");
                } else {
                    System.out.println("Ocorreu um problema, não foi possível ler o arquivo :(\n");
                }
            } else if(opcao == 2) {
                if(!loadMovies) {
                    System.out.println("Nenhum dado foi carregado... Digite a opção 1 primeiro\n");
                } else {
                    exibirDados(movies);
                }
            }
        } while(opcao != 3);

        System.out.println("Encerrando o programa...");

        scan.close();
    }
}