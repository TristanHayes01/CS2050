public class Album {
    private String title,performer,genre;
    private int amountSongs;

    public Album(){
        this.title = "Renaissance";
        this.performer = "Beyonce";
        this.amountSongs = 10;
        this.genre = "easy listening";
    }
    public Album(String title, String performer, String genre, int amountSongs){
        if(!title.isBlank())
            this.title = title;
        if(!performer.isBlank())
            this. performer = performer;
        if(!genre.isBlank())
            this.genre = genre;
        if(amountSongs>10)
            this.amountSongs = amountSongs;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAmountSongs(int amountSongs) {
        this.amountSongs = amountSongs;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPerformer() {
        return performer;
    }

    public int getAmountSongs() {
        return amountSongs;
    }

    public String toString() {
        return  "Title: " + title +
                "\nPerformer: " + performer +
                "\nGenre: " + genre +
                "\nAmount of Songs: " + amountSongs;
    }
    public boolean isLong(){
        if(amountSongs>50)
            return true;
        return false;
    }
}
