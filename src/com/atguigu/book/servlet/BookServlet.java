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
import java.util.List;

/**
 * @author Tom 2022/2/7 18:23
 */
@SuppressWarnings("all")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();


    /**
     *
     * 分页的方法
     */
    protected void page (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.strToInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.strToInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }



    protected void add (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.strToInt(request.getParameter("pageNo"), 0);
        pageNo += 1;
        Book book = WebUtils.mapToBean(request.getParameterMap(), new Book());
        bookService.addBook(book);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }


    protected void delete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("id");
        int id = WebUtils.strToInt(str, 0);
        int pageNo = WebUtils.strToInt(request.getParameter("pageNo"), 1);
        bookService.deleteBookById(id);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }



    protected void update (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Book book = WebUtils.mapToBean(request.getParameterMap(), new Book());
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }



    protected void queryBookById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String str = request.getParameter("id");
        int id = WebUtils.strToInt(str, 0);
        Book book = bookService.queryBookById(id);
        request.setAttribute("book", book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }




    protected void list (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Book> books = bookService.queryList();
        request.setAttribute("list", books);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }

}
