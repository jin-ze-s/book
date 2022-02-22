package com.atguigu.book.test;


import com.atguigu.book.dao.OrderDao;
import com.atguigu.book.dao.impl.OrderDaoImpl;
import com.atguigu.book.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class OrderDaoImplTest {


    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveorder() {
        Order order = new Order("xyz123", new Date(), new BigDecimal(100), 0, 1);
        System.out.println(orderDao.saveorder(order));
    }

    @Test
    public void queryOrders(){
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus(){
        int status = 1;
        String orderId = "xyz123";
        System.out.println(orderDao.changeOrderStatus(orderId, status));
    }

    @Test
    public void queryOrderByUserId(){
        int userId = 1;
        List<Order> orders = orderDao.queryOrderByUserId(userId);
        System.out.println(orders.size());
    }
}
