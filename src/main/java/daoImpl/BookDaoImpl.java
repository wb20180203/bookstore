package daoImpl;

import pojo.Book;

import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author:18312
 * @date:2020/11/13 20:06
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao{

    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(name,author,price,sales,stock,img_path)value(?,?,?,?,?,?)";
        return upDate(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        return upDate(sql,id);
    }

    @Override
    public int deleteBookByName(String name) {
        String sql="delete from t_book where name=?";
        return upDate(sql,name);
    }

    @Override
    public int updateBookByID(Book book) {
        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return upDate(sql,book.getName(),book.getAuthor(),book.getPrice(),
                book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select * from t_book where id=?";
        return getResult(sql,id);
    }

    @Override
    public List<Book> queryBookByAuthor(String author) {
        String sql="select * from t_book where author=?";
        return getResultList(sql,author);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select * from t_book";
        return getResultList(sql);
    }

    @Override
    public int queryForPageTotal() {
        String sql="select count(*) from t_book";
        Number number = (Number) queryForSingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql="select * from t_book limit ?, ?";
        return getResultList(sql,begin,pageSize);
    }

    @Override
    public int queryForLimitTotal(int min, int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        Number number= (Number) queryForSingleValue(sql,min,max);
        return number.intValue();
    }

    @Override
    public List<Book> queryForLimitItems(int min, int max, int begin, int end) {
        String sql="select * from t_book where price between ? and ? order by price limit ?, ?";
        return getResultList(sql, min, max, begin, end);
    }
}
