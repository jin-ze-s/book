package com.atguigu.book.dao;

import com.atguigu.book.pojo.Order;

import java.util.List;

/**
 * @author Tom 2022/2/12 13:12
 */
public interface OrderDao {

    /**
     * 添加订单
     * @param order 订单对象
     * @return 是否添加成功
     */
    boolean saveorder(Order order);

    /**
     *
     * @return 所有订单
     */
    List<Order> queryOrders();

    /**
     * 更改订单状态
     * @param orderId 订单号
     * @param status 状态
     * @return 是否修改成功
     */
    boolean changeOrderStatus(String orderId,int status);

    /**
     *
     * @param userId 用户ID
     * @return 订单集合
     */
    List<Order> queryOrderByUserId(int userId);


    int queryPageTotalCount();



    public int queryPageTotalCount(int UserId);


    List<Order> queryItems(int begin, int pageSize);

    List<Order> queryItems(int begin, int pageSize, int userId);
}
