//Nomes:
// - Enzo Guarnieri, 10410074
// - Erika Borges Piaui, 10403716
// - Júlia Campolim de Oste, 10408802

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert("08");
        bst.insert("03");
        bst.insert("01");
        bst.insert("06");
        bst.insert("04");
        bst.insert("07");
        bst.insert("10");
        bst.insert("14");
        System.out.println("Testando a BST.\n");

        bst.inOrderTraversal();
        System.out.println("\n");

        System.out.println("Menor elemento: " + bst.findMin().getData());
        System.out.println("Maior elemento: " + bst.findMax().getData());
        System.out.println("\n");

        String searchData = "10";
        Node searchNode = bst.search(searchData); 
        if (searchNode != null) {
            System.out.println("Nó com chave " + searchData + " encontrado!");
        } else {
            System.out.println("Nó com chave " + searchData + " não encontrado!");
        }

        searchData = "20";
        searchNode = bst.search(searchData); 
        if (searchNode != null) {
            System.out.println("Nó com chave " + searchData + " encontrado!");
        } else {
            System.out.println("Nó com chave " + searchData + " não encontrado!");
        }
        System.out.println("\n");
        
        Node predecessor = bst.findPredecessor("03");
        if (predecessor != null) {
            System.out.println("Antecessor de 03: " + predecessor.getData());
        } else {
            System.out.println("Não há antecessor para 03");
        }

        Node successor = bst.findSuccessor("01");
        if (successor != null) {
            System.out.println("Sucessor de 01: " + successor.getData());
        } else {
            System.out.println("Não há sucessor para 01");
        }
        System.out.println("\n");

        System.out.println("Retirando o no 04 (Folha)");
        bst.remove("04");
        System.out.println("Retirando o no 10 (Possui apenas um filho)");
        bst.remove("10");
        System.out.println("Retirando o no 8 (Possui 2 filhos e é raiz)");
        bst.remove("10");
        System.out.println("Retirando o no 20 (Não existe)");
        bst.remove("20");
        System.out.println("\n");

        bst.inOrderTraversal();



        bst.clear();
        bst.inOrderTraversal();
    }
}
