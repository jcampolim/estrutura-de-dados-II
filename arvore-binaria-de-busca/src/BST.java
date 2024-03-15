// https://sentry.io/answers/how-to-compare-strings-in-java/#:~:text=If%20you%20want%20to%20find,compareTo()%20.

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
    public Node insert(String data) {
        if(isEmpty()) {
            root = new Node(data, null);
            return root;
        } else {
            Node node = new Node(data, null);
            insert(root, node, data);

            return node;
        }
    }

    public void insert(Node root, Node node, String data) {
        if(data.compareTo(root.getData()) < 0) {
            if(root.getLeft() == null) {
                root.setLeft(node);
                node.setParent(root);

                return;
            }

            insert(root.getLeft(), node, data);
        } else if(data.compareTo(root.getData()) > 0) {
            if(root.getRight() == null) {
                root.setRight(node);
                node.setParent(root);

                return;
            }

            insert(root.getRight(), node, data);
        } else if(data.compareTo(root.getData()) == 0) {
            return;
        }
    }

    //TODO: remove nó, caso o nó pertença à BST.
    // Opcional: lançar execeção (throw new RuntimeException()) para indicar que
    // a BST não possui um nó com a chave indicada
    // enzo
    public boolean remove(String data) {
        if(this.isEmpty()) return false;
        Node remove = this.search(data);
        // Caso 1: No e folha
        // Subimos para o pai e vemos qual filho temos que remover
        if(remove.isLeaf()) {
            if (remove.getParent().getRight().getData().equals(data)) {
                remove.getParent().setRight(null);
                remove.setParent(null);
            } else {
                remove.getParent().setLeft(null);
                remove.setParent(null);
            }
            return true;
        }
        // Caso 2: No so possui uma sub-arvore
        if(remove.getDegree() == 1){
            // pai para a direita
            if (remove.getParent().getRight().getData().equals(data)){
                // filho a direita
                if(remove.getRight() != null){
                    remove.getParent().setRight(remove.getRight());
                    remove.getRight().setParent(remove.getParent());
                }else{ // filho a esquerda
                    remove.getParent().setRight(remove.getRight());
                    remove.getLeft().setParent(remove.getParent());
                }
            }else{ // pai para a esquerda
                // filho a direita
                if(remove.getRight() != null){
                    remove.getParent().setLeft(remove.getRight());
                    remove.getRight().setParent(remove.getParent());
                }else{ // filho a esquerda
                    remove.getParent().setLeft(remove.getRight());
                    remove.getLeft().setParent(remove.getParent());
                }
            }
            return true;
        }
        // Caso 3: No possui dois filhos
        // Usando o antecessor
        // Trocamos as informacoes
        Node antecessor = this.findPredecessor(data);
        String aux = antecessor.getData();
        antecessor.setData(remove.getData());
        remove.setData(aux);
        // Removemos agora que data é folha
        if (remove.getParent().getRight().getData().equals(data)) {
            remove.getParent().setRight(null);
            remove.setParent(null);
        } else {
            remove.getParent().setLeft(null);
            remove.setParent(null);
        }
        return true;

    }

    //TODO: retorna nó com menor chave ou null caso a BST esteja vazia
    // enzo
    public Node findMin() {
        if(this.isEmpty()) return null;
        Node node = this.getRoot();
        while (!node.isLeaf()) node = node.getLeft();
        return node;
    }

    //TODO: retona nó com maior chave ou null caso a BST esteja vazia
    // enzo
    public Node findMax() {
        if(this.isEmpty()) return null;
        Node node = this.getRoot();
        while (!node.isLeaf()) node = node.getRight();
        return node;
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
    public void clear() {
        clear(root);
    }

    public void clear(Node root) {
        if(root.getLeft() != null) {
            clear(root.getLeft());
            root.setLeft(null);
        }
        if(root.getRight() != null) {
            clear(root.getRight());
            root.setRight(null);
        }

        root.setParent(null);
        root.setData(null);
    }

    //TODO: fazer toString
}
