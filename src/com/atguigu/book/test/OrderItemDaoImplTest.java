package com.atguigu.book.test;

import com.atguigu.book.dao.OrderItemDao;
import com.atguigu.book.dao.impl.OrderItemDaoImpl;
import com.atguigu.book.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {

        OrderItem orderItem = new OrderItem();
        orderItem.setName("Tomcat”ÎJavaweb±‡≥Ã");
        orderItem.setCount(1);
        orderItem.setPrice(new BigDecimal(100));
        orderItem.setTotal_price(new BigDecimal(100));
        orderItem.setOrder_id("xyz123");
        System.out.println(orderItemDao.saveOrderItem(orderItem));
    }


    @Test
    public void queryOrderItemByOrderId(){
        String orderId = "16446486884321";
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId(orderId);
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}
