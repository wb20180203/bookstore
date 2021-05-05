package pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author:18312
 * @date:2020/11/18 10:43
 */
public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    private int status=0;
    private int userId;

    public Order() {}

    public Order(String orderId, Date createTime, BigDecimal price, int status, int userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {return orderId;}

    public void setOrderId(String orderId) {this.orderId = orderId;}

    public Date getCreateTime() {return createTime;}

    public void setCreateTime(Date createTime) {this.createTime = createTime;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getStatus() {return status;}

    public void setStatus(int status) {this.status = status;}

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
