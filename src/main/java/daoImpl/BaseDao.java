package daoImpl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对数据库的基础操作
 * @author:18312
 * @date:2020/11/8 14:17
 */
public abstract class BaseDao<T> {
    private Class<T> clazz=null;

    /**
     *获取父类的泛型
     */
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        clazz= (Class<T>) actualTypeArguments[0];
    }

    /**
     * 增删改操作
     * @param sql
     * @param args
     * @return如果大于0，则操作成功，返回大小为受影响的行数。如果返回为0，则操作失败。
     */
    public int upDate(String sql,Object...args){
        PreparedStatement pp=null;
        Connection con=null;
        try {
            con = JDBCUtils.getConnection();
            pp = con.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                pp.setObject(i+1,args[i]);
            }
            return pp.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


    /**
     * 获取结果类的集合
     * @param sql
     * @param args
     * @return返回结果类集合
     */
    public List<T> getResultList(String sql,Object...args){
        ArrayList<T> ts = new ArrayList<>();
        Connection con=null;
        PreparedStatement pp=null;
        try {
            Constructor<T> constructor = clazz.getConstructor();
            con = JDBCUtils.getConnection();
            pp = con.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                pp.setObject(i+1,args[i]);
            }
            ResultSet rs = pp.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int cc = md.getColumnCount();
            while(rs.next()){
                T t = constructor.newInstance();
                for(int i=0;i<cc;i++){
                    Object o = rs.getObject(i + 1);
                    String columnLabel = md.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,o);
                }
                ts.add(t);
            }
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回一个类
     * @param sql
     * @param args
     * @return
     */
    public T getResult(String sql,Object...args){
        Connection con = null;
        PreparedStatement pp = null;
        try {
            con = JDBCUtils.getConnection();
            pp = con.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                pp.setObject(i+1,args[i]);
            }
            ResultSet res = pp.executeQuery();
            ResultSetMetaData md = res.getMetaData();
            int cc = md.getColumnCount();
            Constructor<T> constructor = clazz.getConstructor();
            if(res.next()){
                T t = constructor.newInstance();
                for(int i=0;i<cc;i++){
                    Object o = res.getObject(i + 1);
                    String columnLabel = md.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,o);
                }
                return t;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args){
        Connection con = JDBCUtils.getConnection();
        QueryRunner runner = new QueryRunner();
        try {
            return runner.query(con, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
