// Integrantes - RA
// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste - 10408802

// Fontes de pesquisa:
// https://profkishimoto.github.io/edii04g11-2024-1/
// https://www.geeksforgeeks.org/queue-interface-java/
// https://www.programiz.com/java-programming/queue

public class Node {
    private String data;
    private Node parent;
    private Node left;
    private Node right;

    // Constructors
    public Node() {
        data = "";
        parent = null;
        left = null;
        right = null;
    }
    public Node(String data, Node parent, Node left, Node right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    // Getters and Setters
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

    // Get node degree
    public int getDegree() {
        if(this.right == null && this.left == null) return 0;
        if(this.right == null || this.left == null) return 1;
        return 2;

    }

    // Get node level
    public int getLevel() {
        if(this.isRoot()) return 0;
        return this.parent.getLevel() + 1;
    }

    // Get node height
    public int getHeight() {
        if(this.isLeaf()) return 0;
        int hl = 0;
        int hr = 0;
        if(!(this.left == null)) hl = this.left.getHeight() + 1;
        if(!(this.right == null)) hr = this.right.getHeight() + 1;
        return Math.max(hl, hr);
    }
}

