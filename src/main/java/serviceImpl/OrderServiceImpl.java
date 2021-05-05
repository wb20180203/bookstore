package serviceImpl;

import daoImpl.*;
import pojo.*;

import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author:18312
 * @date:2020/11/18 11:37
 */
public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart,int userId) {
        //保存order数据
        String orderId = System.currentTimeMillis() +""+userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.createOrder(order);

        for(Map.Entry<Integer,CartItem> entry:cart.getItems().entrySet()){
            //遍历购物车数据保存到orderItem中
            CartItem cartItem = entry.getValue();
            orderItemDao.saveOrderItem(new OrderItem(null, cartItem.getName(),
                    cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId));
            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBookByID(book);
        }
        cart.clear();
        return orderId;
    }
}
