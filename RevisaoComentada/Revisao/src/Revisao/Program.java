// Nome / RA:
// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// J�lia Campolim de Oste- 10408802

//Fonte utilizada para pesquisa:
//https://profkishimoto.github.io/edii04g11-2024-1/

package Revisao;

import java.util.Scanner;
import java.io.*;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Program {

    public static boolean carregarDados(String fileName, LinkedList movies) {
        try {
        	//Preparando para ler o arquivo
            InputStream is = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            //Lendo cada linha do arquivo enquanto n�o encontrar uma vazia
            while (true) {
                String title = br.readLine();

                if (title == null) {
                    break;
                }
                
                //Criando um objeto tipo Movie para cada linha lida
                Movie movie = new Movie();
                
                //Alterando os atributos title, year, score para que recebam os novos valores
                movie.setTitle(title);
                movie.setYear(parseInt(br.readLine()));
                movie.setScore(parseFloat(br.readLine()));
                
                //Inserindo o novo filme na LinkedList
                movies.insertMovie(movie);
            }
            //Fechando o arquivo
            is.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //M�todo para exibir todos os valores inseridos na LinkedList
    public static void exibirDados(LinkedList movies) {
        movies.print();
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
    	//Criando um Scanner para receber os dados que o usu�rio digitar
        Scanner scan = new Scanner(System.in);
        
        //Atributos da classe principal
        int opcao;
        boolean loadMovies = false;
        //Criando uma nova lista
        LinkedList movies = new LinkedList();

        do {
        	//Imprimindo o menu de op��es enquanto o usu�rio n�o digitar 3
            System.out.println("------------------------------------");
            System.out.println("1. Carregar dados");
            System.out.println("2. Exibir dados");
            System.out.println("3. Sair");
            System.out.println("------------------------------------\n");

            System.out.print("Op��o: ");
            //Recebendo a op��o digitada pelo usu�rio
            opcao = scan.nextInt();

            System.out.println();
            
            //Validando as op��es
            //Se o usu�rio digitar 1, os dados devem ser carregados na lista
            //E se ocorrer algum erro no processo, uma mensagem � apresentada para o usu�rio
            if(opcao == 1) {
                loadMovies = carregarDados("imdb.txt", movies);
                if(loadMovies) {
                    System.out.println("Dados caregados com sucesso!\n");
                } else {
                    System.out.println("Ocorreu um problema, n�o foi poss�vel ler o arquivo :(\n");
                }
            //Se digitar 2 sem carregar dados para a lista antes, o programa avisa que nenhum dado foi carregado
            //Se j� tiver carregado dados, ele exibe o conte�do da lista (todos os filmes presentes nela)
            } else if(opcao == 2) {
                if(!loadMovies) {
                    System.out.println("Nenhum dado foi carregado... Digite a op��o 1 primeiro\n");
                } else {
                    exibirDados(movies);
                }
            }
          //Se digitar 3, o menu para de aparecer e o programa � encerrado
        } while(opcao != 3);

        System.out.println("Encerrando o programa...");
        //Fechando o Scanner
        scan.close();
    }
}