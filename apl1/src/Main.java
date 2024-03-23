import java.util.*;

public class Main {
    //TODO: enzo
    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.' ) {
                return false;
            }
        }
        return true;
    }
    public static boolean verifyExpression(String expression) {
        Tokenizer t = new Tokenizer(expression);
        List<String> tokens = t.tokenize();
        int nParantesis = 0;
        Boolean opBinario = false;
        if(tokens == null) {return false;}
        else {
            for (int i = 0; i < tokens.size(); i++) {
                if(nParantesis < 0) {
                    System.out.println("Paranteses incorretos.");
                    return false;
                }
                if(tokens.get(i).equals("(")) {nParantesis++;}
                if(tokens.get(i).equals(")")) {nParantesis--;}
                // Como sabemos que todos os tokens são validos.
                // Podemos verificar o formato deles.
                if(isNumber(tokens.get(i)) && !opBinario) {opBinario = true;}
                else if(!tokens.get(i).equals("(") || !tokens.get(i).equals(")") && opBinario) {opBinario = false;}
            }
            if(nParantesis > 0) {
                System.out.println("Paranteses incorretos.");
                return false;
            }
            if(!opBinario) {
                System.out.println("Falta um operando.");
                return false;
            }
        }
        return true;
    }

    //TODO: erika
    public static boolean createTree(String expression, BinaryTree tree) {
        return true;
    }

    public static void printTree(BinaryTree tree) {
        System.out.print("Pré-ordem: ");
        tree.preOrderTraversal();

        System.out.print("\n\nEm ordem: ");
        tree.inOrderTraversal();

        System.out.print("\n\nPós-ordem: ");
        tree.posOrderTraversal();
    }

    //TODO: enzo
    public static float expressionCalculation(BinaryTree tree) {
       /* Queue<Node> queue = new PriorityQueue<Node>();
        Stack<Node> stack = new Stack<Node>();
        queue.add(tree.getRoot());
        // Criar fila e pilha de execução (percorrer tree por nivel e colocar na pilha somente operadores)
        while(!queue.isEmpty()){
            Node aux = queue.pop;
            if(aux.getLeft() != null){
                queue.add(aux.getLeft());
            }
            if(aux.getRight() != null){
                queue.add(aux.getRight());
            }
            if(aux.isOperator) { // PRECISA IMPLEMENTAR ESSA IDEIA
            stack.add(aux);
            }
        }
        // Se trabalha a pilha de execução;
        while (!stack.isEmpty()){
            stack.pop().operate(); // Pensar em como fazer o no Operador se tranformar em um no operando
                                  // equivalente ao resultado dos nos operandos filhos dele.
        }
*/




        return 9;

    }

    public static void menu() {
        Scanner scan = new Scanner(System.in);

        int option = 0;
        String expression =  "", auxOption;


        boolean isValid = false, hasTree = false;

        BinaryTree tree = new BinaryTree();

        do {
            System.out.println("\n***********************************************************");
            System.out.println("1. Entrada da expressão aritmética na notação infixa.");
            System.out.println("2. Criação da árvore binária de expressão aritmética.");
            System.out.println("3. Exibição da árvore binária de expressão aritmética.");
            System.out.println("4. Cálculo da expressão (realizando o percurso da árvore).");
            System.out.println("5. Encerramento do programa.");

            System.out.print("\nOpção: ");

            auxOption = scan.nextLine();
            try {
                option = Integer.parseInt(auxOption);
            } catch (Exception e) {
                System.out.println("O valor digitado deve ser um inteiro\n.");
            }

            System.out.println("***********************************************************\n");

            if(option > 5 || option < 1) {
                System.out.println("Por favor, escolha uma opção válida.");
            } else if(option == 1) {
                System.out.print("Digite a expressão: ");

                expression = scan.nextLine();
                scan.nextLine();

                isValid = verifyExpression(expression);

                if(!isValid) {
                    System.out.println("A expressão digitada não é válida.");
                }
                else{
                    System.out.println("A expressão digitada é válida.");
                }
            } else if(option == 2) {
                if(isValid) {
                    createTree(expression, tree);
                    hasTree = true;
                    System.out.println("Árvore criada com sucesso!");
                } else {
                    System.out.println("Por favor, insira uma expressão válida.");
                }
            } else if(option == 3) {
                if(hasTree) {
                    printTree(tree);
                } else {
                    System.out.println("Por favor, crie a árvore primeiro.");
                }
            } else if(option == 4) {
                if(hasTree) {
                    System.out.print(expression + " = ");
                    System.out.println(expressionCalculation(tree));
                } else {
                    System.out.println("Por favor, crie a árvore primeiro.");
                }
            } else {
                System.out.println("Encerrando programa...");
            }

        } while(option != 5);
    }

    public static void main(String[] args) {
        menu();
    }
}