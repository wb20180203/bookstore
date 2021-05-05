package daoImpl;

import pojo.Book;

import java.util.List;

/**
 * @description:
 * @author:18312
 * @date:2020/11/13 20:05
 */
public interface BookDao {
    /**
     * 添加书籍
     * @param book
     * @return 受影响的行数
     */
    public int addBook(Book book);

    /**
     * 通过书籍ID删除
     * @param id
     * @return受影响的行数
     */
    public int deleteBookById(Integer id);

    /**
     * 通过书籍名删除
     * @param name
     * @return 受影响的行数
     */
    public int deleteBookByName(String name);

    /**
     * 通过ID更改书籍信息
     * @param book
     * @return
     */
    public int updateBookByID(Book book);


    /**
     *通过ID查询书籍
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 通过作者名查询书籍
     * @param author
     * @return
     */
    public List<Book> queryBookByAuthor(String author);

    /**
     * 查询全部书籍
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查询全部数据个数
     * @return
     */
    public int queryForPageTotal();

    /**
     * 查询当前页的数据
     * @return
     */
    public List<Book> queryForItems(int begin,int pageSize);

    /**
     * 查询某属性区间的数据
     * @param min
     * @param max
     * @return
     */
    public int queryForLimitTotal(int min,int max);

    /**
     * 查询某属性区间的数据并分页
     * @param min
     * @param max
     * @param begin
     * @param end
     * @return
     */
    public List<Book> queryForLimitItems(int min,int max,int begin,int end);
}
