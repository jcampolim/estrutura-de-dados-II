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
        boolean ultimoEraOperando = false;
        boolean ultimoEraOperador = true;
        if(tokens == null) {return false;}
        else {
            for (int i = 0; i < tokens.size(); i++) {
                if(nParantesis < 0) {
                    System.out.println("Paranteses incorretos.");
                    return false;
                }
                if(tokens.get(i).equals("(")) {nParantesis++;}
                else if(tokens.get(i).equals(")")) {nParantesis--;}
                // Como sabemos que todos os tokens são validos.
                // Podemos verificar o formato deles.
                else if(isNumber(tokens.get(i)) && !ultimoEraOperando && ultimoEraOperador){
                    ultimoEraOperando = true;
                    ultimoEraOperador = false;
                } else if(ultimoEraOperando && !ultimoEraOperador){
                    ultimoEraOperando = false;
                    ultimoEraOperador = true;
                }
                else{
                    return false;
                }
            }
            if(nParantesis > 0) {
                System.out.println("Paranteses incorretos.");
                return false;
            }
            if(!ultimoEraOperando) {
                System.out.println("Falta um operando.");
                return false;
            }
        }
        return true;
    }
    
    //TODO: erika
    private static int setPrecedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else {
            return 0;
        }
    }
    
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
                System.out.println("Parenteses incorretos");
                return null;
            }
            postfixExpression.add(operatorStack.pop());
        }
        return postfixExpression;
    }

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
       /*
       Character[] a = new Character[]{'+', '*', '(', ')', '-', '/'} ;
       List<Character> validTokens = Arrays.asList(a);
       Queue<Node> queue = new PriorityQueue<Node>();
        Stack<Node> stack = new Stack<Node>();
        queue.add(tree.getRoot());
        // Criar fila e pilha de execução (percorrer tree por nivel e colocar na pilha somente operadores)
        while(!queue.isEmpty()){
            Node aux = queue.pull();
            if(aux.getLeft() != null){
                queue.add(aux.getLeft());
            }
            if(aux.getRight() != null){
                queue.add(aux.getRight());
            }
            if(validTokens.contains(queue.peek())) { // PRECISA IMPLEMENTAR ESSA IDEIA // Acho que implementei kkkkk
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
