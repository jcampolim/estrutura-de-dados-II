public class Main {
    public static void main(String[] args) {
        Node a = new Node();
        Node b = new Node();
        Node c = new Node();
        Node d = new Node();
        Node f = new Node();
        Node e = new Node();
        Node h = new Node();
        Node i = new Node();
        Node g = new Node();

        BinaryTree t = new BinaryTree();
        t.setRoot(f);

        f.setLeft(b);
        f.setRight(g);
        f.setData("F");

        b.setParent(f);
        b.setLeft(a);
        b.setRight(d);
        b.setData("B");

        a.setParent(b);
        a.setData("A");

        d.setParent(b);
        d.setLeft(c);
        d.setRight(e);
        d.setData("D");

        c.setParent(d);
        c.setData("C");

        e.setParent(d);
        e.setData("E");

        g.setParent(f);
        g.setRight(i);
        g.setData("G");

        i.setParent(g);
        i.setLeft(h);
        i.setData("I");

        h.setParent(i);
        h.setData("H");

        System.out.print("Pre order: ");
        t.preOrderTraversal();

        System.out.print("\nIn order: ");
        t.inOrderTraversal();

        System.out.print("\nPos order: ");
        t.posOrderTraversal();

        System.out.print("\nLevel order: ");
        t.levelOrderTraversal();
    }
}
