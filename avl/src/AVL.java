public class AVL extends BST {
    public AVL() {
        super();
    }

    public AVL(Node root) {
        super(root);
    }

    // TODO: método ll (rotateLeft) - erika

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

    // TODO: método lr (rotateLeftRight) - erika

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
