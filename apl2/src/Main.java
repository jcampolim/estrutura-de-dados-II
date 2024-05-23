import BST.*;
import AVL.*;

import java.util.List;
import java.io.*;
import java.util.*;

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

    private static void write(String fileName, LinkedList<NodeAVL> statements, List<String> currentPath, BufferedWriter buffWrite)throws IOException{
        String linha = "\n";
        Queue<NodeAVL> paths = new LinkedList<NodeAVL>();
        LinkedList<NodeAVL> removes = new LinkedList<>();
        List<String> auxilarPath = new ArrayList<String>();

        if(!statements.isEmpty()) {
            for (NodeAVL node : statements) {
                // Imprime todas as Keys que pertecem ao currentPath
                if (node instanceof KeyAVL && node.getPath().equals(currentPath)) {
                    linha = node.getIdentifier() + " = " + node.getValue();
                    buffWrite.append(linha);
                    buffWrite.newLine();
                    removes.add(node);
                } else if (node instanceof ScopeAVL && node.getPath().equals(currentPath)){
                    paths.add(node);
                }
            }
            // Remove chaves já usadas
            for (NodeAVL node : removes) {
                statements.remove(node);
            }
            removes.clear();
            // Entra dentro do proximo Scope
            for (NodeAVL node : paths){
                linha = node.getIdentifier() + "(";
                buffWrite.append(linha);
                buffWrite.newLine();
                auxilarPath = node.getPath();
                auxilarPath.add(node.getIdentifier());
                // Remove Scopes já usadas
                statements.remove(node);
                write(fileName, statements, currentPath = auxilarPath, buffWrite);
                linha = ")";
                buffWrite.append(linha);
                buffWrite.newLine();
                removes.add(node);
            }1

        }
    }

    public static int writeFile(String fileName, AVL avl) throws IOException {
        File file = new File(fileName);

        // Adiciona uma verificação de permissão
        if (!file.exists()) {
            file.createNewFile(); // Cria o arquivo se não existir
        }

        // Adiciona um print para depuração
        System.out.println("Escrevendo no arquivo: " + file.getAbsolutePath());

        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file))) {
            LinkedList<NodeAVL> statements = avl.toList();
            List<String> currentPath = new ArrayList<String>();

            write(fileName, statements, currentPath, buffWrite);
        }

        System.out.println("Escrita concluída.");

        return 1;
    }

    public static void searchIdentifier(BST bst, AVL avl, String identifier) {
        List<NodeBST> listNodeBST = new ArrayList<>();
        List<NodeAVL> listNodeAVL = new ArrayList<>();

        int compBST = bst.searchBST(identifier, listNodeBST);
        int compAVL = avl.searchAVL(identifier, listNodeAVL);

        System.out.println("-------------------------- BUSCA BST --------------------------");
        System.out.println(" - Quantidade de comparações: " + compBST);
        if(listNodeBST.isEmpty()) {
            System.out.println("Não foi encontrada nenhuma chave o escopo com esse identificador.");
        } else {
            for(int i = 0; i < listNodeBST.size(); i++) {
                System.out.println(" - " + listNodeBST.get(i));
            }
        }

        System.out.println("\n-------------------------- BUSCA AVL --------------------------");
        System.out.println(" - Quantidade de comparações: " + compAVL);
        if(listNodeAVL.isEmpty()) {
            System.out.println("Não foi encontrada nenhuma chave o escopo com esse identificador.");
        } else {
            for(int i = 0; i < listNodeAVL.size(); i++) {
                System.out.println(" - " + listNodeAVL.get(i));
            }
        }
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

    public static void menu() throws IOException {
        BST bst = new BST();
        AVL avl = new AVL();

        Scanner scanner = new Scanner(System.in);

        int opt = -1;
        while(opt != 9) {
            System.out.println("*********************************************************");
            System.out.println(" 1. Carregar dados de um arquivo ED2\n" +
                    " 2. Buscar uma chave/escopo na árvore\n" +
                    " 3. Inserir uma chave/escopo da árvore\n" +
                    " 4. Alterar uma chave da árvore\n" +
                    " 5. Remover uma chave da árvore\n" +
                    " 6. Salvar dados para um arquivo\n" +
                    " 7. Exibir o conteúdo e as propriedades da árvore BST\n" +
                    " 8. Exibir o conteúdo e as propriedades da árvore AVL\n" +
                    " 9. Encerrar o programa");
            System.out.println("*********************************************************");

            System.out.print("\nOpção: ");
            opt = scanner.nextInt();
            System.out.print("\n");

            if (opt == 1) {
                testParser(readFile(), bst, avl);
            } else if(opt > 1 || opt < 9) {
                if(!bst.isEmpty() && !avl.isEmpty()){
                    if(opt == 2) {
                        System.out.print("Chave/escopo: ");
                        String identifier = scanner.next();
                        System.out.println();

                        searchIdentifier(bst, avl, identifier);
                    } else if(opt == 3) {
                        // inserir nova chave ou escopo
                    } else if(opt == 4) {
                        // alterar uma chave
                    } else if(opt == 5) {
                        // remover uma chave
                    } else if(opt == 6) {
                        try {
                            writeFile("saida.txt", avl);
                            System.out.println("Deu tudo certo");

                        } catch(RuntimeException e) {
                            System.out.println("\n**** ERRO! O conteúdo inserido não está bem formatado: ");
                            System.out.println("> " + e.getMessage());
                        }
                    } else if(opt == 7) {
                        System.out.println("Em ordem: ");
                        bst.inOrder();
                        System.out.println();

                        System.out.println("Pré ordem: ");
                        bst.preOrder();
                        System.out.println();

                        System.out.println("Pós ordem: ");
                        bst.posOrder();
                        System.out.println();
                    } else if(opt == 8) {
                        System.out.println("Em ordem: ");
                        avl.inOrder();
                        System.out.println();

                        System.out.println("Pré ordem: ");
                        avl.preOrder();
                        System.out.println();

                        System.out.println("Pós ordem: ");
                        avl.posOrder();
                        System.out.println();
                    }
                } else {
                    System.out.println("ERRO: O Arquivo ainda não foi carregado.");
                }
            } else if (opt == 9) {
                System.out.println("Encerrando o programa ....");
                break;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        menu();
    }
}