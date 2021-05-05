package webb;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @description:
 * @author:18312
 * @date:2020/11/13 15:31
 */
public class WebUtils {
    //将request的请求参数封装成javaBean对象
    public static <T> T copyParamToBean(Map map, T t){
        try {
            BeanUtils.populate(t,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }
    //转化成数值型
    public static int parseInt(String str,int defaultInt){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultInt;
    }
}
