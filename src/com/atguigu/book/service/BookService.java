package com.atguigu.book.service;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Page;

import java.util.List;

public interface BookService {

    boolean addBook(Book book);

    boolean deleteBookById(int id);

    boolean updateBook(Book book);

    Book queryBookById(int id);

    List<Book> queryList();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
