public class Operando extends Node {
    private String operate;

    public Operando() {
        super();
    }

    public Operando(Node parent, int operate) {
        super(parent);
        this.operate = Integer.toString(operate);
    }

    public void setOperate(int operate) {
        this.operate = Integer.toString(operate);
    }

    @Override public String visit() {
        return operate;
    }
}
