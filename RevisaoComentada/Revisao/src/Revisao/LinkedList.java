// Nome / RA:
// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste- 10408802

//Fonte utilizada para pesquisa:
//https://profkishimoto.github.io/edii04g11-2024-1/

package Revisao;

public class LinkedList {
	//Atributos da LinkedList: Head aponta para o começo,
	//tail aponta para o final e next aponta para o próximo
    private Movie head;
    private Movie tail;
    private Movie next;

    //Construtor da classe LinkedLista que não recebe nenhum parâmetro
    //Inicializa os atributos head, tail e next como null
    public LinkedList() {
        head = null;
        tail = null;
        next = null;
    }

    //Método para inserir filmes na lista
    //Se ela estiver vazia, o head e o tail apontam para o filme
    //Se não, o próximo do tail aponta para o novo filme inserido e o tail passa a ser o novo filme inserido
    public void insertMovie(Movie movie) {
        if(head == null) {
            head = movie;
            tail = movie;
        } else {
            tail.setNext(movie);
            tail = movie;
        }
    }

    //Método para printar os filmes da lista
    //Inicializa um contador com 1 e uma variável tipo Movie chamada aux com o head
    //Vai imprimindo os filmes da lista enquanto a variável aux for diferente de null
    public void print() {
        int count = 1;

        Movie aux = head;
        do {
            System.out.printf("#%d %s", count, aux.toString());
            aux = aux.getNext();
            count++;
        } while(aux != null);
    }
}