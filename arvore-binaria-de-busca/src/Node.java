//Fontes:
// - https://profkishimoto.github.io/edii04g11-2024-1/

public class Node {
    private String data;
    private Node parent;
    private Node left;
    private Node right;

    public Node() {
        data = "";
        parent = null;
        left = null;
        right = null;
    }
    public Node(String data, Node parent) {
        this.data = data;
        this.parent = parent;
        right = null;
        left = null;
    }

    public String getData() {
        return this.data;
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

    public void setData(String data) {
        this.data = data;
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

    public int getDegree() {
        if(this.right == null && this.left == null) return 0;
        if(this.right == null || this.left == null) return 1;
        return 2;

    }

    public int getLevel() {
        if(this.isRoot()) return 0;
        return this.parent.getLevel() + 1;
    }

    public int getHeight() {
        if(this.isLeaf()) return 0;
        int hl = 0;
        int hr = 0;
        if(!(this.left == null)) hl = this.left.getHeight() + 1;
        if(!(this.right == null)) hr = this.right.getHeight() + 1;
        return Math.max(hl, hr);
    }
}

