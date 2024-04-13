public class Node {
    private int data;
    private int balanceFactor;

    private Node parent;
    private Node left;
    private Node right;

    public Node() {
        parent = null;
        left = null;
        right = null;
    }
    public Node(int data, Node parent) {
        this.data = data;
        this.parent = parent;
        right = null;
        left = null;
    }

    // Getters e Setters
    public int getData() {
        return this.data;
    }

    public int getBalanceFactor() {
        return this.balanceFactor;
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

    public void setData(int data) {
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

    public void serBalanceFactor(int balanceFactor) {this.balanceFactor = balanceFactor;}

    // TODO: fazer função
    public void updateBalanceFactor() {
        if(this.isLeaf()) this.serBalanceFactor(0);
        int left, right;
        if(this.getLeft() == null) left = -1;
        else {
        left = getLeft().getHeight();
        }
        if(this.getRight() == null) right = -1;
        else{
            right = getRight().getHeight();
        }
        this.serBalanceFactor(left - right);
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
        sb.append(" - Fator de Balanciamento: " + getBalanceFactor());

        return sb.toString();
    }
}
