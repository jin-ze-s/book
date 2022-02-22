package com.atguigu.book.servlet;

import com.atguigu.book.pojo.*;
import com.atguigu.book.service.OrderService;
import com.atguigu.book.service.impl.OrderServiceImpl;
import com.atguigu.book.utils.DBUtils;
import com.atguigu.book.utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Tom 2022/2/12 15:25
 */
public class OrderServlet extends BaseServlet {


  private  OrderService orderService = new OrderServiceImpl();


    /**
     * 分页的方法
     */
    protected void page (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.strToInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.strToInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Order> page = orderService.page(pageNo,pageSize);
        page.setUrl("orderServlet?action=page");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     *
     * 分页的方法
     */
    protected void pageByUserId (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.strToInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.strToInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        Page<Order> page = orderService.pageByUserId(pageNo,pageSize,userId);
        page.setUrl("orderServlet?action=pageByUserId");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    /**
     * 生成订单
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            //如果用户没有登录，则重定向回登录页面
            response.sendRedirect("/book/pages/user/login.jsp");
            return;
        }
        String order = orderService.createOrder(cart, user.getId());
        if (order.contains("无法下单")){
            request.getSession().setAttribute("errorMsg", order);
            response.sendRedirect("/book/pages/cart/checkout.jsp");
        }else {
            request.getSession().setAttribute("order", order);
            request.getSession().removeAttribute("errorMsg");
            response.sendRedirect("/book/pages/cart/checkout.jsp");
        }
    }


    /**
     * 查询所有订单
     */
    protected void showAllOrders (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     * 发货
     */
    protected void sendOrder (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String orderId = request.getParameter("orderId");
        orderService.sendOrder(orderId);
        response.sendRedirect(request.getContextPath()+"/orderServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    /**
     * 查看订单详情
     */
    protected void showOrderDetail  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        request.setAttribute("orderItems", orderItems);
        request.getRequestDispatcher("pages/order/order_detail.jsp").forward(request, response);
    }

    /**
     * 查看我的订单
     */
    protected void showMyOrders  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("showMyOrders收到请求");
    }

    /**
     * 签收订单（收货）
     */
    protected void receiveOrder (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String orderId = request.getParameter("orderId");
        orderService.receiveOrder(orderId);
        response.sendRedirect(request.getContextPath()+"/orderServlet?action=pageByUserId&pageNo="+request.getParameter("pageNo"));
    }
}



