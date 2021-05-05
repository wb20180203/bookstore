package daoImpl;

import pojo.OrderItem;

/**
 * @description:
 * @author:18312
 * @date:2020/11/18 13:27
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao{
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(name,count,price,total_price,order_id)value(?,?,?,?,?)";
        upDate(sql, orderItem.getName(), orderItem.getCount(),
                orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
