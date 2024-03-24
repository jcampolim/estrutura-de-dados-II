// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// https://www.geeksforgeeks.org/overriding-in-java/

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
