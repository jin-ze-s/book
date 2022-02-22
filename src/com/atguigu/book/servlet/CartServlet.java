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
     * 向购物车中添加商品
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获得添加图书的id
        String id = request.getParameter("id");
        int i = WebUtils.strToInt(id, 2);
        //获取图书
        Book book = bookService.queryBookById(i);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //添加图书
        cart.addItem(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));
        request.getSession().setAttribute("lastAdd", book.getName());
        //添加之后，重定向回请求发起的页面
        response.sendRedirect(request.getHeader("referer"));
    }

    /**
     * 删除购物车中商品
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int i = WebUtils.strToInt(id, 2);
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.deleteItem(i);
        response.sendRedirect(request.getHeader("referer"));
    }

    /**
     * 清空购物车
     */
    protected void clearItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        //清空购物车
        if (cart != null){
            cart.clear();
        }
        response.sendRedirect(request.getHeader("referer"));
    }

    /**
     * 修改商品数量
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
     * 向购物车中添加商品
     */
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获得添加图书的id
        String id = request.getParameter("id");
        int i = WebUtils.strToInt(id, 2);
        //获取图书
        Book book = bookService.queryBookById(i);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //添加图书
        cart.addItem(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",cart.getTotalCount());
        map.put("lastName", book.getName());
        Gson gson = new Gson();
//        将map字符串响应给客户端
        String jsonStr = gson.toJson(map);
        response.getWriter().write(jsonStr);
    }
}
