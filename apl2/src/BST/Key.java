package BST;

public class Key extends Node {
    private String identifier;
    private String value;

    public Key() {
        super();
    }

    public Key(String identifier, String value) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
