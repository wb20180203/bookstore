package serviceImpl;

import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @description:
 * @author:18312
 * @date:2020/11/13 21:48
 */
public interface BookService {
    /**
     * 添加图书
     * @param book
     */
    void addBook(Book book);

    /**
     * 删除图书
     * @param id
     */
    void deleteBookById(Integer id);

    /**
     * 更新图书
     * @param book
     */
    void updateBookById(Book book);

    /**
     * 通过ID查询图书
     * @param id
     * @return
     */
    Book queryBookById(Integer id);

    /**
     * 通过作者名查询图书
     * @param author
     * @return
     */
    List<Book> queryBooksByAuthor(String author);

    /**
     * 查询全部图书
     * @return
     */
    List<Book> queryBooks();

    /**
     * 查询page对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page page(int pageNo,int pageSize);

    /**
     * 通过书籍价格查询图书
     * @param min
     * @param max
     * @return
     */
    Page pageByPrice(int pageNo,int pageSize,int min,int max);
}
