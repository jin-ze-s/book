package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.pojo.User;
import com.atguigu.book.utils.DBUtils;

/**
 * @author Tom 2022/1/31 13:32
 */
public class UserDaoImpl implements UserDao {


    @Override
    public User queryOne(User user) {
        return DBUtils.queryOne(User.class, "select * from user where username=? and password=?", user.getUsername(),user.getPassword());
    }

    @Override
    public boolean addOne(User user) {
        return DBUtils.exeUpdate(DBUtils.getConn(), "insert into user(username,password,email) values(?,?,?)", user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryOneByUsername(String username) {
        return DBUtils.queryOne(User.class, "select * from user where username=?", username);
    }


}
