package com.atguigu.book.service.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.dao.OrderDao;
import com.atguigu.book.dao.OrderItemDao;
import com.atguigu.book.dao.impl.BookDaoImpl;
import com.atguigu.book.dao.impl.OrderDaoImpl;
import com.atguigu.book.dao.impl.OrderItemDaoImpl;
import com.atguigu.book.pojo.*;
import com.atguigu.book.service.OrderService;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Tom 2022/2/12 14:35
 */
@SuppressWarnings("all")
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();


    @Override
    public String createOrder(Cart cart, int userId) {
        Map<Integer, CartItem> cartItems = cart.getCartItems();
        for (Map.Entry<Integer,CartItem> entry : cartItems.entrySet()) {
            CartItem value = entry.getValue();
            Book book = bookDao.queryOneById(value.getId());
            if (book.getStock() - value.getCount() < 0) {
                return "【" + book.getName() + "】库存不足，无法下单";
            }
        }

        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //生成订单
        orderDao.saveorder(order);
//
//        产生异常
        int i = 10 / 0;

        //生成对应订单项
        for (Map.Entry<Integer,CartItem> entry : cartItems.entrySet()){
            CartItem value = entry.getValue();
            Book book = bookDao.queryOneById(value.getId());
            book.setStock(book.getStock()-value.getCount());
            book.setSales(book.getSales()+value.getCount());
            bookDao.updateBook(book);
            OrderItem orderItem = new OrderItem(0, value.getName(), value.getCount(), value.getPrice(), value.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
        }
        //生成订单后，清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public boolean sendOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrder(int userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public boolean receiveOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 2);
    }

    @Override
    public Page<Order> page(int pageNo, int pageSize) {
        Page<Order> page = new Page<>();
        page.setPageSize(pageSize);
        int pageTotalCount = orderDao.queryPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        int pageTotal = pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : (pageTotalCount / pageSize)+1;
        page.setPageTotal(pageTotal);
//        因为pageNo有边界检查，所以pageNo的赋值一定要在pageTotal之后
        page.setPageNo(pageNo);
        int begin = (page.getPageNo() - 1) * page.getPageSize();
        List<Order> items = orderDao.queryItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Order> pageByUserId(int pageNo, int pageSize, int userId) {
        Page<Order> page = new Page<>();
        page.setPageSize(pageSize);
        int pageTotalCount = orderDao.queryPageTotalCount(userId);
        page.setPageTotalCount(pageTotalCount);
        int pageTotal = pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : (pageTotalCount / pageSize)+1;
        page.setPageTotal(pageTotal);
//        因为pageNo有边界检查，所以pageNo的赋值一定要在pageTotal之后
        page.setPageNo(pageNo);
        int begin = (page.getPageNo() - 1) * page.getPageSize();
        List<Order> items = orderDao.queryItems(begin,pageSize,userId);
        page.setItems(items);
        return page;
    }
}
