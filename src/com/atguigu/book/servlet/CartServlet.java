package com.atguigu.book.servlet;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.impl.BookServiceImpl;
import com.atguigu.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tom 2022/2/11 19:09
 */
@SuppressWarnings("all")
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * ���ﳵ�������Ʒ
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ������ͼ���id
        String id = request.getParameter("id");
        int i = WebUtils.strToInt(id, 2);
        //��ȡͼ��
        Book book = bookService.queryBookById(i);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //���ͼ��
        cart.addItem(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));
        request.getSession().setAttribute("lastAdd", book.getName());
        //���֮���ض�����������ҳ��
        response.sendRedirect(request.getHeader("referer"));
    }

    /**
     * ɾ�����ﳵ����Ʒ
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int i = WebUtils.strToInt(id, 2);
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.deleteItem(i);
        response.sendRedirect(request.getHeader("referer"));
    }

    /**
     * ��չ��ﳵ
     */
    protected void clearItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        //��չ��ﳵ
        if (cart != null){
            cart.clear();
        }
        response.sendRedirect(request.getHeader("referer"));
    }

    /**
     * �޸���Ʒ����
     */
    protected void updateItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = WebUtils.strToInt(request.getParameter("id"), 1);
       int count = WebUtils.strToInt(request.getParameter("count"), 1);
       Cart cart = (Cart) request.getSession().getAttribute("cart");
       if (cart != null){
           cart.updateItem(id, count);
           response.sendRedirect(request.getHeader("referer"));
       }
    }


    /**
     * ���ﳵ�������Ʒ
     */
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ������ͼ���id
        String id = request.getParameter("id");
        int i = WebUtils.strToInt(id, 2);
        //��ȡͼ��
        Book book = bookService.queryBookById(i);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //���ͼ��
        cart.addItem(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",cart.getTotalCount());
        map.put("lastName", book.getName());
        Gson gson = new Gson();
//        ��map�ַ�����Ӧ���ͻ���
        String jsonStr = gson.toJson(map);
        response.getWriter().write(jsonStr);
    }
}
