public class Operando extends Node {
    private float operate;

    // Construtores
    public Operando() {
        super();
    }

    public Operando(Node parent, float operate) {
        super(parent);
        this.operate = operate;
    }

    // Setter
    public void setOperate(float operate) {
        this.operate = operate;
    }

    // Método herdado da super classe que retorna o conteúdo do Node
    @Override public Float visit() {
        return operate;
    }
}
