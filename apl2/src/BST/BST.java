package BST;

import BST.NodeBST;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST extends BinaryTree {

    // Construtor
    public BST() {
        super();
    }

    public BST(NodeBST root) {
        super(root);
    }


    // Procura um nó na árvore pela chave
    public int searchBST(String identifier, List<NodeBST> listNodes) {
        return searchBST(root, identifier, listNodes);
    }

    private int searchBST(NodeBST root, String identifier, List<NodeBST> listNodes) {
        int comp = 0;
        if(root != null) {
            if (identifier.compareTo(root.getIdentifier()) < 0) {
                comp = searchBST(root.getLeft(), identifier, listNodes);
                comp++;
            } else if(identifier.compareTo(root.getIdentifier()) > 0 || identifier.compareTo(root.getIdentifier()) == 0) {
                comp = searchBST(root.getRight(), identifier, listNodes);
                comp++;
            }
            if (identifier.equals(root.getIdentifier())) {
                listNodes.add(root);
                comp++;
            }
        }
        return comp;
    }

    // Insere uma nova chave na árvore
    public NodeBST insert(NodeBST newNode) {
        if(isEmpty()) {
            root = newNode;
            return root;
        } else {
            insert(root, newNode);

            return newNode;
        }
    }

    public void insert(NodeBST root, NodeBST node) {
        if(node.getIdentifier().compareTo(root.getIdentifier()) < 0) {
            if(root.getLeft() == null) {
                root.setLeft(node);
                node.setParent(root);

                return;
            }

            insert(root.getLeft(), node);
        } else {
            if(root.getRight() == null) {
                root.setRight(node);
                node.setParent(root);

                return;
            }

            insert(root.getRight(), node);
        }
    }

    private NodeBST search(NodeBST root, String identifier) {
        if (root == null || identifier.equals(root.getIdentifier())) {
            return root;
        } else if (identifier.compareTo(root.getIdentifier()) < 0) {
            return search(root.getLeft(), identifier);
        } else {
            return search(root.getRight(), identifier);
        }
    }

    // Remove uma chave da árvore
    public boolean remove(NodeBST root) {
        if(!this.isEmpty()) {
            // Caso 1: o nó é folha (subimos para o pai e vemos qual filho temos que remover)
            if(root.isLeaf()) {
                removeLeaf(root);
            }

            // Caso 2: o nó possui um filho
            if(root.getDegree() == 1){
                if (root.getParent().getRight().getIdentifier().equals(root.getIdentifier())){   // Pai para a direita
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
                NodeBST predecessor = this.findPredecessor(root.getIdentifier());
                String dataPredecessor = predecessor.getIdentifier();

                predecessor.setIdentifier(root.getIdentifier());
                root.setIdentifier(dataPredecessor);

                // Removemos o nó que agora é uma folha
                removeLeaf(predecessor);
            }
            return true;
        }
        return false;
    }

    public void removeLeaf(NodeBST root) {
        if (root.getParent().getRight().getIdentifier().equals(root.getIdentifier())) {
            root.getParent().setRight(null);
            root.setParent(null);
        } else {
            root.getParent().setLeft(null);
            root.setParent(null);
        }
    }

    // Encontra o menor valor da árvore
    public NodeBST findMin() {
        if(this.isEmpty()) {
            return null;
        }

        NodeBST node = this.getRoot();
        while (!node.isLeaf()) {
            node = node.getLeft();
        }

        return node;
    }

    // Encontra o maior elemento da árvore
    public NodeBST findMax() {
        if(this.isEmpty()) {
            return null;
        }

        NodeBST node = this.getRoot();
        while (!node.isLeaf()) {
            node = node.getRight();
        }

        return node;
    }

    // Encontra o antecessor de algum elemento
    public NodeBST findPredecessor(String identifier) {
        NodeBST node = search(root, identifier);

        if (node != null && node.getLeft() != null) {
            NodeBST predecessor = node.getLeft();
            while (predecessor.getRight() != null) {
                predecessor = predecessor.getRight();
            }
            return predecessor;
        }

        NodeBST aux = root;
        NodeBST predecessor = null;
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
    public NodeBST findSuccessor(String identifier) {
        NodeBST node = search(root, identifier);

        if (node != null && node.getRight() != null) {
            NodeBST successor = node.getRight();
            while (successor.getLeft() != null) {
                successor = successor.getLeft();
            }
            return successor;
        }

        NodeBST aux = root;
        NodeBST successor = null;
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

    public void clear(NodeBST root) {
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
    public void inOrder() {
        inOrder(root);
    }

    public void inOrder(NodeBST root) {
        if(root.getLeft() != null) {
            inOrder(root.getLeft());
        }

        System.out.println(root);
        System.out.println("----------------------------------------------------------------------" +
                "--------------------------------------------------------------------");

        if(root.getRight() != null) {
            inOrder(root.getRight());
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    public void preOrder(NodeBST root) {
        System.out.println(root);
        System.out.println("----------------------------------------------------------------------" +
                "--------------------------------------------------------------------");

        if(root.getLeft() != null) {
            preOrder(root.getLeft());
        }

        if(root.getRight() != null) {
            preOrder(root.getRight());
        }
    }

    public void posOrder() {
        posOrder(root);
    }

    public void posOrder(NodeBST root) {
        if(root.getLeft() != null) {
            posOrder(root.getLeft());
        }

        if(root.getRight() != null) {
            posOrder(root.getRight());
        }

        System.out.println(root);
        System.out.println("----------------------------------------------------------------------" +
                "--------------------------------------------------------------------");
    }
    public Queue<NodeBST> toQueue() {
        Queue<NodeBST> queue = new LinkedList<>();
        inOrderTraversal(root, queue);
        return queue;
    }

    // Função para realizar a travessia in-order e adicionar os elementos na fila
    private void inOrderTraversal(NodeBST node, Queue<NodeBST> queue) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), queue);
            queue.add(node);
            inOrderTraversal(node.getRight(), queue);
        }
    }
}
