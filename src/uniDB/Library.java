package uniDB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Tyler Lund
 * organizes and manages how users can interact with Book objects
 */
public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private Map<Book, student> checkedOut = new HashMap<>();
    private Map<Book, Date> returnList = new HashMap<>();
    private double lateFee = 5.00;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private Calendar currentCalendar = Calendar.getInstance();
    private Calendar returnCalendar = Calendar.getInstance();

    public Library(){

    }

    /**
     * method that assigns a user a book and gives a date to return by
     * @param s
     * @param book
     * @return
     */
    public String checkOutBook(student s, Book book){
        if(checkedOut.containsKey(book)){
            return "Book has already been checked out";
        }
        else{
            returnCalendar.setTime(new Date());
            checkedOut.put(book, s);
            returnCalendar.add(Calendar.DAY_OF_MONTH, 7);
            returnList.put(book, returnCalendar.getTime());
            return "Student " + s.getFullname() + " has checked out " + book.getTitle() + " due back by " + dateFormat.format(returnCalendar.getTime());
        }
    }

    /**
     * method that allows a user to return a book that has been checkedOut and readds that book to the available list to check out
     * @param s
     * @param book
     * @return
     */
    public String checkInBook(student s, Book book){
        if(checkedOut.containsKey(book)){
            if(checkedOut.get(book).getUsername().trim().equalsIgnoreCase(s.getUsername().trim())){
                checkedOut.remove(book);
                return "student " + s.getFullname() + " has returned " + book.getTitle();
            }
            else{
                return "student " + s.getFullname() + " has not checked out that book and cannot check it in";
            }
        }
        return "Book "  + book.getTitle() + " is not currently checked out and cannot be checked in";
    }

    /**
     * method that determines if a book has been out longer than its due date and returns the cooresponding student that was issued the book
     * @return
     */
    public Map<Book,student> checkForLate(){
        currentCalendar.setTime(new Date());
        Map<Book,student> overdue = new HashMap<>();
        for(Map.Entry<Book, Date> entry: returnList.entrySet()){
            if(entry.getValue().before(currentCalendar.getTime())){
               overdue.put(entry.getKey(), checkedOut.get(entry.getKey()));
            }
        }
        return overdue;
    }

    /**
     * returns an instance of a book if that book is a part of the Book list
     * @param title
     * @param author
     * @return
     */
    public Book findBook(String title, String author){
        for(Book x: books){
            if(x.getTitle().trim().equalsIgnoreCase(title.trim()) && x.getAuthor().trim().equalsIgnoreCase(author.trim())){
                return x;
            }
        }
        return null;
    }

    /**
     * adds a book object to the Book list
     * @param book
     */
    public void addBook(Book book){
        books.add(book);
    }

    /**
     * removes a book object from the Book list
     * @param book
     */
    public void removeBook(Book book){
        books.remove(book);
    }

    /**
     * returns a list of all the book objects in Book list in string format
     * @return
     */
    public String getBooks(){
        String temp = "";
        for(Book x: books){
            temp += x.toString() + "\n";
        }
        return temp;
    }
}
