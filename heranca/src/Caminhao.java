// Nome / RA:
// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste- 10408802

// Fontes utilizadas para esse trabalho:
// Material disponibilizado pelo professor.
// e os seguintes sites;
// - https://www.devmedia.com.br/trabalhando-com-arrays-em-java/25530
// - https://pt.stackoverflow.com/questions/3905/como-comparar-strings-em-java

public class Caminhao extends VeiculoTerrestre{
    private int totalDeEixos;

    //Construtores
    public Caminhao(){
        super();
        totalDeEixos = 0;

    }
    
    public Caminhao(int ano, String cor, int totalDeEixos){
        super(ano, cor);
        this.totalDeEixos = totalDeEixos;
    }

    //Getters e Setters
    public int getTotalDeEixos() {
        return totalDeEixos;
    }
    public void setTotalDeEixos(int totalDeEixos) {
        this.totalDeEixos = totalDeEixos;
    }

    // Override do metodo toString
    public String toString() {
        return "Carro de Passeio: ano " + super.getAno() + "," + " cor " + super.getCor() + "," + " Eixos " + totalDeEixos;
    }
}
