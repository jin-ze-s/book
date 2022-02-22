package com.atguigu.book.service;

import com.atguigu.book.pojo.*;

import java.util.List;

public interface OrderService {

    String createOrder(Cart cart,int userId);

    List<Order> showAllOrders();

    boolean sendOrder(String orderId);

    List<OrderItem> showOrderDetail(String orderId);

    List<Order> showMyOrder(int userId);

    boolean receiveOrder(String orderId);

    Page<Order> page(int pageNo, int pageSize);

    Page<Order> pageByUserId(int pageNo, int pageSize, int userId);
}
