package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.utils.DBUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Tom 2022/2/7 16:50
 */
public class BookDaoImpl implements BookDao {


    @Override
    public boolean addOne(Book book) {
        return DBUtils.exeUpdate(DBUtils.getConn(),
                "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `photo`) " +
                        "values(?,?,?,?,?,?)",
                book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getPhoto());
    }

    @Override
    public boolean deleteOneById(int id) {
        return DBUtils.exeUpdate(DBUtils.getConn(),
                "delete from t_book where `id` = ?", id);
    }

    @Override
    public boolean updateBook(Book book) {
        return DBUtils.exeUpdate(DBUtils.getConn(),
                "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`photo`=? where `id`=?",
                book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getPhoto(),book.getId());
    }

    @Override
    public Book queryOneById(int id) {
        return DBUtils.queryOne(Book.class, "select * from t_book where id = ?", id);
    }

    @Override
    public List<Book> queryAll() {
        return DBUtils.queryList(Book.class, "select * from t_book");
    }

    @Override
    public int queryPageTotalCount() {
        String sql = "select count(*) from t_book";
            return DBUtils.queryCount(sql);
    }

    @Override
    public List<Book> queryItems(int begin, int pageSize) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`photo` from t_book limit ?,?";
        return DBUtils.queryList(Book.class, sql, begin,pageSize);
    }

    @Override
    public int queryPageTotalCount(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        return DBUtils.queryCount(sql, min,max);
    }

    @Override
    public List<Book> queryItems(int begin, int pageSize, int min, int max) {
        String sql = "select `id`,`name`,`price`,`author`,`sales`,`stock`,`photo` from t_book where price between ? and ? order by price limit ?,?";
        return DBUtils.queryList(Book.class,sql,min,max,begin,pageSize);
    }
}
