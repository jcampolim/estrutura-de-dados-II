//Fontes:
// - https://profkishimoto.github.io/edii04g11-2024-1/

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree {
    Node root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Node root) {
        this.root = root;
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

    public void inOrderTraversal() {
        inOrderTraversal(root);
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
