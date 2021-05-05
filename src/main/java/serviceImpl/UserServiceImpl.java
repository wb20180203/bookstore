package serviceImpl;

import daoImpl.UserDaoImpl;
import pojo.User;

/**
 * @description:
 * @author:18312
 * @date:2020/11/8 16:29
 */
public class UserServiceImpl implements UserService{
    private UserDaoImpl userDao = new UserDaoImpl();

    /**
     * 用户注册业务
     * @param user
     */
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    /**
     * 判断用户名是否存在
     * @param name
     * @return
     */
    @Override
    public boolean existsUsername(String name) {
        User user = userDao.queryUserByUsername(name);
        if(user!=null)return true;
        return false;
    }
}
