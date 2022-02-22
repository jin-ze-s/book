package com.atguigu.book.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Tom 2022/2/12 12:46
 */
public class Order {

    private String order_id;
    private Date create_time;
    private BigDecimal price;
    /**0表示未发货
     * 1表示已发货
     * 2表示已收货
     */
    private int status;
    private int user_id;

    public Order() {
    }

    public Order(String order_id, Date create_time, BigDecimal price, int status, int user_id) {
        this.order_id = order_id;
        this.create_time = create_time;
        this.price = price;
        this.status = status;
        this.user_id = user_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", create_time=" + create_time +
                ", price=" + price +
                ", status=" + status +
                ", user_id=" + user_id +
                '}';
    }
}
