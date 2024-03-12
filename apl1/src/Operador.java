public class Operador extends Node {
    private String operator;

    public Operador() {
        super();
    }

    public Operador(Node parent, String operator) {
        super(parent);
        this.operator = operator;
    }

    public void setOperate(String operator) {
        this.operator = operator;
    }

    @Override public String visit() {
        return operator;
    }
}
