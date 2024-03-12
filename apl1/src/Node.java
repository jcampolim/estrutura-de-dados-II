// https://www.geeksforgeeks.org/overriding-in-java/

public class Node {
    private Node parent;
    private Node left;
    private Node right;

    public Node() {
        parent = null;
        left = null;
        right = null;
    }
    public Node(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return this.parent;
    }
    public Node getLeft() {
        return this.left;
    }
    public Node getRight() {
        return this.right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public void setRight(Node right) {
        this.right = right;
    }

    public Boolean isRoot() {
        return this.parent == null;
    }
    public Boolean isLeaf() {
        return this.right == null && this.left == null;
    }

    public String visit() {
        return "";
    }
}

