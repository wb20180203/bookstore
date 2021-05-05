package webb;

import pojo.Book;
import pojo.Page;
import serviceImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @description:
 * @author:18312
 * @date:2020/11/14 10:33
 */
public class BookServlet extends HttpServlet {
    private BookServiceImpl bookService=new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 通过反射动态获取请求信息（该调用什么方法）
         */
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.addBook(book);
        resp.sendRedirect("bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 实现图书分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("bookServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("id");
        bookService.deleteBookById(Integer.parseInt(id));
        resp.sendRedirect("bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 更新图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBookById(book);
        resp.sendRedirect("bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 查询全部图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 修改图书信息用到的信息回显
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void book(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(Integer.parseInt(id));
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
}
