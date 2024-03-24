// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// https://www.geeksforgeeks.org/overriding-in-java/

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

    // Calcula a operação utilizando os nós filhos
    public void operate(){
        Float num = 0.0F;
        Float n1 = Float.valueOf(this.getLeft().visit().toString());
        Float n2 = Float.valueOf(this.getRight().visit().toString());

        if(this.visit().equals("+")){
            num = n1 + n2;
        } else if(this.visit().equals("-")) {
            num = n1 - n2;
        }else if(this.visit().equals("*")) {
            num = n1 * n2;
        }else if(this.visit().equals("/")) {
            num = n1 / n2;
        }
        this.setOperate(num.toString());
    }

    // Método herdado da super classe que retorna o conteúdo do Node
    @Override public String visit() {
        return operator;
    }
}
