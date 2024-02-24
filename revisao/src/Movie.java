public class Movie {
    private String title;
    private int year;
    private double score;
    private Movie next;

    public Movie() {
        title = "N/A";
        year = 0;
        score = 0;
    }

    public Movie(String title, int year, double score) {
        this.title = title;
        this.year = year;
        this.score = score;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setNext(Movie movie) {
        next = movie;
    }

    public Movie getNext() {
        return next;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s (%d) %.1f\n", title, year, score));
        return str.toString();
    }
}
