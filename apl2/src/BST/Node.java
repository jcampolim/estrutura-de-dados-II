package BST;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<String> path;

    private Node parent;
    private Node left;
    private Node right;

    // Construtores
    public Node() {
        path = new ArrayList<>();
        parent = null;
        left = null;
        right = null;
    }

    // Getters e Setters
    public List<String> getPath() {
        return this.path;
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

    public void addPath(String scope) {
        path.add(scope);
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

    // Verifica se é raiz
    public Boolean isRoot() {
        return this.parent == null;
    }

    // Verifica se é folha
    public Boolean isLeaf() {
        return this.right == null && this.left == null;
    }

    // Retorna o grau do nó
    public int getDegree() {
        if(this.right == null && this.left == null) return 0;
        if(this.right == null || this.left == null) return 1;
        return 2;

    }

    // Retorna o nível do nó
    public int getLevel() {
        if(this.isRoot()) return 0;
        return this.parent.getLevel() + 1;
    }

    // Retorna a altura do nó
    public int getHeight() {
        if(this.isLeaf()) return 0;
        int hl = 0;
        int hr = 0;
        if(!(this.left == null)) hl = this.left.getHeight() + 1;
        if(!(this.right == null)) hr = this.right.getHeight() + 1;
        return Math.max(hl, hr);
    }

    // Exibe na tela as informações do nó
    /*
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nó: " + data);

        if(parent == null) {
            sb.append(" - Pai: null");
        } else {
            sb.append(" - Pai: " + parent.getData());
        }

        if(left == null) {
            sb.append(" - Filho esquerdo: null");
        } else {
            sb.append(" - Filho esquerdo: " + left.getData());
        }

        if(right == null) {
            sb.append(" - Filho direito: null");
        } else {
            sb.append(" - Filho direito: " + right.getData());
        }

        sb.append(" - É folha: " + isLeaf());
        sb.append(" - É raiz: " + isRoot());
        sb.append(" - Grau: " + getDegree());
        sb.append(" - Nível: " + getLevel());
        sb.append(" - Altura: " + getHeight());

        return sb.toString();
    }
    */
}
