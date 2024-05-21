package BST;

public class KeyBST extends NodeBST {
    private String identifier;
    private String value;

    public KeyBST() {
        super();
    }

    public KeyBST(String identifier, String value) {
        super();
        this.identifier = identifier;
        this.value = value;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
