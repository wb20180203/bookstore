package daoImpl;

import org.junit.Test;
import pojo.Book;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @description:
 * @author:18312
 * @date:2020/11/14 9:27
 */
public class BookDaoTest {
    private BookDaoImpl bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        Book book = new Book(1, "123", "123", new BigDecimal(123), 1234, 123, "static/img/default.jpg");
        System.out.println(bookDao.addBook(book));
    }

    @Test
    public void deleteBookById() {
        System.out.println(bookDao.deleteBookById(21));
    }

    @Test
    public void deleteBookByName() {
    }

    @Test
    public void updateBookByID() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBookByAuthor() {
    }

    @Test
    public void queryBooks() {
    }
}