package BST;

import java.util.ArrayList;

public class Scope extends Node {
    private String identifier;

    public Scope() {
        super();
    }

    public Scope(String identifier) {
        super();
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
