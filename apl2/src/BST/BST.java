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
    public Node search(String identifier) {
        return search(root, identifier);
    }

    private Node search(Node root, String identifier) {
        if (root == null || identifier.equals(root.getIdentifier())) {
            return root;
        } else if (identifier.compareTo(root.getIdentifier()) < 0) {
            return search(root.getLeft(), identifier);
        } else {
            return search(root.getRight(), identifier);
        }
    }

    // Insere uma nova chave na árvore
    public Node insert(Node newNode) {
        if(isEmpty()) {
            root = newNode;
            return root;
        } else {
            insert(root, newNode);

            return newNode;
        }
    }

    public void insert(Node root, Node node) {
        if(node.getIdentifier().compareTo(root.getIdentifier()) < 0) {
            if(root.getLeft() == null) {
                root.setLeft(node);
                node.setParent(root);

                return;
            }

            insert(root.getLeft(), node);
        } else if(node.getIdentifier().compareTo(root.getIdentifier()) > 0) {
            if(root.getRight() == null) {
                root.setRight(node);
                node.setParent(root);

                return;
            }

            insert(root.getRight(), node);
        } else if(node.getIdentifier().compareTo(root.getIdentifier()) == 0) {
            return;
        }
    }

    // Remove uma chave da árvore
    public boolean remove(String identifier) {
        if(!this.isEmpty()) {
            Node root = this.search(identifier);

            // Se o nó não existir
            if(root == null){
                System.out.println("O nó '" + identifier + "' não existe na árvore.");
                return false;
            }

            // Caso 1: o nó é folha (subimos para o pai e vemos qual filho temos que remover)
            if(root.isLeaf()) {
                removeLeaf(root);
            }

            // Caso 2: o nó possui um filho
            if(root.getDegree() == 1){
                if (root.getParent().getRight().getIdentifier().equals(identifier)){   // Pai para a direita
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
                Node predecessor = this.findPredecessor(identifier);
                String dataPredecessor = predecessor.getIdentifier();

                predecessor.setIdentifier(identifier);
                root.setIdentifier(dataPredecessor);

                // Removemos o nó que agora é uma folha
                removeLeaf(predecessor);
            }
            return true;
        }
        return false;
    }

    public void removeLeaf(Node root) {
        if (root.getParent().getRight().getIdentifier().equals(root.getIdentifier())) {
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
    public Node findPredecessor(String identifier) {
        Node node = search(root, identifier);

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
            if (identifier.compareTo(aux.getIdentifier()) > 0) {
                predecessor = aux;
                aux = aux.getRight();
            } else if (identifier.compareTo(aux.getIdentifier()) < 0) {
                aux = aux.getLeft();
            } else {
                break;
            }
        }
        return predecessor;
    }

    // Encontra o sucessor de algum elemento
    public Node findSuccessor(String identifier) {
        Node node = search(root, identifier);

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
            if (identifier.compareTo(aux.getIdentifier()) < 0) {
                successor = aux;
                aux = aux.getLeft();
            } else if (identifier.compareTo(aux.getIdentifier()) > 0) {
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
        root.setIdentifier(null);
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