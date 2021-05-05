package webb;

import pojo.User;
import serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @description:
 * @author:18312
 * @date:2020/11/12 15:38
 */
public class UserServlet extends HttpServlet {

    private UserServiceImpl user=new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        //获取请求参数中方法名称
        String action = req.getParameter("action");
        try {
            //通过反射机制动态获取需要调用的方法
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用该方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    //注销登录
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //停止session生命周期
        req.getSession().invalidate();
        //请求转发到首页
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    //用户登录功能
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将请求参数封装成一个user对象
        User user1 = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //调用userService的用户登录方法查找数据库中是否存在该用户，返回查找的user对象
        User user2 = user.login(user1);
        if(user2!=null){
            //如果有该用户，则将该用户保存到session域中，用于用户名回显
            req.getSession().setAttribute("user",user2);
            //请求转发到登陆成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else{
            //没有该用户，则保存输入的信息到request中，用于回显信息
            req.setAttribute("mesg","用户名或密码错误");
            req.setAttribute("username",user1.getUsername());
            req.setAttribute("password",user1.getPassword());
            //请求转发到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    private void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将服务器的验证码提取出来
        String key = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的服务器验证码，防止用户重复提交
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //提取用户输入的验证码
        String code = req.getParameter("code");
        //将请求参数封装成一个user对象
        User user1 = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //比较用户输入的验证码是否与服务器提供的验证码相同
        if(key!=null && key.equalsIgnoreCase(code)){
            //如果相同，则查找用户名是否可用
            if(user.existsUsername(user1.getUsername())){
                //数据回显
                req.setAttribute("mesg","用户名已存在");
                req.setAttribute("password",user1.getPassword());
                req.setAttribute("email",user1.getEmail());
                req.setAttribute("code",code);
                //请求转发到注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                //注册用户
                user.registerUser(user1);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            //数据回显
            req.setAttribute("mesg","验证码错误");
            req.setAttribute("username",user1.getUsername());
            req.setAttribute("password", user1.getPassword());
            req.setAttribute("email",user1.getEmail());
            req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
        }
    }
}
