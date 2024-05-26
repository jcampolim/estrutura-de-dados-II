package BST;

// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/

import java.util.ArrayList;
import java.util.List;

public class NodeBST {

    // Atributos
    private List<String> path;

    private NodeBST parent;
    private NodeBST left;
    private NodeBST right;

    // Construtores
    public NodeBST() {
        path = new ArrayList<>();
        parent = null;
        left = null;
        right = null;
    }

    // Getters e Setters
    public List<String> getPath() {
        return this.path;
    }
    public NodeBST getParent() {
        return this.parent;
    }
    public NodeBST getLeft() {
        return this.left;
    }
    public NodeBST getRight() {
        return this.right;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public void setParent(NodeBST parent) {
        this.parent = parent;
    }
    public void setLeft(NodeBST left) {
        this.left = left;
    }
    public void setRight(NodeBST right) {
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

    public String getIdentifier() {
        return "";
    }

    public void setIdentifier(String identifier) {

    }

    public String getValue() {
        return null;
    }

    public void setValue(String value) {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(getValue() != null) {
            sb.append("Tipo de dado: chave");
            sb.append(" - Valor: " + getValue());
        } else {
            sb.append("Tipo de dado: escopo");
        }

        sb.append(" - Identificador: " + getIdentifier());

        if(path.isEmpty()) {
            sb.append(" - Escopo: global");
        } else {
            sb.append(" - Escopo: " + path.get(path.size() - 1));
        }

        sb.append(" - Grau: " + getDegree());
        sb.append(" - Nível: " + getLevel());

        return sb.toString();
    }
}
