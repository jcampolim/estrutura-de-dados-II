// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
//// - https://sentry.io/answers/how-to-compare-strings-in-java/#:~:text=If%20you%20want%20to%20find,compareTo()%20.
public class Main {
    public static void main(String[] args) {
        BST bst = new BST();

        System.out.println("> Inserindo os nós na BST...\n");

        // Inserindo nós na BST
        bst.insert("08");
        bst.insert("03");
        bst.insert("01");
        bst.insert("06");
        bst.insert("04");
        bst.insert("07");
        bst.insert("10");
        bst.insert("14");

        // Imprime os dados de cada nó da árvore
        bst.treeInfo();

        System.out.print("Percuso em ordem: ");
        bst.inOrderTraversal();

        System.out.println("\n> Tentando inserir uma chave que já foi registrada (não há mudanças): \n");
        bst.insert("03");

        bst.treeInfo();

        System.out.print("Percuso em ordem: ");
        bst.inOrderTraversal();

        System.out.println("\n> Buscando por nós existentes e não existentes...\n");

        String data = "10";
        Node searchNode = bst.search(data);
        if (searchNode != null) {
            System.out.println("Nó com chave '" + data + "' encontrado!");
        } else {
            System.out.println("Nó com chave '" + data + "' não encontrado!");
        }

        data = "20";
        searchNode = bst.search(data);
        if (searchNode != null) {
            System.out.println("Nó com chave '" + data + "' encontrado!");
        } else {
            System.out.println("Nó com chave '" + data + "' não encontrado!");
        }
        System.out.println();

        System.out.println("> Removendo elementos...");

        System.out.println("\n> Removendo o nó '04' (folha):\n");
        bst.remove("04");

        bst.treeInfo();
        System.out.print("Percuso em ordem: ");
        bst.inOrderTraversal();

        System.out.println("\n> Removendo o nó '10' (possui um filho):\n");
        bst.remove("10");

        bst.treeInfo();
        System.out.print("Percuso em ordem: ");
        bst.inOrderTraversal();

        System.out.println("\n> Removendo o nó '08' (possui dois filhos e é raiz):\n");
        bst.remove("08");

        bst.treeInfo();
        System.out.print("Percuso em ordem: ");
        bst.inOrderTraversal();

        System.out.println("\n> Removendo o nó '20' (não existe):\n");
        bst.remove("20");
        System.out.println();

        bst.treeInfo();
        System.out.print("Percuso em ordem: ");
        bst.inOrderTraversal();

        System.out.println();

        System.out.println("Menor elemento: '" + bst.findMin().getData() + "'.");
        System.out.println("Maior elemento: " + bst.findMax().getData() + "'.\n");

        System.out.println("> Buscando antecessor e sucessor...\n");

        data = "03";
        Node predecessor = bst.findPredecessor(data);
        if (predecessor != null) {
            System.out.println("Antecessor de '" + data + "': " + predecessor.getData() + ".");
        } else {
            System.out.println("Não há antecessor para '" + data + "'.");
        }

        data = "01";
        Node successor = bst.findSuccessor(data);
        if (successor != null) {
            System.out.println("Sucessor de '" + data + "': " + successor.getData() + ".");
        } else {
            System.out.println("Não há sucessor para '" + data + "'.");
        }
        System.out.println("");

        System.out.println("> Buscando antecessor e sucessor nulos...\n");

        data = "01";
        predecessor = bst.findPredecessor(data);
        if (predecessor != null) {
            System.out.println("Antecessor de '" + data + "': " + predecessor.getData() + ".");
        } else {
            System.out.println("Não há antecessor para '" + data + "'.");
        }

        data = "14";
        successor = bst.findSuccessor(data);
        if (successor != null) {
            System.out.println("Sucessor de '" + data + "': " + successor.getData() + ".");
        } else {
            System.out.println("Não há sucessor para '" + data + "'.");
        }
        System.out.println();

        System.out.println("> Removendo todos os nós da BST...\n");
        bst.clear();

        bst.treeInfo();
        System.out.print("Percurso em ordem: ");
        bst.inOrderTraversal();
    }
}
