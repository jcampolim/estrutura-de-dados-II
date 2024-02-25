public class LinkedList {
    private Movie head;
    private Movie tail;
    private Movie next;

    public LinkedList() {
        head = null;
        tail = null;
        next = null;
    }

    public void insertMovie(Movie movie) {
        if(head == null) {
            head = movie;
            tail = movie;
        } else {
            tail.setNext(movie);
            tail = movie;
        }
    }

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
