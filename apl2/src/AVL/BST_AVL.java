package AVL;

// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
// https://www.javatpoint.com/java-tokens
// https://www.devmedia.com.br/tokenizacao-com-scanner-em-java/26508
// SZWARCFITER, J.L.; MARKENZON, L. Estruturas de Dados e seus Algoritmos. 3ª. ed. Rio de Janeiro: LTC, 2010.

import BST.NodeBST;

import java.util.List;

public class BST_AVL extends BinaryTree {

    // Construtor
    public BST_AVL() {
        super();
    }

    public BST_AVL(NodeAVL root) {
        super(root);
    }

    // Procura um nó na árvore pela chave
    public int searchAVL(String identifier, List<NodeAVL> listNodes) {
        return searchAVL(root, identifier, listNodes);
    }

    private int searchAVL(NodeAVL root, String identifier, List<NodeAVL> listNodes) {
        int comp = 0;
        if(root != null) {
            if (identifier.compareTo(root.getIdentifier()) < 0) {
                comp = searchAVL(root.getLeft(), identifier, listNodes);
                comp++;
            } else if(identifier.compareTo(root.getIdentifier()) > 0 || identifier.compareTo(root.getIdentifier()) == 0) {
                comp = searchAVL(root.getRight(), identifier, listNodes);
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
    public NodeAVL insertBST(NodeAVL newNode) {
        if(isEmpty()) {
            root = newNode;
            return root;
        } else {
            insertBST(root, newNode);
            return newNode;
        }
    }

    public void insertBST(NodeAVL root, NodeAVL node) {
        if(node.getIdentifier().compareTo(root.getIdentifier()) < 0) {
            if(root.getLeft() == null) {
                root.setLeft(node);
                node.setParent(root);

                return;
            }

            insertBST(root.getLeft(), node);
        } else {
            if(root.getRight() == null) {
                root.setRight(node);
                node.setParent(root);

                return;
            }

            insertBST(root.getRight(), node);
        }
    }

    public boolean updateBST(String oldKey, String newKey) {
        NodeAVL updateNode = search(root, oldKey);
    
        if (updateNode != null) {
    
            updateNode.setIdentifier(newKey);
            return true;
        }
    
        return false;
    }

    private NodeAVL search(NodeAVL root, String identifier) {
        if (root == null || identifier.equals(root.getIdentifier())) {
            return root;
        } else if (identifier.compareTo(root.getIdentifier()) < 0) {
            return search(root.getLeft(), identifier);
        } else {
            return search(root.getRight(), identifier);
        }
    }

    // Remove uma chave da árvore
    public boolean remove(NodeAVL root) {
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
                NodeAVL predecessor = this.findPredecessor(root.getIdentifier());
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

    public void removeLeaf(NodeAVL root) {
        if (root.getParent().getRight().getIdentifier().equals(root.getIdentifier())) {
            root.getParent().setRight(null);
            root.setParent(null);
        } else {
            root.getParent().setLeft(null);
            root.setParent(null);
        }
    }

    // Encontra o menor valor da árvore
    public NodeAVL findMin() {
        if(this.isEmpty()) {
            return null;
        }

        NodeAVL node = this.getRoot();
        while (!node.isLeaf()) {
            node = node.getLeft();
        }

        return node;
    }

    // Encontra o maior elemento da árvore
    public NodeAVL findMax() {
        if(this.isEmpty()) {
            return null;
        }

        NodeAVL node = this.getRoot();
        while (!node.isLeaf()) {
            node = node.getRight();
        }

        return node;
    }

    // Encontra o antecessor de algum elemento
    public NodeAVL findPredecessor(String identifier) {
        NodeAVL node = search(root, identifier);

        if (node != null && node.getLeft() != null) {
            NodeAVL predecessor = node.getLeft();
            while (predecessor.getRight() != null) {
                predecessor = predecessor.getRight();
            }
            return predecessor;
        }

        NodeAVL aux = root;
        NodeAVL predecessor = null;
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
    public NodeAVL findSuccessor(String identifier) {
        NodeAVL node = search(root, identifier);

        if (node != null && node.getRight() != null) {
            NodeAVL successor = node.getRight();
            while (successor.getLeft() != null) {
                successor = successor.getLeft();
            }
            return successor;
        }

        NodeAVL aux = root;
        NodeAVL successor = null;
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

    public void clear(NodeAVL root) {
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

    public void inOrder(NodeAVL root) {
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

    public void preOrder(NodeAVL root) {
        System.out.println(root);
        System.out.println("----------------------------------------------------------------------" +
                "--------------------------------------------------------------------");

        if(root.getLeft() != null) {
            inOrder(root.getLeft());
        }

        if(root.getRight() != null) {
            inOrder(root.getRight());
        }
    }

    public void posOrder() {
        posOrder(root);
    }

    public void posOrder(NodeAVL root) {
        if(root.getLeft() != null) {
            inOrder(root.getLeft());
        }

        if(root.getRight() != null) {
            inOrder(root.getRight());
        }

        System.out.println(root);
        System.out.println("----------------------------------------------------------------------" +
                "--------------------------------------------------------------------");
    }

    public int qtdeNodes(NodeAVL root) {
        if(root == null) {
            return 0;
        }

        return 1 + qtdeNodes(root.getLeft()) + qtdeNodes(root.getRight());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Grau da árvore: " + getDegree());
        sb.append(" - Altura da árvore: " + getHeight());
        sb.append(" - Quantidade de nós: " + qtdeNodes(root));

        return sb.toString();
    }
}