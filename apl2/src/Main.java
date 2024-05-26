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
            }
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
            System.out.println("Não foi encontrada nenhuma chave ou escopo com esse identificador.");
        } else {
            for(int i = 0; i < listNodeBST.size(); i++) {
                System.out.println(" - " + listNodeBST.get(i));
            }
        }

        System.out.println("\n-------------------------- BUSCA AVL --------------------------");
        System.out.println(" - Quantidade de comparações: " + compAVL);
        if(listNodeAVL.isEmpty()) {
            System.out.println("Não foi encontrada nenhuma chave ou escopo com esse identificador.");
        } else {
            for(int i = 0; i < listNodeAVL.size(); i++) {
                System.out.println(" - " + listNodeAVL.get(i));
            }
        }
    }

    public static boolean verifyScope(String scope, AVL avl, List<NodeAVL> listNodeAVL) {
        avl.searchAVL(scope, listNodeAVL);

        boolean isScopeValid = false;

        int i = 0;
        while(i < listNodeAVL.size()) {
            if(listNodeAVL.get(i).getValue() == null) {
                isScopeValid = true;
                i++;
            } else {
                listNodeAVL.remove(i);
            }
        }


        if(!isScopeValid || listNodeAVL == null) {
            return false;
        }
        return true;
    }
    public static boolean verifyKey(String key, BST bst, AVL avl, List<NodeBST> keyBST, List<NodeAVL> keyAVL) {
        bst.searchBST(key, keyBST);
        avl.searchAVL(key, keyAVL);

        boolean isKeyValid = false;

        int i = 0;
        while(i < keyBST.size()) {
            if(keyBST.get(i).getValue() == null) {
                keyBST.remove(i);
            } else {
                isKeyValid = true;
                i++;
            }
        }

        i = 0;
        while(i < keyAVL.size()) {
            if(keyAVL.get(i).getValue() == null) {
                keyAVL.remove(i);
            } else {
                i++;
            }

        }

        if(!isKeyValid || keyBST == null) {
            System.out.println("a");
            return false;
        }
        return true;
    }


    public static void testParser(List<String> contents, BST bst, AVL avl) {
        Parser parser = new Parser();
        List<String> path = new ArrayList<>();

        try {
            parser.run(contents, bst, avl, path);
            System.out.println("A gramática está correta");
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
            } else if(opt > 1 && opt < 9) {
                if(!bst.isEmpty() && !avl.isEmpty()){
                    if(opt == 2) {
                        System.out.print("Chave/escopo: ");

                        scanner.nextLine();
                        String identifier = scanner.nextLine();

                        System.out.println();
                        searchIdentifier(bst, avl, identifier);
                    } else if(opt == 3) {
                        System.out.print("Digite o escopo em que deseja inserir a nova chave/escopo: ");

                        scanner.nextLine();
                        String scope = scanner.nextLine();

                        List<NodeAVL> listNodeAVL = new ArrayList<>();
                        List<String> path;
                        if(verifyScope(scope, avl, listNodeAVL)) {
                            if(listNodeAVL.size() > 1) {
                                System.out.println("\nMais de um escopo com o mesmo nome encontrado: ");
                                for(int i = 0; i < listNodeAVL.size(); i++) {
                                    System.out.println((i + 1) + ". " + listNodeAVL.get(i).getIdentifier() + " " +
                                            listNodeAVL.get(i).getPath());
                                }

                                System.out.print("\nEm qual escopo deseja inserir: ");
                                int optScope = scanner.nextInt();
                                scanner.nextLine();

                                while(!(optScope > 0 && optScope <= listNodeAVL.size())) {
                                    System.out.print("\nOpção inválida. Tente novamente: ");
                                    optScope = scanner.nextInt();
                                }
                                path = new ArrayList<>(listNodeAVL.get(optScope - 1).getPath());
                                path.add(listNodeAVL.get(optScope - 1).getIdentifier());
                            } else {
                                path = new ArrayList<>(listNodeAVL.get(0).getPath());
                                path.add(listNodeAVL.get(0).getIdentifier());
                            }

                            System.out.print("\nDigite a nova chave ou escopo (linha vazia para parar): ");

                            List<String> contents = new ArrayList<>();
                            String aux = scanner.nextLine();

                            while(aux != null && !aux.isBlank()) {
                                contents.add(aux);
                                aux = scanner.nextLine();
                            }

                            Parser parser = new Parser();
                            parser.run(contents, bst, avl, path);

                            System.out.println("\nChave/escopo inserido com sucesso!");
                        } else {
                            System.out.println("Não foi possível encontrar o escopo.");
                        }
                    } else if(opt == 4) {
                        System.out.print("Digite a chave que deseja alterar: ");
                        scanner.nextLine();
                        String oldKey = scanner.nextLine();

                        List<NodeBST> keyBST = new ArrayList<>();
                        List<NodeAVL> keyAVL = new ArrayList<>();

                        NodeBST nodeBST = null;
                        NodeAVL nodeAVL = null;

                        if (verifyKey(oldKey, bst, avl, keyBST, keyAVL)) {
                            if (keyBST.size() > 1) {
                                System.out.println("\nMais de uma chave com o mesmo nome encontrado: ");
                                for (int i = 0; i < keyBST.size(); i++) {
                                    System.out.println((i + 1) + ". " + keyBST.get(i).getIdentifier() + " " +
                                            keyBST.get(i).getPath());
                                }

                                System.out.print("\nQual chave deseja alterar: ");
                                int optKey = scanner.nextInt();
                                scanner.nextLine();

                                while (!(optKey > 0 && optKey <= keyBST.size())) {
                                    System.out.print("\nOpção inválida. Tente novamente: ");
                                    optKey = scanner.nextInt();
                                }

                                nodeBST = keyBST.get(optKey - 1);
                                for (int i = 0; i < keyAVL.size(); i++) {
                                    if (keyAVL.get(i).getPath() == nodeBST.getPath()) {
                                        nodeAVL = keyAVL.get(i);
                                        break;
                                    }
                                }
                            } else {
                                nodeBST = keyBST.get(0);
                                nodeAVL = keyAVL.get(0);
                            }

                            System.out.print("\nDigite a nova chave: ");
                            String newKey = scanner.nextLine();

                            if (!verifyKey(newKey, bst, avl, keyBST, keyAVL)) {
                                System.out.println("A nova chave já existe na árvore. Operação de alteração cancelada.");
                            } else {
                                Boolean successBST = bst.update(nodeBST.getIdentifier(), newKey);
                                Boolean successAVL = avl.updateBST(nodeAVL.getIdentifier(), newKey);

                                if (successBST && successAVL) {
                                    System.out.println("Chave alterada com sucesso!");
                                } else {
                                    System.out.println("Ocorreu um erro ao tentar alterar a chave.");
                                }
                            }
                        } else {
                            System.out.println("\nA chave inserida não é válida.");
                        }
                    } else if(opt == 5) {
                        System.out.print("Digite a chave que deseja remover: ");

                        scanner.nextLine();
                        String key = scanner.nextLine();

                        List<NodeBST> keyBST = new ArrayList<>();
                        List<NodeAVL> keyAVL = new ArrayList<>();

                        NodeBST nodeBST = null;
                        NodeAVL nodeAVL = null;

                        if(verifyKey(key, bst, avl, keyBST, keyAVL)) {
                            if(keyBST.size() > 1) {
                                System.out.println("\nMais de um escopo com o mesmo nome encontrado: ");
                                for(int i = 0; i < keyBST.size(); i++) {
                                    System.out.println((i + 1) + ". " + keyBST.get(i).getIdentifier() + " " +
                                            keyBST.get(i).getPath());
                                }

                                System.out.print("\nEm qual escopo deseja inserir: ");
                                int optKey = scanner.nextInt();
                                scanner.nextLine();

                                while(!(optKey > 0 && optKey <= keyBST.size())) {
                                    System.out.print("\nOpção inválida. Tente novamente: ");
                                    optKey = scanner.nextInt();
                                }

                                nodeBST = keyBST.get(optKey);
                                for(int i = 0; i < keyAVL.size(); i++) {
                                    if(keyAVL.get(i).getPath() == nodeBST.getPath()) {
                                        nodeAVL = keyAVL.get(i);
                                        break;
                                    }
                                }
                            } else {
                                nodeBST = keyBST.get(0);
                                nodeAVL = keyAVL.get(0);
                            }

                            Boolean aux = bst.remove(nodeBST);
                            Boolean aux1 = avl.remove(nodeAVL);

                            if(aux && aux1) {
                                System.out.println("Remoção realizada com sucesso!");
                            } else {
                                System.out.println("Ocorreu um erro na remoção.");
                            }
                        } else {
                            System.out.println("\nA chave inserida não é válida.");
                        }
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