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
     * ��ҳ�ķ���
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
     * ��ҳ�ķ���
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
     * ���ɶ���
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            //����û�û�е�¼�����ض���ص�¼ҳ��
            response.sendRedirect("/book/pages/user/login.jsp");
            return;
        }
        String order = orderService.createOrder(cart, user.getId());
        if (order.contains("�޷��µ�")){
            request.getSession().setAttribute("errorMsg", order);
            response.sendRedirect("/book/pages/cart/checkout.jsp");
        }else {
            request.getSession().setAttribute("order", order);
            request.getSession().removeAttribute("errorMsg");
            response.sendRedirect("/book/pages/cart/checkout.jsp");
        }
    }


    /**
     * ��ѯ���ж���
     */
    protected void showAllOrders (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     * ����
     */
    protected void sendOrder (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String orderId = request.getParameter("orderId");
        orderService.sendOrder(orderId);
        response.sendRedirect(request.getContextPath()+"/orderServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    /**
     * �鿴��������
     */
    protected void showOrderDetail  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        request.setAttribute("orderItems", orderItems);
        request.getRequestDispatcher("pages/order/order_detail.jsp").forward(request, response);
    }

    /**
     * �鿴�ҵĶ���
     */
    protected void showMyOrders  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("showMyOrders�յ�����");
    }

    /**
     * ǩ�ն������ջ���
     */
    protected void receiveOrder (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String orderId = request.getParameter("orderId");
        orderService.receiveOrder(orderId);
        response.sendRedirect(request.getContextPath()+"/orderServlet?action=pageByUserId&pageNo="+request.getParameter("pageNo"));
    }
}



