package daoImpl;

import pojo.Order;
import pojo.OrderItem;

/**
 * @description:
 * @author:18312
 * @date:2020/11/18 11:30
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao{

    @Override
    public void createOrder(Order order) {
        String sql="insert into t_order(order_id,create_time,price,status,user_id)value(?,?,?,?,?)";
        upDate(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
