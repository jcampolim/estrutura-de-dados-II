public class BST extends BinaryTree{
    public BST() {
        super();
    }

    public BST(Node root) {
        super(root);
    }

    public Node search(int data) {
        return search(root, data);
    }

    private Node search(Node root, int data) {
        if (root == null || data == root.getData()) {
            return root;
        } else if (data > root.getData()) {
            return search(root.getLeft(), data);
        } else {
            return search(root.getRight(), data);
        }
    }

    public Node insert(int data) {
        if(isEmpty()) {
            root = new Node(data, null);
            return root;
        } else {
            Node node = new Node(data, null);
            insert(root, node, data);

            return node;
        }
    }

    public void insert(Node root, Node node, int data) {
        if(data < root.getData()) {
            if(root.getLeft() == null) {
                root.setLeft(node);
                node.setParent(root);

                return;
            }

            insert(root.getLeft(), node, data);
        } else if(data > root.getData()) {
            if(root.getRight() == null) {
                root.setRight(node);
                node.setParent(root);

                return;
            }

            insert(root.getRight(), node, data);
        } else if(data == root.getData()) {
            return;
        }
    }

    public boolean remove(int data) {
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
                if (root.getParent().getRight().getData() == data){   // Pai para a direita
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
        if (root.getParent().getRight().getData() == root.getData()) {
            root.getParent().setRight(null);
            root.setParent(null);
        } else {
            root.getParent().setLeft(null);
            root.setParent(null);
        }
    }

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

    public Node findPredecessor(int data) {
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
            if (data > aux.getData()) {
                predecessor = aux;
                aux = aux.getRight();
            } else if (data < aux.getData()) {
                aux = aux.getLeft();
            } else {
                break;
            }
        }
        return predecessor;
    }

    public Node findSuccessor(int data) {
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
            if (data < aux.getData()) {
                successor = aux;
                aux = aux.getLeft();
            } else if (data > aux.getData()) {
                aux = aux.getRight();
            } else {
                break;
            }
        }
        return successor;
    }

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
        root.setData(0);
    }

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
