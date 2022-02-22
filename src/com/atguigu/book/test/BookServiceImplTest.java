package com.atguigu.book.test;


import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Page;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;


public class BookServiceImplTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        Book book = new Book();
        book.setName("¹Å¶¼");
        book.setAuthor("´¨¶Ë¿µ³É");
        book.setStock(100);
        book.setPrice(new BigDecimal(42.5));
        book.setSales(1000);
        book.setPhoto("");
        System.out.println(bookService.addBook(book));
    }

    @Test
    public void deleteBookById() {
        System.out.println(bookService.deleteBookById(22));
    }

    @Test
    public void updateBook() {
        Book book = bookService.queryBookById(14);
        book.setAuthor("Ê©ÄÍâÖ");
        System.out.println(bookService.updateBook(book));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(14));
    }

    @Test
    public void queryList() {
        List<Book> books = bookService.queryList();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(1, 4);
        System.out.println(page);
    }

    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(1, 4, 40, 50);
        System.out.println(page);
    }
}
