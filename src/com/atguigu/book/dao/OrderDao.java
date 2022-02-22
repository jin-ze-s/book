package com.atguigu.book.dao;

import com.atguigu.book.pojo.Order;

import java.util.List;

/**
 * @author Tom 2022/2/12 13:12
 */
public interface OrderDao {

    /**
     * ��Ӷ���
     * @param order ��������
     * @return �Ƿ���ӳɹ�
     */
    boolean saveorder(Order order);

    /**
     *
     * @return ���ж���
     */
    List<Order> queryOrders();

    /**
     * ���Ķ���״̬
     * @param orderId ������
     * @param status ״̬
     * @return �Ƿ��޸ĳɹ�
     */
    boolean changeOrderStatus(String orderId,int status);

    /**
     *
     * @param userId �û�ID
     * @return ��������
     */
    List<Order> queryOrderByUserId(int userId);


    int queryPageTotalCount();



    public int queryPageTotalCount(int UserId);


    List<Order> queryItems(int begin, int pageSize);

    List<Order> queryItems(int begin, int pageSize, int userId);
}
