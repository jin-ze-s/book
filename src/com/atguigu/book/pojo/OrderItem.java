package com.atguigu.book.pojo;

import java.math.BigDecimal;

/**
 * @author Tom 2022/2/12 12:46
 */
public class OrderItem {

    private int id;
    private String name;
    private int count;
    private BigDecimal price;
    private BigDecimal total_price;
    private String order_id;

    public OrderItem() {
    }

    public OrderItem(int id, String name, int count, BigDecimal price, BigDecimal total_price, String order_id) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.total_price = total_price;
        this.order_id = order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", total_price=" + total_price +
                ", order_id='" + order_id + '\'' +
                '}';
    }
}
