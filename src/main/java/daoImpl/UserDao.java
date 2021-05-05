package daoImpl;

import pojo.User;


/**
 * @description:
 * @author:18312
 * @date:2020/11/8 15:04
 */
public interface UserDao {


    /**
     * 注册，检查是否已有该用户名
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);


    /**
     * 保存用户信息
     * @param user
     */
    public int saveUser(User user);



    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password);

}
