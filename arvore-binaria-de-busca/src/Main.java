public class Main {
    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert("08");
        bst.insert("03");
        bst.insert("01");
        bst.insert("06");
        bst.insert("04");
        bst.insert("07");
        bst.insert("10");
        bst.insert("14");

        bst.inOrderTraversal();

        bst.clear();
        bst.inOrderTraversal();
    }
}