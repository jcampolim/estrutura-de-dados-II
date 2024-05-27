package AVL;

// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
// https://www.javatpoint.com/java-tokens
// https://www.devmedia.com.br/tokenizacao-com-scanner-em-java/26508
// SZWARCFITER, J.L.; MARKENZON, L. Estruturas de Dados e seus Algoritmos. 3ª. ed. Rio de Janeiro: LTC, 2010.
public class BinaryTree {
    NodeAVL root;

    // Construtor
    public BinaryTree() {
        root = null;
    }

    public BinaryTree(NodeAVL root) {
        this.root = root;
    }

    // Getters e Setters
    public void setRoot(NodeAVL root) {
        this.root = root;
    }

    public NodeAVL getRoot() {
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

    public int getDegree(NodeAVL root) {
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

    public int getHeight(NodeAVL root) {
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

    public void inOrderTraversal(NodeAVL root) {
        if(root.getLeft() != null) {
            inOrderTraversal(root.getLeft());
        }

        System.out.print(root.getIdentifier() + " ");

        if(root.getRight() != null) {
            inOrderTraversal(root.getRight());
        }
    }

    // Exibe a árvore pré ordem
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    public void preOrderTraversal(NodeAVL root) {
        System.out.print(root.getIdentifier() + " ");

        if(root.getLeft() != null) {
            preOrderTraversal(root.getLeft());
        }
        if(root.getRight() != null) {
            preOrderTraversal(root.getRight());
        }
    }

    // Exibe a árvore pós ordem
    public void posOrderTraversal() {
        posOrderTraversal(root);
    }

    public void posOrderTraversal(NodeAVL root) {
        if(root.getLeft() != null) {
            posOrderTraversal(root.getLeft());
        }
        if(root.getRight() != null) {
            posOrderTraversal(root.getRight());
        }

        System.out.print(root.getIdentifier() + " ");
    }
}

