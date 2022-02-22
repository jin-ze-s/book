package com.atguigu.book.dao;

import com.atguigu.book.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    /**
     * ���涩����
     * @param orderItem ������
     * @return �Ƿ���ӳɹ�
     */
    boolean saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemByOrderId(String orderId);
}
