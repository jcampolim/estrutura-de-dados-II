public class AVL extends BST {
    // TODO: construtores

    // TODO: método ll (rotateLeft) - erika

    // TODO: método rr (rotateRight) - júlia

    // TODO: método lr (rotateLeftRight) - erika

    // TODO: método rl (rotateRightLeft) - júlia

    // TODO: update do f

    // TODO: update fator de balanciamento

    public Node insert(int data){
        Node insertedNode = super.insert(data);
        insertedNode.updateBalanceFactor();
        return insertedNode;
    }
}
