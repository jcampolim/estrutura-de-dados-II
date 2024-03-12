public class BinaryTree {
    Node root;

    public BinaryTree() {
        root = null;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

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
