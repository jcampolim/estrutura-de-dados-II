package BST;

public class BST extends BinaryTree {

    // Construtor
    public BST() {
        super();
    }

    public BST(Node root) {
        super(root);
    }


    // Procura um nó na árvore pela chave
    public Node search(String data) {
        return search(root, data);
    }

    private Node search(Node root, String data) {
        if (root == null || data.equals(root.getData())) {
            return root;
        } else if (data.compareTo(root.getData()) < 0) {
            return search(root.getLeft(), data);
        } else {
            return search(root.getRight(), data);
        }
    }

    // Insere uma nova chave na árvore
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

    // Remove uma chave da árvore
    public boolean remove(String data) {
        if(!this.isEmpty()) {
            Node root = this.search(data);

            // Se o nó não existir
            if(root == null){
                System.out.println("O nó '" + data + "' não existe na árvore.");
                return false;
            }

            // Caso 1: o nó é folha (subimos para o pai e vemos qual filho temos que remover)
            if(root.isLeaf()) {
                removeLeaf(root);
            }

            // Caso 2: o nó possui um filho
            if(root.getDegree() == 1){
                if (root.getParent().getRight().getData().equals(data)){   // Pai para a direita
                    if(root.getRight() != null){                           // Filho para a direita
                        root.getParent().setRight(root.getRight());
                        root.getRight().setParent(root.getParent());
                    }else{                                                 // Filho para a esquerda
                        root.getParent().setRight(root.getRight());
                        root.getLeft().setParent(root.getParent());
                    }
                }else{                                                    // Pai para a esquerda
                    if(root.getRight() != null){                          // Filho para a direita
                        root.getParent().setLeft(root.getRight());
                        root.getRight().setParent(root.getParent());
                    }else{                                               // Filho para esquerda
                        root.getParent().setLeft(root.getRight());
                        root.getLeft().setParent(root.getParent());
                    }
                }
            }

            // Caso 3: o nó possui dois filhos (usamos o maior dos menores trocamos as informações)
            if(root.getDegree() == 2) {
                Node predecessor = this.findPredecessor(data);
                String dataPredecessor = predecessor.getData();

                predecessor.setData(data);
                root.setData(dataPredecessor);

                // Removemos o nó que agora é uma folha
                removeLeaf(predecessor);
            }
            return true;
        }
        return false;
    }

    public void removeLeaf(Node root) {
        if (root.getParent().getRight().getData().equals(root.getData())) {
            root.getParent().setRight(null);
            root.setParent(null);
        } else {
            root.getParent().setLeft(null);
            root.setParent(null);
        }
    }

    // Encontra o menor valor da árvore
    public Node findMin() {
        if(this.isEmpty()) {
            return null;
        }

        Node node = this.getRoot();
        while (!node.isLeaf()) {
            node = node.getLeft();
        }

        return node;
    }

    // Encontra o maior elemento da árvore
    public Node findMax() {
        if(this.isEmpty()) {
            return null;
        }

        Node node = this.getRoot();
        while (!node.isLeaf()) {
            node = node.getRight();
        }

        return node;
    }

    // Encontra o antecessor de algum elemento
    public Node findPredecessor(String data) {
        Node node = search(root, data);

        if (node != null && node.getLeft() != null) {
            Node predecessor = node.getLeft();
            while (predecessor.getRight() != null) {
                predecessor = predecessor.getRight();
            }
            return predecessor;
        }

        Node aux = root;
        Node predecessor = null;
        while (aux != null) {
            if (data.compareTo(aux.getData()) > 0) {
                predecessor = aux;
                aux = aux.getRight();
            } else if (data.compareTo(aux.getData()) < 0) {
                aux = aux.getLeft();
            } else {
                break;
            }
        }
        return predecessor;
    }

    // Encontra o sucessor de algum elemento
    public Node findSuccessor(String data) {
        Node node = search(root, data);

        if (node != null && node.getRight() != null) {
            Node successor = node.getRight();
            while (successor.getLeft() != null) {
                successor = successor.getLeft();
            }
            return successor;
        }

        Node aux = root;
        Node successor = null;
        while (aux != null) {
            if (data.compareTo(aux.getData()) < 0) {
                successor = aux;
                aux = aux.getLeft();
            } else if (data.compareTo(aux.getData()) > 0) {
                aux = aux.getRight();
            } else {
                break;
            }
        }
        return successor;
    }

    // Remove todos os elementos da árvore
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

    // Imprime as informações de todos os nós da árvore
    public void treeInfo() {
        treeInfo(root);
    }

    public void treeInfo(Node root) {
        if(root.getLeft() != null) {
            treeInfo(root.getLeft());
        }

        System.out.println(root);
        System.out.println("----------------------------------------------------------------------" +
                "--------------------------------------------------------------------");

        if(root.getRight() != null) {
            treeInfo(root.getRight());
        }
    }
}