// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Material disponibilizado pelo professor
// https://profkishimoto.github.io/edii04g11-2024-1/

public class Main {
    public static void main(String[] args) {
        System.out.println("a) Inserir nós com chaves 1, 2 e 3 (nesta sequência).");
        // Cria a árvore do exercício a) e insere os valores
        AVL treeA = new AVL();
        treeA.insert(1);
        treeA.insert(2);
        treeA.insert(3);
        treeA.treeInfo();


        System.out.println("\nb) Inserir nós com chaves 3, 2 e 1 (nesta sequência).");
        // Cria a árvore do exercício b) e insere os valores
        AVL treeB = new AVL();
        treeB.insert(3);
        treeB.insert(2);
        treeB.insert(1);
        treeB.treeInfo();


        System.out.println("\nc) Inserir nós com chaves 3, 1 e 2 (nesta sequência).");
        // Cria a árvore do exercício c) e insere os valores
        AVL treeC = new AVL();
        treeC.insert(3);
        treeC.insert(1);
        treeC.insert(2);
        treeC.treeInfo();


        System.out.println("\nd) Inserir nós com chaves 1, 3 e 2 (nesta sequência).");
        // Cria a árvore do exercício d) e insere os valores
        AVL treeD = new AVL();
        treeD.insert(1);
        treeD.insert(3);
        treeD.insert(2);
        treeD.treeInfo();



        System.out.println("\ne) Inserir nós com chaves 5, 4, 3, 1, 2, 6, 7, 9 e 8 (nesta sequência).");
        // Cria a árvore do exercício d) e insere os valores
        AVL treeE = new AVL();
        treeE.insert(5);
        treeE.insert(4);
        treeE.insert(3);
        treeE.insert(1);
        treeE.insert(2);
        treeE.insert(6);
        treeE.insert(7);
        treeE.insert(9);
        treeE.insert(8);
        treeE.treeInfo();


        System.out.println("\nf) Remover o nó 4 da árvore do item (e).");
        treeE.remove(4);
        treeE.treeInfo();

        System.out.println("\nf) Remover o nó 5 da árvore do item (f).");
        treeE.remove(5);
        treeE.treeInfo();

        System.out.println("\nf) Remover o nó 1 da árvore do item (g).");
        treeE.remove(1);
        treeE.treeInfo();










    }
}
