package serviceImpl;

import pojo.Cart;
import pojo.Order;

/**
 * @description:
 * @author:18312
 * @date:2020/11/18 11:36
 */
public interface OrderService {
    String createOrder(Cart cart,int id);
}
