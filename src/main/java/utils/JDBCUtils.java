package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    //数据库连接池
    private static DruidDataSource dataSources;
    //将一个连接放入ThreadLocal中，确保在事务中使用的是同一个数据库连接
    private static ThreadLocal<Connection> conn=new ThreadLocal<>();


    //初始化数据库连接池，加载配置文件
    static{
        try {
            Properties ps=new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            ps.load(is);
            dataSources = (DruidDataSource) DruidDataSourceFactory.createDataSource(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接
     * @return 一个数据库连接
     */
    public static Connection getConnection(){
        Connection con = conn.get();//在ThreadLocal中获取连接
        try {
            if(con==null){//如果没有连接，则通过数据库连接池拿一个连接并放入ThreadLocal中
                con = dataSources.getConnection();
                conn.set(con);
                con.setAutoCommit(false);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    /**
     * 事务中没有出现错误则提交事务并关闭数据库连接
     */
    public static void commitAndCloseConnection(){
        Connection con = conn.get();
        if(con!=null){
            try {
                con.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //将ThreadLocal中的连接移除
        conn.remove();
    }


    /**
     * 事务中出现错误则回滚事务并关闭连接
     */
    public static void rollbackAndCloseConnection(){
        Connection con = conn.get();
        if(con!=null){
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //将ThreadLocal中的连接移除
        conn.remove();
    }
}
