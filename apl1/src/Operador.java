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

    @Override public String visit() {
        return operator;
    }
}


