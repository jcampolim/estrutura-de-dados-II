import BST.BST;
import AVL.AVL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> readFile() throws IOException {
        File file = new File("test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> fileList = new ArrayList<>();

        String line;
        while((line = br.readLine()) != null) {
            fileList.add(line);
        }

        return fileList;
    }

    public static int writeFile() throws IOException {
        File file = new File("saida.txt");

        // Adiciona uma verificação de permissão
        if (!file.exists()) {
            file.createNewFile(); // Cria o arquivo se não existir
        }

        // Adiciona um print para depuração
        System.out.println("Escrevendo no arquivo: " + file.getAbsolutePath());

        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file))) {
            String linha = "OK";
            buffWrite.append(linha);
            buffWrite.newLine();
        }

        System.out.println("Escrita concluída.");

        return 1;
    }


    public static void testParser(List<String> contents, BST bst, AVL avl) {
        Parser parser = new Parser();
        try {
            parser.run(contents, bst, avl);
            System.out.println("A gramática está correta\n");
        } catch(RuntimeException e) {
            System.out.println("\n**** ERRO! O conteúdo inserido não está bem formatado: ");
            System.out.println("> " + e.getMessage());
        }
    }

    public static int menu(Scanner scanner){
        int opt = -1;
        while(opt < 0 || opt > 9) {
            System.out.println("1. Carregar dados de um arquivo ED2\n" +
                    "2. Buscar uma chave/escopo na árvore\n" +
                    "3. Inserir uma chave/escopo da árvore\n" +
                    "4. Alterar uma chave da árvore\n" +
                    "5. Remover uma chave da árvore\n" +
                    "6. Salvar dados para um arquivo\n" +
                    "7. Exibir o conteúdo e as propriedades da árvore BST\n" +
                    "8. Exibir o conteúdo e as propriedades da árvore AVL\n" +
                    "9. Encerrar o programa");
            opt = scanner.nextInt();
            System.out.print("\n");
        }
        return opt;
    }

    public static void main(String[] args) throws IOException {
        BST bst = new BST();
        AVL avl = new AVL();
        Scanner scanner = new Scanner(System.in);
        int opt = -1;

        while(true){

            if (opt == 1) {
                testParser(readFile(), bst, avl);

            } else if (opt == 2) {
                if(!bst.isEmpty() && !avl.isEmpty()){

                }else{
                    System.out.println("ERRO: O Arquivo ainda não foi carregado.");
                }


            } else if (opt == 3) {
                if(!bst.isEmpty() && !avl.isEmpty()){

                }else{
                    System.out.println("ERRO: O Arquivo ainda não foi carregado.");
                }
            } else if (opt == 4) {
                if(!bst.isEmpty() && !avl.isEmpty()){

                }else{
                    System.out.println("ERRO: O Arquivo ainda não foi carregado.");
                }
            } else if (opt == 5) {
                if(!bst.isEmpty() && !avl.isEmpty()){

                }else{
                    System.out.println("ERRO: O Arquivo ainda não foi carregado.");
                }
            } else if (opt == 6) {
                if(!bst.isEmpty() && !avl.isEmpty()){
                    try {
                        writeFile();
                        System.out.println("Deu tudo certo");

                    } catch(RuntimeException e) {
                        System.out.println("\n**** ERRO! O conteúdo inserido não está bem formatado: ");
                        System.out.println("> " + e.getMessage());
                    }

                }else{
                    System.out.println("ERRO: O Arquivo ainda não foi carregado.");
                }
            } else if (opt == 7) {
                if(!bst.isEmpty() && !avl.isEmpty()){

                }else{
                    System.out.println("ERRO: O Arquivo ainda não foi carregado.");
                }
            } else if (opt == 8) {
                if(!bst.isEmpty() && !avl.isEmpty()){


                }else{
                    System.out.println("ERRO: O Arquivo ainda não foi carregado.");
                }
            }else if (opt == 9) {
                System.out.println("Encerrando o programa ....");
                break;
            }

            opt = menu(scanner);
        }

        scanner.close();
    }
}