public class Main {


    public static void main(String[] args) {
        AVL tree = new AVL();

        System.out.println("a) Inserir nós com chaves 1, 2 e 3 (nesta sequência).");
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.treeInfo();
        // aplicar rotacoes

        tree.treeInfo();
        // limpar a arvore
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);

        System.out.println("b) Inserir nós com chaves 3, 2 e 1 (nesta sequência).");
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.treeInfo();
        // aplicar rotacoes

        tree.treeInfo();
        // limpar a arvore
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);

        System.out.println("c) Inserir nós com chaves 3, 1 e 2 (nesta sequência).");
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);
        tree.treeInfo();
        // aplicar rotacoes

        tree.treeInfo();
        // limpar a arvore
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);

        System.out.println("d) Inserir nós com chaves 1, 3 e 2 (nesta sequência).");
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);
        tree.treeInfo();
        // aplicar rotacoes

        tree.treeInfo();
        // limpar a arvore
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);


    }
}