// Nome / RA:
// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// J�lia Campolim de Oste- 10408802

//Fonte utilizada para pesquisa:
//https://profkishimoto.github.io/edii04g11-2024-1/

package Revisao;

public class Movie {
	//Atributos da classe Movie
    private String title;
    private int year;
    private double score;
    private Movie next;

    //Construtor da classe Movie que n�o recebe nenhum par�metro
    //Inicializa os atributos title com N/A, year com 0 e score com 0
    public Movie() {
        title = "N/A";
        year = 0;
        score = 0;
    }

    //Construtor da classe Movie que recebe os atributos title, year e score como par�metros
    //Referencia os atributos externos title, year e score
    //E faz com que eles recebam os atributos title, year e score do pr�prio construtor
    public Movie(String title, int year, double score) {
        this.title = title;
        this.year = year;
        this.score = score;
    }

    //M�todo SET para alterar o atributo title
    public void setTitle(String title) {
        this.title = title;
    }

    //M�todo GET para consultar o atributo title
    public String getTitle() {
        return title;
    }

    //M�todo SET para alterar o atributo year
    public void setYear(int year) {
        this.year = year;
    }
    
   //M�todo GET para consultar o atributo year
    public int getYear() {
        return year;
    }

    //M�todo SET para alterar o atributo score
    public void setScore(double score) {
        this.score = score;
    }

    //M�todo GET para consultar o atributo score
    public double getScore() {
        return score;
    }

    //M�todo SET para alterar o atributo next
    //Recebe o atributo movie do tipo Movie como par�metro
    public void setNext(Movie movie) {
        next = movie;
    }

    //M�todo GET para consultar o atributo next
    public Movie getNext() {
        return next;
    }

    //M�todo para transformar a sa�da em uma �nica String
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s (%d) %.1f\n", title, year, score));
        return str.toString();
    }
}