import daoImpl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

/**
 * @description:
 * @author:18312
 * @date:2020/11/8 15:39
 */
public class UserDaoTest {
    @Test
    public void UserDaoTest1(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsername("123456");
        System.out.println(user);
    }

    @Test
    public void UserDaoTest2(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsernameAndPassword("123456", "123456");
        System.out.println(user);
    }


    @Test
    public void UserDaoTest3(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User(2, "1234", "1234", "1234@qq.com");
        System.out.println(userDao.saveUser(user));
    }
}
