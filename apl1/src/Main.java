import java.util.*;

public class Main {
    // Verifica se a string é um número
    public static boolean isNumber(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c) && c != '.' ) {
                return false;
            }
        }
        return true;
    }

    // Verifica se a expressão digitada pelo usuário é válida
    public static boolean verifyExpression(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        List<String> tokens = tokenizer.tokenize();

        int nParantesis = 0;
        boolean lastIsOperate = false;
        boolean lastIsOperator = true;

        if(tokens == null) {
            return false;
        } else {
            for (int i = 0; i < tokens.size(); i++) {
                if(nParantesis < 0) {
                    System.out.println("Parênteses incorretos.");
                    return false;
                }

                if(tokens.get(i).equals("(")) {
                    nParantesis++;
                } else if(tokens.get(i).equals(")")) {
                    nParantesis--;
                }
                // Como todos os tokens são válidos, basta verificar a posição deles
                else if(isNumber(tokens.get(i)) && !lastIsOperate && lastIsOperator){
                    lastIsOperate = true;
                    lastIsOperator = false;
                } else if(lastIsOperate && !lastIsOperator){
                    lastIsOperate = false;
                    lastIsOperator = true;
                }
                else{
                    return false;
                }
            }
            if(nParantesis > 0) {
                System.out.println("Parênteses incorretos.");
                return false;
            }
            if(!lastIsOperate) {
                System.out.println("Falta um operando.");
                return false;
            }
        }
        return true;
    }
    
    // Para montar a ávore binária, a estratégia usada foi passar a expressão para posfixa primeiro
    // Função para indicar a prioridade do operador da expressão
    private static int setPrecedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else {
            return 0;
        }
    }

    // Função que converte a expressão para posfixa
    private static List<String> convertToPosfix(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        List<String> tokens = tokenizer.tokenize();

        List<String> postfixExpression = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                postfixExpression.add(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    postfixExpression.add(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop(); 
                } 
            } else {
                while (!operatorStack.isEmpty() && setPrecedence(operatorStack.peek()) >= setPrecedence(token)) {
                    postfixExpression.add(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(")) {
                System.out.println("Parênteses incorretos");
                return null;
            }
            postfixExpression.add(operatorStack.pop());
        }
        return postfixExpression;
    }

    // Cria a árvore binária com a expressão
    public static boolean createTree(String expression, BinaryTree tree) {
        List<String> postfixExpression = convertToPosfix(expression);
        Stack<Node> stack = new Stack<>();

        for (String token : postfixExpression) {
            if (isNumber(token)) {
                stack.push(new Operando(null, Integer.parseInt(token)));
            } else {
                Operador operatorNode = new Operador();
                operatorNode.setOperate(token);
                
                Node right = stack.pop();
                Node left = stack.pop();
                
                operatorNode.setLeft(left);
                operatorNode.setRight(right);
                
                stack.push(operatorNode);
            }
        }

        Node root;
        if (stack.isEmpty()) {
            root = null;
        } else {
            root = stack.pop();
        }

        if (root != null) {
            tree.setRoot(root);
            return true;
        } else {
            return false;
        }
    }

    // Exibe a árvore na tela
    public static void printTree(BinaryTree tree) {
        System.out.print("Pré-ordem: ");
        tree.preOrderTraversal();

        System.out.print("\n\nEm ordem: ");
        tree.inOrderTraversal();

        System.out.print("\n\nPós-ordem: ");
        tree.posOrderTraversal();

        System.out.println();
    }

    // TODO: enzo
    // Calcula o resultado da expressão
    public static float expressionCalculation(BinaryTree tree) {
        String[] a = new String[]{"+", "*", "(", ")", "-", "/"} ;
        List<String> validTokens = Arrays.asList(a);

        Queue<Object> queue = new LinkedList<>();
        Stack<Operador> stack = new Stack<Operador>();
        queue.add(tree.getRoot());
        // Criar fila e pilha de execução (percorrer tree por nivel e colocar na pilha somente operadores)
        while(!queue.isEmpty()){
            Node aux = (Node) queue.poll();
            if(aux.getLeft() != null){
                queue.add(aux.getLeft());
            }
            if(aux.getRight() != null){
                queue.add(aux.getRight());
            }
            if(validTokens.contains(aux.visit().toString())){ // PRECISA IMPLEMENTAR ESSA IDEIA
            stack.add((Operador) aux);
            }
        }
         // Se trabalha a pilha de execução
        while (!stack.isEmpty()){
            stack.pop().operate();  // Pensar em como fazer o no Operador se tranformar em um no operando
                                    // equivalente ao resultado dos nos operandos filhos dele.
        }

        return Float.valueOf(tree.root.visit().toString());

    }

    // Função com todas as chamadas de menu
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
