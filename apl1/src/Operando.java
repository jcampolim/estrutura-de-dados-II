public class Operando extends Node {
    private int operate;

    // Construtores
    public Operando() {
        super();
    }

    public Operando(Node parent, int operate) {
        super(parent);
        this.operate = operate;
    }

    // Setter
    public void setOperate(int operate) {
        this.operate = operate;
    }

    // Método herdado da super classe que retorna o conteúdo do Node
    @Override public Integer visit() {
        return operate;
    }
}
