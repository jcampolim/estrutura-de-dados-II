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

<<<<<<< HEAD

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

=======
    // Método herdado da super classe que retorna o conteúdo do Node
>>>>>>> 1f06ac6b7e4051902486b77ee710e5aa36237cf8
    @Override public String visit() {
        return operator;
    }
}
