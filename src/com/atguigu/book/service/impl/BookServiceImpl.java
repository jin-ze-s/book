package com.atguigu.book.service.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.dao.impl.BookDaoImpl;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Page;
import com.atguigu.book.service.BookService;

import java.util.List;

/**
 * @author Tom 2022/2/7 17:54
 */
@SuppressWarnings("all")
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public boolean addBook(Book book) {
        return bookDao.addOne(book);
    }

    @Override
    public boolean deleteBookById(int id) {
        return bookDao.deleteOneById(id);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryOneById(id);
    }

    @Override
    public List<Book> queryList() {
        return bookDao.queryAll();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);
        int pageTotalCount = bookDao.queryPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        int pageTotal = pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : (pageTotalCount / pageSize)+1;
        page.setPageTotal(pageTotal);
//        因为pageNo有边界检查，所以pageNo的赋值一定要在pageTotal之后
        page.setPageNo(pageNo);
        int begin = (page.getPageNo() - 1) * page.getPageSize();
        List<Book> items = bookDao.queryItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {

        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);
        int pageTotalCount = bookDao.queryPageTotalCount(min,max);
        page.setPageTotalCount(pageTotalCount);
        int pageTotal = pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : (pageTotalCount / pageSize)+1;
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin = (page.getPageNo() - 1) * page.getPageSize();
        List<Book> items = bookDao.queryItems(begin, pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
