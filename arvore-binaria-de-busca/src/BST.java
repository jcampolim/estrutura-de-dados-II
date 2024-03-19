//Nomes:
// - Enzo Guarnieri, 10410074
// - Erika Borges Piaui, 10403716
// - Júlia Campolim de Oste, 10408802
//Fontes:
// - https://sentry.io/answers/how-to-compare-strings-in-java/#:~:text=If%20you%20want%20to%20find,compareTo()%20.
// - https://profkishimoto.github.io/edii04g11-2024-1/

public class BST extends BinaryTree {
    public BST() {
        super();
    }

    public BST(Node root) {
        super(root);
    }


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


    public boolean remove(String data) {
        if(this.isEmpty()) return false;
        Node remove = this.search(data);
        //Se nao existir o no
        if(remove == null){
            System.out.println("Nó não existe na arvore.");
            return false;
        }
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
        // Usando o antecessor (maior dos menores)
        // Trocamos as informacoes
        Node antecessor = this.findPredecessor(data);
        String aux = antecessor.getData();
        antecessor.setData(remove.getData());
        remove.setData(aux);
        // Removemos agora que data é folha
        if (antecessor.getParent().getRight().getData().equals(data)) {
            antecessor.getParent().setRight(null);
            antecessor.setParent(null);
        } else {
            antecessor.getParent().setLeft(null);
            antecessor.setParent(null);
        }
        return true;

    }


    public Node findMin() {
        if(this.isEmpty()) return null;
        Node node = this.getRoot();
        while (!node.isLeaf()) node = node.getLeft();
        return node;
    }


    public Node findMax() {
        if(this.isEmpty()) return null;
        Node node = this.getRoot();
        while (!node.isLeaf()) node = node.getRight();
        return node;
    }
   

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
