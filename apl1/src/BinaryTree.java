// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// https://www.geeksforgeeks.org/overriding-in-java/
// Material disponibilizado pelo professor


public class BinaryTree {
    Node root;

    // Construtor
    public BinaryTree() {
        root = null;
    }

    // Getter e setter
    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    // Verifica se a árvore está vazia
    public boolean isEmpty() {
        return root == null;
    }

    // Exibe a ávore na tela dos modos em ordem, pré-ordem e pós-ordem
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    public void inOrderTraversal(Node root) {
        if(root.getLeft() != null) {
            inOrderTraversal(root.getLeft());
        }

        System.out.print(root.visit() + " ");

        if(root.getRight() != null) {
            inOrderTraversal(root.getRight());
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    public void preOrderTraversal(Node root) {
        System.out.print(root.visit() + " ");

        if(root.getLeft() != null) {
            preOrderTraversal(root.getLeft());
        }
        if(root.getRight() != null) {
            preOrderTraversal(root.getRight());
        }
    }

    public void posOrderTraversal() {
        posOrderTraversal(root);
    }

    public void posOrderTraversal(Node root) {
        if(root.getLeft() != null) {
            posOrderTraversal(root.getLeft());
        }
        if(root.getRight() != null) {
            posOrderTraversal(root.getRight());
        }

        System.out.print(root.visit() + " ");
    }
}
