package BST;

public class ScopeBST extends NodeBST {
    private String identifier;

    public ScopeBST() {
        super();
    }

    public ScopeBST(String identifier) {
        super();
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
