package AVL;

import java.util.ArrayList;
import java.util.List;

public class NodeAVL {
    // Atributos
    private List<String> path;

    private int balanceFactor;
    private NodeAVL parent;
    private NodeAVL left;
    private NodeAVL right;

    // Construtores
    public NodeAVL() {
        this.path = new ArrayList<String>();
        this.parent = null;
        right = null;
        left = null;
    }

    // Getters e Setters
    public int getBalanceFactor() {
        updateBalanceFactor();
        return this.balanceFactor;
    }

    public List<String> getPath() {
        return this.path;
    }

    public NodeAVL getParent() {
        return this.parent;
    }

    public NodeAVL getLeft() {
        return this.left;
    }

    public NodeAVL getRight() {
        return this.right;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public void setParent(NodeAVL parent) {
        this.parent = parent;
    }

    public void setLeft(NodeAVL left) {
        this.left = left;
    }

    public void setRight(NodeAVL right) {
        this.right = right;
    }

    // Atualiza o fator de balanceamento
    private void updateBalanceFactor() {
        if(this.isLeaf()) {
            balanceFactor = 0;
        } else {
            int left, right;
            if(this.getLeft() == null) {
                left = -1;
            } else {
                left = this.getLeft().getHeight();
            }

            if(this.getRight() == null) {
                right = -1;
            } else {
                right = this.getRight().getHeight();
            }

            balanceFactor = right - left;
        }
    }

    // Verifica se é raiz
    public Boolean isRoot() {
        return this.parent == null;
    }

    // Verifica se é folha
    public Boolean isLeaf() {
        return this.right == null && this.left == null;
    }

    // Retorna o grau da árvore
    public int getDegree() {
        if(this.right == null && this.left == null) return 0;
        if(this.right == null || this.left == null) return 1;
        return 2;

    }

    // Retona o nível da árvore
    public int getLevel() {
        if(isRoot()) return 0;
        return parent.getLevel() + 1;
    }

    // Retorna a altura da árvore
    public int getHeight() {
        if(this.isLeaf()) return 0;
        int hl = 0;
        int hr = 0;
        if(!(this.left == null)) hl = this.left.getHeight() + 1;
        if(!(this.right == null)) hr = this.right.getHeight() + 1;
        return Math.max(hl, hr);
    }

    public String getIdentifier() {
        return "";
    }

    public void setIdentifier(String identifier) {

    }

    /*
    // Retorna as informações do nó
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nó: " + data);

        if(parent == null) {
            sb.append(" - Pai: null");
        } else {
            sb.append(" - Pai: " + parent.getIdentifier());
        }

        if(left == null) {
            sb.append(" - Filho esquerdo: null");
        } else {
            sb.append(" - Filho esquerdo: " + left.getIdentifier());
        }

        if(right == null) {
            sb.append(" - Filho direito: null");
        } else {
            sb.append(" - Filho direito: " + right.getIdentifier());
        }

        sb.append(" - É folha: " + isLeaf());
        sb.append(" - É raiz: " + isRoot());
        sb.append(" - Grau: " + getDegree());
        sb.append(" - Nível: " + getLevel());
        sb.append(" - Altura: " + getHeight());
        sb.append(" - Fator de balanceamento: " + getBalanceFactor());

        return sb.toString();
    } */
}
