// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Material disponibilizado pelo professor
// https://profkishimoto.github.io/edii04g11-2024-1/

public class AVL extends BST {
    // Construtores
    public AVL() {
        super();
    }

    public AVL(Node root) {
        super(root);
    }

    // Rotação a esquerda
    public void rotateLeft() {
        rotateLeft(root);
    }

    public void rotateLeft(Node oldRoot) {
        root = oldRoot.getRight();

        oldRoot.setParent(root);
        root.setParent(null);

        if(root.getLeft() != null) {
            oldRoot.setRight(root.getLeft());
            root.getLeft().setParent(oldRoot);
        } else {
            oldRoot.setRight(null);
        }

        root.setLeft(oldRoot);
    }

    // Rotação a direita
    public void rotateRight() {
        rotateRight(root);
    }

    public void rotateRight(Node oldRoot) {
        root = oldRoot.getLeft();

        oldRoot.setParent(root);
        root.setParent(null);

        if(root.getRight() != null) {
            oldRoot.setLeft(root.getRight());
            root.getRight().setParent(oldRoot);
        } else {
            oldRoot.setLeft(null);
        }

        root.setRight(oldRoot);
    }

    // Rotação esquerda-direita
    public void rotateLeftRight() {
            rotateLeftRight(root);
        }
        
    public void rotateLeftRight(Node oldRoot) {
            Node aux = root.getLeft();
            Node newRoot = aux.getRight();
    
            oldRoot.setLeft(newRoot);
            aux.setParent(newRoot);
    
            if(newRoot.getLeft() != null) {
                aux.setRight(newRoot.getLeft());
                newRoot.getLeft().setParent(aux);
            } else {
                aux.setRight(null);
            }
    
            newRoot.setLeft(aux);
    
            root = newRoot;
            oldRoot.setParent(newRoot);
            newRoot.setParent(null);
    
            if(newRoot.getRight() != null) {
                oldRoot.setLeft(newRoot.getRight());
                newRoot.getRight().setParent(oldRoot);
            } else {
                oldRoot.setLeft(null);
            }
    
            newRoot.setRight(oldRoot);
        }

        // Rotação direita-esquerda
    public void rotateRightLeft() {
        rotateRightLeft(root);
    }

    public void rotateRightLeft(Node oldRoot) {
        Node aux = root.getRight();
        Node newRoot = aux.getLeft();

        oldRoot.setRight(newRoot);
        aux.setParent(newRoot);

        if(newRoot.getRight() != null) {
            aux.setLeft(newRoot.getRight());
            newRoot.getRight().setParent(aux);
        } else {
            aux.setLeft(null);
        }

        newRoot.setRight(aux);

        root = newRoot;
        oldRoot.setParent(newRoot);
        newRoot.setParent(null);

        if(newRoot.getLeft() != null) {
            oldRoot.setRight(newRoot.getLeft());
            newRoot.getLeft().setParent(oldRoot);
        } else {
            oldRoot.setRight(null);
        }

        newRoot.setLeft(oldRoot);
    }
}
