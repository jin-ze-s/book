package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.OrderDao;
import com.atguigu.book.pojo.Order;
import com.atguigu.book.utils.DBUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @author Tom 2022/2/12 13:14
 */
public class OrderDaoImpl implements OrderDao {


    @Override
    public boolean saveorder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)" +
                "values(?,?,?,?,?)";
        return DBUtils.exeUpdate(DBUtils.getConn(), sql,
                order.getOrder_id(),order.getCreate_time(),order.getPrice(),order.getStatus(),order.getUser_id());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id`,`create_time`,`price`,`status`,`user_id` from t_order";
        return DBUtils.queryList(Order.class, sql);
    }

    @Override
    public boolean changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set status=? where order_id=?";
        return DBUtils.exeUpdate(DBUtils.getConn(), sql, status,orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        String sql = "select `order_id`,`create_time`,`price`,`status`,`user_id` from t_order where user_id=?";
        return DBUtils.queryList(Order.class, sql, userId);
    }

    @Override
    public int queryPageTotalCount() {
        String sql = "select count(*) from t_order";
        return DBUtils.queryCount(sql);
    }

    @Override
    public int queryPageTotalCount(int UserId) {
        String sql = "select count(*) from t_order where user_id=?";
        return DBUtils.queryCount(sql, UserId);
    }


    @Override
    public List<Order> queryItems(int begin, int pageSize) {
        String sql = "select * from t_order limit ?,?";
        return DBUtils.queryList(Order.class, sql, begin,pageSize);
    }

    @Override
    public List<Order> queryItems(int begin, int pageSize, int userId) {
        String sql = "select * from t_order where user_id = ? limit ?,?";
        return DBUtils.queryList(Order.class, sql, userId,begin,pageSize);
    }
}
