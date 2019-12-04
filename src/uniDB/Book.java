package uniDB;

/**
 * @author Tyler Lund
 * This class serves as a constructor for books to be used in the library object
 */
public class Book {
    private String author;
    private String title;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    @Override
    public String toString(){
        return "Title:" + getTitle() + " Author:" + getAuthor();
    }
}
