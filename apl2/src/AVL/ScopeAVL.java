package AVL;

public class ScopeAVL extends NodeAVL {
    private String identifier;

    public ScopeAVL() {
        super();
    }

    public ScopeAVL(String identifier) {
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
