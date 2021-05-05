import org.junit.Test;
import pojo.User;
import serviceImpl.UserServiceImpl;

/**
 * @description:
 * @author:18312
 * @date:2020/11/8 16:46
 */
public class UserServiceTest {
    @Test
    public void tes1(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.registerUser(new User(1,"12","123","123@qq.com"));
    }

    @Test
    public void tes2(){
        UserServiceImpl userService = new UserServiceImpl();
        User login = userService.login(new User(1, "123456", "123456", "123456@qq.com"));
        System.out.println(login);
    }

    @Test
    public void tes3(){
        UserServiceImpl userService = new UserServiceImpl();
        boolean b = userService.existsUsername("12");
        System.out.println(b);
    }
}
