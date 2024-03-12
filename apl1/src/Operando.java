public class Operando extends Node {
    private int operate;

    public Operando() {
        super();
    }

    public Operando(Node parent, int operate) {
        super(parent);
        this.operate = operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    @Override public Integer visit() {
        return operate;
    }
}
