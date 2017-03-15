package service.book;

import model.Book;
import model.Student;

import java.util.List;

/**
 * Created by Vadim on 14.03.2017.
 */
interface IBookService {

    void saveBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    void giveBook(Book book, Student student);

    void takeBook(Book book);

    List<Book> getAllBooks();
    List<Book> getAvailableBooks();
    List<Book> getNotAvailableBooks();

    Book getBookById(int id);

}
