package daoImpl;

import pojo.User;

/**
 * @description:
 * @author:18312
 * @date:2020/11/8 15:18
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao{

    @Override
    public User queryUserByUsername(String username) {
        String sql="select user_id id,user_name username,user_password password,user_email email from users where user_name=?";
        return getResult(sql, username);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into users(user_name,user_password,user_email)value(?,?,?)";
        return upDate(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select user_id id,user_name username,user_password password,user_email email from users where user_name=? and user_password=?";
        return getResult(sql, username,password);
    }
}
