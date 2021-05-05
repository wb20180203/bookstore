import daoImpl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;
import serviceImpl.BookServiceImpl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author:18312
 * @date:2020/11/14 9:51
 */
public class BookServiceImplTest {
    private BookServiceImpl bookService=new BookServiceImpl();
    @Test
    public void test1(){
        bookService.addBook(new Book(1,"123","123",new BigDecimal(123),123,123,"static/img/default.jpg"));
    }
    @Test
    public void test2(){
        bookService.deleteBookById(22);
    }

    @Test
    public void test3(){
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> books = bookDao.queryForItems(0, 4);
        for(Book b:books){
            System.out.println(b);
        }
    }

    @Test
    public void test4(){
        BookDaoImpl bookDao = new BookDaoImpl();
        int i = bookDao.queryForPageTotal();
        System.out.println(i);
    }

    @Test
    public void test5(){
        BookDaoImpl bookDao = new BookDaoImpl();
        int i = bookDao.queryForLimitTotal(30, 100);
        System.out.println(i);
    }

    @Test
    public void test6(){
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> books = bookDao.queryForLimitItems(30, 100, 2, 5);
        Iterator<Book> it = books.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
