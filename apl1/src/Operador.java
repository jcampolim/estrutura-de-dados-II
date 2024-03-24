public class Operador extends Node {
    private String operator;

    // Construtores
    public Operador() {
        super();
    }

    public Operador(Node parent, String operator) {
        super(parent);
        this.operator = operator;
    }

    // Setter
    public void setOperate(String operator) {
        this.operator = operator;
    }

    // Método herdado da super classe que retorna o conteúdo do Node
    @Override public String visit() {
        return operator;
    }
}
