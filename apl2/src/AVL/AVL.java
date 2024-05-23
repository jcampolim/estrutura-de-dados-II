package AVL;
import java.util.LinkedList;

public class AVL extends BST_AVL {
    // Construtores
    public AVL() {
        super();
    }

    public AVL(NodeAVL root) {
        super(root);
    }

    public boolean removeBST(String identifier) {
        boolean isRemoved = removeBST(identifier);
        isBalanced(root);

        return isRemoved;
    }

    public void insert(NodeAVL newNode) {
        insertBST(newNode);
        isBalanced(root);
    }

    // Verifica se a árvore está balanceada, se não estiver faz as rotações
    private void isBalanced(NodeAVL root) {
        if (root.getRight() != null) {
            isBalanced(root.getRight());
        }
        if (root.getLeft() != null) {
            isBalanced(root.getLeft());
        }

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
    }

    // Rotação a esquerda
    private void rotateLeft(NodeAVL oldRoot) {
        NodeAVL newRoot = oldRoot.getRight();  // y
        NodeAVL aux = newRoot.getLeft(); // beta

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
    private void rotateRight(NodeAVL oldRoot) {
        NodeAVL newRoot = oldRoot.getLeft(); // x
        NodeAVL aux = newRoot.getRight(); // beta

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
    private void rotateLeftRight(NodeAVL oldRoot) {
        NodeAVL newRoot = oldRoot.getLeft();
        rotateLeft(newRoot);
        rotateRight(oldRoot);
    }

    // Rotação direita-esquerda
    private void rotateRightLeft(NodeAVL oldRoot) {
        NodeAVL newRoot = oldRoot.getRight();
        rotateRight(newRoot);
        rotateLeft(oldRoot);
    }

    public LinkedList<NodeAVL> toList() {
        LinkedList<NodeAVL> list = new LinkedList<>();
        inOrderTraversal(root, list);
        return list;
    }

    // Função para realizar a travessia in-order e adicionar os elementos na lista
    private void inOrderTraversal(NodeAVL node, LinkedList<NodeAVL> list) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), list);
            list.add(node);
            inOrderTraversal(node.getRight(), list);
        }
    }
}

