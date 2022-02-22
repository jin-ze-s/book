package com.atguigu.book.test;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.dao.impl.BookDaoImpl;
import com.atguigu.book.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tom 2022/2/7 17:07
 */
public class TestBookDao {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addOne(){
        Book book = new Book();
        book.setName("我是猫");
        book.setAuthor("夏目漱石");
        book.setPrice(new BigDecimal(50));
        book.setSales(400);
        book.setStock(2000);
        System.out.println(bookDao.addOne(book));
    }

    @Test
    public void deleteOneById(){
        int id = 21;
        System.out.println(bookDao.deleteOneById(id));
    }

    @Test
    public void updateBook(){
        Book book = bookDao.queryOneById(13);
        book.setAuthor("吴承恩");
        System.out.println(bookDao.updateBook(book));
    }

    @Test
    public void queryOneById(){
        int id = 13;
        System.out.println(bookDao.queryOneById(id));
    }

    @Test
    public void queryAll(){
        List<Book> books = bookDao.queryAll();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryPageTotalCount(){
        System.out.println(bookDao.queryPageTotalCount());
    }

    @Test
    public void queryItems(){
        int begin = 4;
        int pageSize = 4;
        List<Book> books = bookDao.queryItems(begin, pageSize);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryPageTotalCountByPrice(){
        int min = 50;
        int max = 60;
        System.out.println(bookDao.queryPageTotalCount(min, max));
    }

    @Test
    public void queryItemsByPrice(){
        int begin = 0;
        int pageSize = 4;
        List<Book> books = bookDao.queryItems(0, 4, 0, 50);
        for (Book book : books) {
            System.out.println(book);
        }
    }

}
