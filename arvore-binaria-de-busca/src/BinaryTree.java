// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
//// - https://sentry.io/answers/how-to-compare-strings-in-java/#:~:text=If%20you%20want%20to%20find,compareTo()%20.
public class BinaryTree {
    Node root;

    // Construtor
    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    // Getters e Setters
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

    // Retorna o grau da árvore
    public int getDegree() {
        return getDegree(root);
    }

    public int getDegree(Node root) {
        int parent = 0, left = 0, right = 0;

        if(root.getLeft() != null) {
            parent++;
            left = getDegree(root.getLeft());
        }
        if(root.getRight() != null) {
            parent++;
            right = getDegree(root.getRight());
        }

        if(left > parent) parent = left;
        if(right > parent) parent = right;

        return parent;
    }

    // Retorna a altura da árvore
    public int getHeight() {
        if(root == null) {
            return -1;
        } else {
            int height = getHeight(root);
            return height;
        }
    }

    public int getHeight(Node root) {
        if(root.isLeaf()) {
            return 0;
        } else {
            int left = 0, right = 0;

            if(root.getLeft() != null) {
                left = 1 + getHeight(root.getLeft());
            }
            if(root.getRight() != null) {
                right = 1 + getHeight(root.getRight());
            }

            if(left > right) {
                return left;
            } else {
                return right;
            }
        }
    }

    // Exibe a árvore em ordem
    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    public void inOrderTraversal(Node root) {
        if(root.getLeft() != null) {
            inOrderTraversal(root.getLeft());
        }

        System.out.print(root.getData() + " ");

        if(root.getRight() != null) {
            inOrderTraversal(root.getRight());
        }
    }
}
