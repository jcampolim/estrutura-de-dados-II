package BST;

public class Scope extends Node {
    private String identifier;

    public Scope() {
        super();
    }

    public Scope(String identifier) {
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
