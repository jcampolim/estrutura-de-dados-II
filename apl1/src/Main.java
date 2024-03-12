import java.util.Scanner;

public class Main {
    //TODO: enzo
    public static boolean verifyExpression(String expression) {
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
                System.out.println("O valor digitado deve ser um inteiro.");
            }

            System.out.println("***********************************************************\n");

            if(option > 5 || option < 1) {
                System.out.println("Por favor, escolha uma opção válida.");
            } else if(option == 1) {
                System.out.print("Digite a expressão: ");

                scan.nextLine();
                expression = scan.nextLine();

                isValid = verifyExpression(expression);

                if(!isValid) {
                    System.out.println("A expressão digitada não é válida.");
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