// Nome / RA:
// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste- 10408802

//Fonte utilizada para pesquisa:
//https://profkishimoto.github.io/edii04g11-2024-1/

package Revisao;

public class Movie {
	//Atributos da classe Movie
    private String title;
    private int year;
    private double score;
    private Movie next;

    //Construtor da classe Movie que não recebe nenhum parâmetro
    //Inicializa os atributos title com N/A, year com 0 e score com 0
    public Movie() {
        title = "N/A";
        year = 0;
        score = 0;
    }

    //Construtor da classe Movie que recebe os atributos title, year e score como parâmetros
    //Referencia os atributos externos title, year e score
    //E faz com que eles recebam os atributos title, year e score do próprio construtor
    public Movie(String title, int year, double score) {
        this.title = title;
        this.year = year;
        this.score = score;
    }

    //Método SET para alterar o atributo title
    public void setTitle(String title) {
        this.title = title;
    }

    //Método GET para consultar o atributo title
    public String getTitle() {
        return title;
    }

    //Método SET para alterar o atributo year
    public void setYear(int year) {
        this.year = year;
    }
    
   //Método GET para consultar o atributo year
    public int getYear() {
        return year;
    }

    //Método SET para alterar o atributo score
    public void setScore(double score) {
        this.score = score;
    }

    //Método GET para consultar o atributo score
    public double getScore() {
        return score;
    }

    //Método SET para alterar o atributo next
    //Recebe o atributo movie do tipo Movie como parâmetro
    public void setNext(Movie movie) {
        next = movie;
    }

    //Método GET para consultar o atributo next
    public Movie getNext() {
        return next;
    }

    //Método para transformar a saída em uma única String
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s (%d) %.1f\n", title, year, score));
        return str.toString();
    }
}