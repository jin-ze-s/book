package com.atguigu.book.dao;

import com.atguigu.book.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem 订单项
     * @return 是否添加成功
     */
    boolean saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemByOrderId(String orderId);
}
