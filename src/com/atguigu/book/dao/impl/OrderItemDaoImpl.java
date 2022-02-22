package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.OrderItemDao;
import com.atguigu.book.pojo.OrderItem;
import com.atguigu.book.utils.DBUtils;

import java.util.List;

/**
 * @author Tom 2022/2/12 13:14
 */
public class OrderItemDaoImpl implements OrderItemDao {


    @Override
    public boolean saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)" +
                "values(?,?,?,?,?)";
        return DBUtils.exeUpdate(DBUtils.getConn(), sql,
                orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotal_price(),orderItem.getOrder_id());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` from t_order_item where order_id = ?";
        return DBUtils.queryList(OrderItem.class, sql,orderId);
    }
}
