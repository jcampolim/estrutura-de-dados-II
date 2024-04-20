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

    public boolean remove(int data) {
        boolean isRemoved = removeBST(data);
        isBalanced(root);

        return isRemoved;
    }

    public void insert(int data) {
        insertBST(data);
        isBalanced(root);
    }

    // Verifica se a árvore está balanceada
    private void isBalanced(Node root) {
        int bf = root.getBalanceFactor();

        if (!(bf <= 1 && bf >= -1)) {
            if (bf > 1) {
                if (root.getRight().getBalanceFactor() < 0) {
                    rotateRightLeft(root);
                } else {
                    rotateLeft(root);
                }
            } else {
                if (root.getLeft().getBalanceFactor() > 0) {
                    rotateLeftRight(root);
                } else {
                    rotateRight(root);
                }
            }
        }

        if (root.getRight() != null) {
            isBalanced(root.getRight());
        }
        if (root.getLeft() != null) {
            isBalanced(root.getLeft());
        }
    }

    // Rotação a esquerda
    private void rotateLeft(Node oldRoot) {
        Node newRoot = oldRoot.getRight();  // y
        Node aux = newRoot.getLeft(); // beta

        // newRoot se torna root da sub arvore, é conserta o parent dele
        if(oldRoot.getParent() != null){
            if(oldRoot.getParent().getRight() == oldRoot){
                oldRoot.getParent().setRight(newRoot);
                newRoot.setParent(oldRoot.getParent());
            }
            else{
                oldRoot.getParent().setLeft(newRoot);
                newRoot.setParent(oldRoot.getParent());
            }
        }
        else{
            newRoot.setParent(null);
            root = newRoot;
        }
        oldRoot.setParent(newRoot);
        newRoot.setLeft(oldRoot);
        oldRoot.setRight(aux);
        if(aux != null) aux.setParent(oldRoot);
    }

    // Rotação a direita
    private void rotateRight(Node oldRoot) {
        Node newRoot = oldRoot.getLeft(); // x
        Node aux = newRoot.getRight(); // beta

        // newRoot se torna root da sub arvore, é conserta o parent dele
        if(oldRoot.getParent() != null){
            if(oldRoot.getParent().getRight() == oldRoot){
                oldRoot.getParent().setRight(newRoot);
                newRoot.setParent(oldRoot.getParent());
            }
            else{
                oldRoot.getParent().setLeft(newRoot);
                newRoot.setParent(oldRoot.getParent());
            }
        }
        else{
            newRoot.setParent(null);
            root = newRoot;
        }
        oldRoot.setParent(newRoot);
        newRoot.setRight(oldRoot);
        oldRoot.setLeft(aux);
        if(aux != null) aux.setParent(oldRoot);
    }

    // Rotação esquerda-direita
    private void rotateLeftRight(Node oldRoot) {
        Node newRoot = oldRoot.getLeft();
        rotateLeft(newRoot);
        rotateRight(oldRoot);
    }

    // Rotação direita-esquerda
    private void rotateRightLeft(Node oldRoot) {
        Node newRoot = oldRoot.getRight();
        rotateRight(newRoot);
        rotateLeft(oldRoot);
    }
}
