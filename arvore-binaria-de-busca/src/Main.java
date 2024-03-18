//Nomes:
// - Enzo Guarnieri, 10410074
// - Erika Borges Piaui, 10403716
// - Júlia Campolim de Oste, 10408802

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        BST teste = new BST();
        teste.remove("a");

        bst.insert("08");
        bst.insert("03");
        bst.insert("01");
        bst.insert("06");
        bst.insert("04");
        bst.insert("07");
        bst.insert("10");
        bst.insert("14");

        System.out.println(bst.findMin().getData());
        System.out.println(bst.findMax().getData());
        
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

        bst.inOrderTraversal();

        bst.clear();
        bst.inOrderTraversal();
    }
}
