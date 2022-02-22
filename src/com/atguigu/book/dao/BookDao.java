package com.atguigu.book.dao;

import com.atguigu.book.pojo.Book;

import java.util.List;

public interface BookDao  {

    /**
     *
     * @param book 添加的图书
     * @return 是否成功
     */
    boolean addOne(Book book);

    /**
     *
     * @param id 要删除书的id
     * @return 是否成功
     */
    boolean deleteOneById(int id);

    /**
     * 修改图书
     * @param book 更新后的图书
     * @return 是否成功
     */
    boolean updateBook(Book book);

    /**
     *
     * @param id 查询的id
     * @return 对应id的图书
     */
    Book queryOneById(int id);

    /**
     *
     * @return 返回查询到的所有图书
     */
    List<Book> queryAll();

    /**
     *
     * @return 总数据数
     */
    int queryPageTotalCount();

    /**
     *
     * @param begin 开始索引
     * @param pageSize 一页显示的数据数
     * @return 当前页数据
     */
    List<Book> queryItems(int begin, int pageSize);

    /**
     *
     * @param min 最小价格
     * @param max 最高价格
     * @return 总的记录数
     */
    int queryPageTotalCount(int min, int max);

    /**
     *
     * @param begin 开始索引
     * @param pageSize 一页显示的数据
     * @param min 最小价格
     * @param max 最高价格
     * @return 当前页页数
     */
    List<Book> queryItems(int begin, int pageSize, int min, int max);
}
