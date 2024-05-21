package AVL;

public class KeyAVL extends NodeAVL {
    private String identifier;
    private String value;

    public KeyAVL() {
        super();
    }

    public KeyAVL(String identifier, String value) {
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
