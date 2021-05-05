package Filter;

import utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author:18312
 * @date:2020/11/20 14:26
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndCloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.rollbackAndCloseConnection();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
