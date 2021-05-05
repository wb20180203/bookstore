package serviceImpl;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @description:
 * @author:18312
 * @date:2020/11/18 11:39
 */
public class OrderServiceImplTest {
    private OrderService orderService=new OrderServiceImpl();
    @Test
    public void saveOrder() {
        Cart cart=new Cart();
        cart.addBook(new CartItem(1,"hhhh",2,new BigDecimal(200)));
        cart.addBook(new CartItem(2,"hfgdg",2,new BigDecimal(400)));
        orderService.createOrder(cart,1);
    }
}