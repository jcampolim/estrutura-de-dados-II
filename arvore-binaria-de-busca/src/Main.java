public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        BST teste = new BST();
        teste.remove("a");

        bst.insert("08");
        bst.insert("03");
        bst.insert("01");
        bst.insert("06");
        bst.insert("04");
        bst.insert("07");
        bst.insert("10");
        bst.insert("14");

        System.out.println(bst.findMin().getData());
        System.out.println(bst.findMax().getData());

        bst.inOrderTraversal();

        bst.clear();
        bst.inOrderTraversal();
    }
}