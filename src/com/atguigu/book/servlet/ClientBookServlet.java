package com.atguigu.book.servlet;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Page;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.impl.BookServiceImpl;
import com.atguigu.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tom 2022/2/8 20:56
 */
@SuppressWarnings("all")
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();


    /**
     *
     * 分页的方法
     */
    protected void page (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.strToInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.strToInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    /**
     * 根据价格区间查询
     */
    protected void pageByPrice (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int pageNo = WebUtils.strToInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.strToInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.strToInt(request.getParameter("min"), 0);
        int max = WebUtils.strToInt(request.getParameter("max"), 1000);
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        page.setUrl("client/bookServlet?action=pageByPrice");
        request.setAttribute("page", page);
        request.setAttribute("min", min);
        request.setAttribute("max", max);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

}
