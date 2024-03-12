public class BST extends BinaryTree {
    public BST() {
        super();
    }

    public BST(Node root) {
        super(root);
    }

    //TODO: busca o nó com a chave na BST. Caso o nó pertença à BST
    // retorna o nó encontrado, caso contrário retorna null
    // erika
    public Node search(String data) {
        return null;
    }

    //TODO: insere novo nó na BST. Caso já exista um nó com a mesma chave
    // a BST não é alterada
    // Opcional: lançar execeção (throw new RuntimeException()) para indicar que
    // já existe um nó com a chave indicada
    // julia
    public boolean insert(String data) {
        return true;
    }

    //TODO: remove nó, caso o nó pertença à BST.
    // Opcional: lançar execeção (throw new RuntimeException()) para indicar que
    // a BST não possui um nó com a chave indicada
    // enzo
    public boolean remove(String data) {
        return true;
    }

    //TODO: retorna nó com menor chave ou null caso a BST esteja vazia
    // enzo
    public Node findMin() {
        return null;
    }

    //TODO: retona nó com maior chave ou null caso a BST esteja vazia
    // enzo
    public Node findMax() {
        return null;
    }

    //TODO: retorna o antecessor do nó que contém a chave indicada ou null
    // caso esteja vazia
    // erika
    public Node findPredecessor(String data) {
        return null;
    }

    //TODO: retorna o sucessor do nó que contém a chave indicada ou null
    // caso esteja vazia
    // erika
    public Node findSuccessor(String data) {
        return null;
    }

    //TODO: remove todos os nós e suas conexões
    // julia
    public boolean clear() {
        return true;
    }

    //TODO: fazer toString
}
