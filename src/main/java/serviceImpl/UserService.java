package serviceImpl;

import pojo.User;

/**
 * @description: 业务层接口
 * @author:18312
 * @date:2020/11/8 16:25
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);


    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);


    /**
     * 检查用户名是否可用
     * @param name
     * @return
     */
    public boolean existsUsername(String name);
}
