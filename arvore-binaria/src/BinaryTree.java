// https://www.geeksforgeeks.org/queue-interface-java/
// https://www.programiz.com/java-programming/queue

import java.util.ArrayDeque;
import java.util.Queue;

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

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    public void preOrderTraversal(Node root) {
        System.out.print(root.getData() + " ");

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

        System.out.print(root.getData() + " ");
    }

    public void levelOrderTraversal() {
        Queue<Node> queue = new ArrayDeque<>();

        Node aux;
        queue.add(root);

        while(!queue.isEmpty()) {
            aux = queue.peek();

            if(aux.getLeft() != null) {
                queue.add(aux.getLeft());
            }
            if(aux.getRight() != null) {
                queue.add(aux.getRight());
            }

            System.out.print(aux.getData() + " ");
            queue.remove();
        }
    }
}
