package serviceImpl;

import daoImpl.BookDao;
import daoImpl.BookDaoImpl;
import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @description:
 * @author:18312
 * @date:2020/11/13 21:57
 */
public class BookServiceImpl implements BookService{
    private BookDao bookDao=new BookDaoImpl();


    @Override
    public void addBook(Book book) {bookDao.addBook(book);}

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBookById(Book book) {bookDao.updateBookByID(book);}

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooksByAuthor(String author) {
        return bookDao.queryBookByAuthor(author);
    }

    @Override
    public List<Book> queryBooks() {return bookDao.queryBooks();}

    @Override
    public Page page(int pageNo, int pageSize) {
        int i = bookDao.queryForPageTotal();
        int begin=(pageNo-1)*pageSize;
        List<Book> books = bookDao.queryForItems(begin, pageSize);
        int pageTotal=i/pageSize;
        if(i%pageSize>0){
            pageTotal+=1;
        }
        Page<Book> bookPage = new Page<Book>(pageNo,pageTotal,pageSize,i,books);
        return bookPage;
    }

    @Override
    public Page pageByPrice(int pageNo,int pageSize,int min,int max){
        int i = bookDao.queryForLimitTotal(min,max);
        int begin=(pageNo-1)*pageSize;
        List<Book> books = bookDao.queryForLimitItems(min,max,begin,pageSize);
        int pageTotal=i/pageSize;
        if(i%pageSize>0){
            pageTotal+=1;
        }
        Page<Book> bookPage = new Page<Book>(pageNo,pageTotal,pageSize,i,books);
        return bookPage;
    }
}
