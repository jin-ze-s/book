package com.atguigu.book.test;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.Order;
import com.atguigu.book.pojo.OrderItem;
import com.atguigu.book.service.OrderService;
import com.atguigu.book.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("all")
public class OrderServiceImplTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "我就不放弃!", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(1, "我就不放弃!", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "加油！", 1, new BigDecimal(100), new BigDecimal(100)));
        System.out.println(orderService.createOrder(cart, 1));
    }

    @Test
    public void showAllOrders(){
        List<Order> orders = orderService.showAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder(){
        String orderId = "16446560427151";
        System.out.println(orderService.sendOrder(orderId));
    }

    @Test
    public void showOrderDetail(){
        String orderId = "16446560427151";
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void  showMyOrder(){
        int userId = 1;
        System.out.println(orderService.showMyOrder(userId).size());
    }

    @Test
    public void receiveOrder(){
        String orderId = "xyz123";
        System.out.println(orderService.receiveOrder(orderId));
    }
}
