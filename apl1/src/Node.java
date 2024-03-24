// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// https://www.geeksforgeeks.org/overriding-in-java/

public class Node {
    private Node parent;
    private Node left;
    private Node right;

    // Construtores
    public Node() {
        parent = null;
        left = null;
        right = null;
    }
    public Node(Node parent) {
        this.parent = parent;
    }

    // Getters e setters
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

    // Verifica se o nó é raiz da árvore
    public Boolean isRoot() {
        return this.parent == null;
    }

    // Verifica se o nó é folha da árvore
    public Boolean isLeaf() {
        return this.right == null && this.left == null;
    }

    // Método que será herdado pelas sub-classes de Node
    public Object visit() {
        return "";
    }
}

