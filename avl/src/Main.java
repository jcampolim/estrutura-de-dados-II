public class Main {


    public static void main(String[] args) {
<<<<<<< HEAD
        AVL tree1 = new AVL();
        AVL tree2 = new AVL();
        AVL tree3 = new AVL();
        AVL tree4 = new AVL();

        System.out.println("a) Inserir nós com chaves 1, 2 e 3 (nesta sequência).");
        tree1.insert(1);
        tree1.insert(2);
        tree1.insert(3);
        tree1.treeInfo();
        // aplicar rotacoes
        System.out.println("\nDepois de aplicar rotação X");

        tree1.treeInfo();
        System.out.println("\n\n");


        System.out.println("b) Inserir nós com chaves 3, 2 e 1 (nesta sequência).");
        tree2.insert(3);
        tree2.insert(2);
        tree2.insert(1);
        tree2.treeInfo();
        // aplicar rotacoes
        System.out.println("\nDepois de aplicar rotação X");

        tree2.treeInfo();
        System.out.println("\n\n");


        System.out.println("c) Inserir nós com chaves 3, 1 e 2 (nesta sequência).");
        tree3.insert(3);
        tree3.insert(1);
        tree3.insert(2);
        tree3.treeInfo();
        // aplicar rotacoes
        System.out.println("\nDepois de aplicar rotação X");

        tree3.treeInfo();
        System.out.println("\n\n");


        System.out.println("d) Inserir nós com chaves 1, 3 e 2 (nesta sequência).");
        tree4.insert(1);
        tree4.insert(3);
        tree4.insert(2);
        tree4.treeInfo();
        // aplicar rotacoes
        System.out.println("\nDepois de aplicar rotação X");

        tree4.treeInfo();
        System.out.println("\n\n");
=======
        System.out.println("a) Inserir nós com chaves 1, 2 e 3 (nesta sequência).");

        AVL treeA = new AVL();

        treeA.insert(1);
        treeA.insert(2);
        treeA.insert(3);

        System.out.println(" > Antes da rotação: ");
        treeA.treeInfo();

        // rotateLeft
        System.out.println("\n > Depois da rotação: ");
        treeA.treeInfo();

        System.out.println("\nb) Inserir nós com chaves 3, 2 e 1 (nesta sequência).");

        AVL treeB = new AVL();

        treeB.insert(3);
        treeB.insert(2);
        treeB.insert(1);
>>>>>>> 6ffe0d751188eecea6349d502456e6dcd68dd4db

        System.out.println(" > Antes da rotação: ");
        treeB.treeInfo();

        treeB.rotateRight();
        System.out.println("\n > Depois da rotação: ");
        treeB.treeInfo();

        System.out.println("\nc) Inserir nós com chaves 3, 1 e 2 (nesta sequência).");

        AVL treeC = new AVL();

        treeC.insert(3);
        treeC.insert(1);
        treeC.insert(2);

        System.out.println(" > Antes da rotação: ");
        treeC.treeInfo();

        // rotateLeftRight
        System.out.println("\n > Depois da rotação: ");
        treeC.treeInfo();

        System.out.println("\nd) Inserir nós com chaves 1, 3 e 2 (nesta sequência).");

        AVL treeD = new AVL();

        treeD.insert(1);
        treeD.insert(3);
        treeD.insert(2);

        System.out.println(" > Antes da rotação: ");
        treeD.treeInfo();

        treeD.rotateRightLeft();
        System.out.println("\n > Depois da rotação: ");
        treeD.treeInfo();

    }
}